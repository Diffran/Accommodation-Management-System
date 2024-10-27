/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.gestors;

import eac3.model.*;
import org.eclipse.persistence.internal.oxm.schema.model.All;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author  Diana
 */
public class GestorAllotjament {

    private EntityManager em;

    public GestorAllotjament(EntityManager em) {
        this.em = em;
    }

    /**
     * Insereix un allotjament
     *
     * @param allotjament un allotjament
     * @throws GestorException si el codi de l'establiment ja existeix
	 *
     */
    public void inserir(Allotjament allotjament) throws GestorException {
        //mira si existeix
        if(em.find(Allotjament.class, allotjament.getCodi()) != null){
            throw new GestorException("l'allotjament amb codi: "+ allotjament.getCodi() + " ja existeix");
        }
        //fa la transaccio
        em.getTransaction().begin();

        try{
            em.persist(allotjament);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            throw new GestorException("ERROR AL INSERIR L'ALLOTJAMENT" + e.getMessage());
        }
    }

    /**
     * Elimina un allotjament de la base de dades
     *
     * @param codi el codi de l'allotjament
     * @throws GestorException si l'allotjament no existeix
     */
    public void eliminar(String codi) throws GestorException {
        Allotjament allotjamentAEliminar = em.find(Allotjament.class, codi);

        if(allotjamentAEliminar == null){
            throw new GestorException("l'allotjament amb codi: "+ codi+ " no existeix");
        }

        em.getTransaction().begin();
        em.remove(allotjamentAEliminar);
        em.getTransaction().commit();
    }

    /**
     * Consulta de tots els allotjaments 
     *
     * @return la llista d'allotjaments 
     */
    public List<Allotjament> consultaAllotjaments() {
        List<Allotjament> allotjaments;

        Query consultaAllotjaments = em.createQuery("Select a FROM Allotjament a");
        allotjaments = consultaAllotjaments.getResultList();

        return allotjaments;
    }

    /**
     * Obtenir els allotjaments lliures d'un establiment
     *
     * @param codiEstabliment el codi de l'establiment
     * @return la llista d'allotjaments lliures
     * @throws GestorException si l'establiment no existeix
     */
    public List<Allotjament> consultaAllotjamentsLliures(String codiEstabliment) throws GestorException {
        List<Allotjament> allotjamentsDeEstabliment;

        //comprovar que existeixi un establiment amb el codi
        if(em.find(Establiment.class, codiEstabliment) == null){
            throw new GestorException("no Existeix un Establiment amb aquest codi");
        }

        Query consultaAllotjaments = em.createQuery("SELECT a FROM Allotjament a " +
                "WHERE a.establiment.codi= :codiEstabliment AND a.ocupat=false");
        //RECORDA SET EL PARAMETRE!!!
        consultaAllotjaments.setParameter("codiEstabliment", codiEstabliment);
        allotjamentsDeEstabliment = consultaAllotjaments.getResultList();

        return allotjamentsDeEstabliment;
    }

    /**
     * Actualitza la capacitat d'un allotjament. També el seu preu
	 * de manera proporcional a l'augment de capacitat
     * @param codi el codi de l'allotjament
     * @param capacitat la nova capacitat
     * @throws GestorException si l'allotjament no existeix
     */
    void actualitzarCapacitat(String codi, int capacitat) throws GestorException {
        em.getTransaction().begin();
        Allotjament allotjament = em.find(Allotjament.class, codi);

        //comprovar que existeixi un establiment amb el codi
        if(allotjament == null){
            throw new GestorException("no Existeix un Allotjament amb aquest codi");
        }

        //nomes modificant l'objecte ja es fa
        allotjament.setPreu(allotjament.getPreu() * capacitat / allotjament.getCapacitat());
        allotjament.setCapacitat(capacitat);

        em.getTransaction().commit();
    }

}
