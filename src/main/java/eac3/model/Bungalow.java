/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Diana
 */
@Entity
@NamedQueries({
        @NamedQuery(name="Bungalow.findAll", query="SELECT b FROM Bungalow b"),
        @NamedQuery(name="Bungalow.findByKitchen", query="SELECT b FROM Bungalow b WHERE b.cuinaEquipada=:cuina")
})
public class Bungalow extends Allotjament {

    @Column(name="cuina_equipada")
    private boolean cuinaEquipada;
    @Column(name="data_manteniment")
    private LocalDate dataManteniment;

    public Bungalow() {
    }

    public Bungalow(String codi, int numero, double preu, double superficie, int capacitat, boolean ocupat, Establiment establiment, boolean cuinaEquipada, LocalDate dataManteniment) {
        super(codi, numero, preu, superficie, capacitat, ocupat, establiment);
        this.cuinaEquipada = cuinaEquipada;
        this.dataManteniment = dataManteniment;
    }

    public boolean isCuinaEquipada() {
        return cuinaEquipada;
    }

    public void setCuinaEquipada(boolean cuinaEquipada) {
        this.cuinaEquipada = cuinaEquipada;
    }

    public LocalDate getDataManteniment() {
        return dataManteniment;
    }

    public void setDataManteniment(LocalDate dataManteniment) {
        this.dataManteniment = dataManteniment;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.cuinaEquipada ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.dataManteniment);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        if (!super.equals(obj)) {
            return false;
        }

        final Bungalow other = (Bungalow) obj;
        if (this.cuinaEquipada != other.cuinaEquipada) {
            return false;
        }
        return Objects.equals(this.dataManteniment, other.dataManteniment);
    }

}
