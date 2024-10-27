package eac3.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name="Habitacio.findAll", query="SELECT a FROM Habitacio a"),
        @NamedQuery(name="Habitacio.findByCapacity", query="SELECT a FROM Habitacio a WHERE a.capacitat= :capacitat")
})
public class Habitacio extends Allotjament {

    private String tipus;
    private int llits;
    private boolean minibar;

    public Habitacio() {

    }

    public Habitacio(String codi, int numero, double preu, double superficie, int capacitat, boolean ocupat, Establiment establiment, String tipus, int llits, boolean minibar) {
        super(codi, numero, preu, superficie, capacitat, ocupat, establiment);
        this.tipus = tipus;
        this.llits = llits;
        this.minibar = minibar;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public int getLlits() {
        return llits;
    }

    public void setLlits(int llits) {
        this.llits = llits;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.tipus);
        hash = 89 * hash + this.llits;
        hash = 89 * hash + (this.minibar ? 1 : 0);
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

        final Habitacio other = (Habitacio) obj;
        if (this.llits != other.llits) {
            return false;
        }
        if (this.minibar != other.minibar) {
            return false;
        }
        return Objects.equals(this.tipus, other.tipus);
    }

}
