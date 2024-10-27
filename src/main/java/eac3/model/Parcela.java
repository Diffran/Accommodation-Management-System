package eac3.model;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name="Parcela.findAll", query="SELECT p FROM Parcela p"),
        @NamedQuery(name="Parcela.findBySurface", query="SELECT p FROM Parcela p WHERE p.superficie BETWEEN :min AND :max")
})
public class Parcela extends Allotjament {

    private boolean ombra;
    private boolean electricitat;
    private boolean aigua;

    public Parcela() {
    }

    public Parcela(String codi, int numero, double preu, double superficie, int capacitat, boolean ocupat, Establiment establiment, boolean ombra, boolean electricitat, boolean aigua) {
        super(codi, numero, preu, superficie, capacitat, ocupat, establiment);
        this.ombra = ombra;
        this.electricitat = electricitat;
        this.aigua = aigua;
    }

    public boolean isOmbra() {
        return ombra;
    }

    public void setOmbra(boolean ombra) {
        this.ombra = ombra;
    }

    public boolean isElectricitat() {
        return electricitat;
    }

    public void setElectricitat(boolean electricitat) {
        this.electricitat = electricitat;
    }

    public boolean isAigua() {
        return aigua;
    }

    public void setAigua(boolean aigua) {
        this.aigua = aigua;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.ombra ? 1 : 0);
        hash = 41 * hash + (this.electricitat ? 1 : 0);
        hash = 41 * hash + (this.aigua ? 1 : 0);
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

        final Parcela other = (Parcela) obj;
        if (this.ombra != other.ombra) {
            return false;
        }
        if (this.electricitat != other.electricitat) {
            return false;
        }
        return this.aigua == other.aigua;
    }

}
