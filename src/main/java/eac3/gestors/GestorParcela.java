/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.gestors;

import eac3.model.Parcela;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Diana
 */
public class GestorParcela {

    private EntityManager em;

    public GestorParcela(EntityManager em) {
        this.em = em;
    }

    /**
     * Consulta de totes les parcel·les
     *
     * @return la llista de parcel·les
     */
    public List<Parcela> consultaParceles() {
        Query consultaParcela = em.createNamedQuery("Parcela.findAll");
        return consultaParcela.getResultList();
    }

    /**
     * Consulta de totes les parcel·les en un interval de superfície
     *
     * @param minSup superfície mínima de la parcel·la
     * @param maxSup superfície màxima de la parcel·la
     * @return la llista de parcel·les
     */
    public List<Parcela> consultaParceles(double minSup, double maxSup) {
        Query consultaParcela = em.createNamedQuery("Parcela.findBySurface");
        consultaParcela.setParameter("min",minSup);
        consultaParcela.setParameter("max",maxSup);
        return consultaParcela.getResultList();
    }

}
