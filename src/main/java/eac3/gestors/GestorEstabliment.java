/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.gestors;

import eac3.model.Allotjament;
import eac3.model.Establiment;
import eac3.model.Parcela;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Diana
 */
public class GestorEstabliment {

    private EntityManager em;

    GestorEstabliment(EntityManager em) {
        this.em = em;
    }

    /**
     * Insereix un nou establiment a la base de dades
     *
     * @param establiment l'establiment a afegir
     * @throws GestorException si l'establiment ja existeix
     */
    public void inserir(Establiment establiment) throws GestorException {
        //mira si existeix
        if(em.find(Establiment.class, establiment.getCodi()) != null){
            throw new GestorException("l'establiment amb codi: "+ establiment.getCodi() + " ja existeix");
        }
        //fa la transaccio
        em.getTransaction().begin();

        try{
            em.persist(establiment);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            throw new GestorException("ERROR AL INSERIR L'ESTABLIMENT" + e.getMessage());
        }

    }

    /**
     * Elimina un establiment i tots els seus allotjaments 
	 * de la base de dades
     *
     * @param codi el codi de l'establiment
     * @throws GestorException si l'establiment no existeix
     */
    public void eliminar(String codi) throws GestorException {
        Establiment establimentAEliminar = em.find(Establiment.class, codi);

        if(establimentAEliminar == null){
            throw new GestorException("l'establiment amb codi: "+ codi+ " no existeix");
        }
        em.getTransaction().begin();

        try{
            em.remove(establimentAEliminar);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            throw new GestorException("ERROR AL ElIMINAR L'ESTABLIMENT" + e.getMessage());
        }

    }

    /**
     * Obtenir tots els establiments d'una ciutat
     *
     * @param ciutat el nom de la ciutat
     * @return la llista amb els establiments
     */
    public List<Establiment> consultaEstabliments(String ciutat) {
        List<Establiment> establiments;

        Query consultaEstabliments = em.createQuery("SELECT e FROM Establiment e WHERE e.ciutat= :ciutat");
        consultaEstabliments.setParameter("ciutat",ciutat);
        establiments = consultaEstabliments.getResultList();

        return establiments;
    }

    /**
     * Aplica un percentatge de descompte a totes les parcel·les sense ombra
     * d'un establiment
     *
     * @param codiEstabliment
     * @param descompte
     * @throws GestorException si l'establiment no existeix
     */
    void descompteSenseOmbra(String codiEstabliment, double descompte) throws GestorException {
        //mira si no existeix
        em.getTransaction().begin();

        Establiment establiment = em.find(Establiment.class, codiEstabliment);
        if( establiment == null){
            throw new GestorException("l'establiment amb codi: "+ codiEstabliment+ " no existeix");
        }

        for(Allotjament a : establiment.getAllotjaments()){
            if(a instanceof Parcela){
                Parcela p = (Parcela) a;
                if(!p.isOmbra()){
                    p.setPreu(p.getPreu() * (1 + descompte / 100.0));
                }
            }
        }

        em.getTransaction().commit();
    }

}
