/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.gestors;

import eac3.model.Bungalow;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Diana
 */
public class GestorBungalow {

    private EntityManager em;

    public GestorBungalow(EntityManager em) {
        this.em = em;
    }

    /**
     * Consulta tots els bungalows
     *
     * @return la llista de bungalows
     */
    public List<Bungalow> consultaBungalows() {
        Query consultaBungalow = em.createNamedQuery("Bungalow.findAll");
        return consultaBungalow.getResultList();
    }

    /**
     * Consulta tots els bungalows segons si tenen cuina equipada o no
     *
     * @param cuinaEquipada true si tenen cuina equipada, false si no en tenen
     * @return llista de bungalows
     */
    public List<Bungalow> consultaBungalows(boolean cuinaEquipada) {
        Query consultaBungalow = em.createNamedQuery("Bungalow.findByKitchen");
        consultaBungalow.setParameter("cuina", cuinaEquipada);
        return consultaBungalow.getResultList();
    }

    /**
     * Actualitza la data de manteniment amb la data del paràmetre, de tots els
     * bungalows amb data de manteniment anterior a la donada
     *
     * @param dataManteniment data de manteniment
     *
     */
    public void actualitzaDataManteniment(LocalDate dataManteniment) throws GestorException {
            em.getTransaction().begin();

            Query updateQuery = em.createQuery(
                    "UPDATE Bungalow b SET b.dataManteniment = :dataManteniment " +
                            "WHERE b.dataManteniment < :dataLimit"
            );
            updateQuery.setParameter("dataManteniment", dataManteniment);
            updateQuery.setParameter("dataLimit", dataManteniment);


            updateQuery.executeUpdate();

            em.getTransaction().commit();
    }

}
