/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.gestors;

import eac3.model.Allotjament;
import eac3.model.Habitacio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Diana
 */
public class GestorHabitacio {

    private EntityManager em;

    public GestorHabitacio(EntityManager em) {
        this.em = em;
    }

    /**
     * Obtenir totes les habitacions
     *
     * @return la llista d'habitacions
     */
    public List<Habitacio> consultaHabitacions() {
        Query consultaHabitacions = em.createNamedQuery("Habitacio.findAll");
        return consultaHabitacions.getResultList();
    }

    /**
     * Obtenir totes les habitacions amb una capacitat concreta
     *
     * @param capacitat la capacitat de l'habitació
     * @return la llista d'habitacions
     */
    public List<Habitacio> consultaHabitacions(int capacitat) {
        Query consultaHabitacions = em.createNamedQuery("Habitacio.findByCapacity");
        consultaHabitacions.setParameter("capacitat", capacitat);
        return consultaHabitacions.getResultList();
    }

    /**
     *  Passar a triple totes les habitacions dobles amb una superfície igual o superior a una de donada.
	 *  El seu preu augmenta de manera proporcional a l'agument de la capacitat.
	 *
     *  @param superficie superfície mínima a partir una habitació passa de doble a triple
     */
    void dobleATriple(double superficie) {
        List<Habitacio> habitacionsDobles;
        em.getTransaction().begin();


        Query habitacionsDoblesConsulta = em.createQuery("SELECT h FROM Habitacio h WHERE h.capacitat=2 AND h.superficie >= :superficie");
        habitacionsDoblesConsulta.setParameter("superficie",superficie);
        habitacionsDobles = habitacionsDoblesConsulta.getResultList();

        for(Habitacio h : habitacionsDobles){
            h.setCapacitat(3);
            h.setPreu(h.getPreu() * 3 / 2);
        }

        em.getTransaction().commit();
    }

}
