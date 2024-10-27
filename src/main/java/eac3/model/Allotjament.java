package eac3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="allotjament")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@NamedQueries(
//        @NamedQuery(name="Allotjament.findAll", query="SELECT a FROM Allotjament a")
//    )
public abstract class Allotjament implements Serializable {
    @Id
    private String codi;
    private int numero;
    private double preu;
    private double superficie;
    private int capacitat;
    private boolean ocupat;
    @ManyToOne
    @JoinColumn(name="establiment_codi")
    private Establiment establiment;

    public Allotjament() {

    }

    public Allotjament(String codi, int numero, double preu, double superficie, int capacitat, boolean ocupat, Establiment establiment) {
        this.codi = codi;
        this.numero = numero;
        this.preu = preu;
        this.superficie = superficie;
        this.capacitat = capacitat;
        this.ocupat = ocupat;
        this.establiment = establiment;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public int getCapacitat() {
        return capacitat;
    }

    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
    }

    public boolean isOcupat() {
        return ocupat;
    }

    public void setOcupat(boolean ocupat) {
        this.ocupat = ocupat;
    }

    public Establiment getEstabliment() {
        return establiment;
    }

    public void setEstabliment(Establiment establiment) {
        this.establiment = establiment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.codi);
        hash = 37 * hash + this.numero;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.preu) ^ (Double.doubleToLongBits(this.preu) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.superficie) ^ (Double.doubleToLongBits(this.superficie) >>> 32));
        hash = 37 * hash + this.capacitat;
        hash = 37 * hash + (this.ocupat ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.establiment);
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
        final Allotjament other = (Allotjament) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (Double.doubleToLongBits(this.preu) != Double.doubleToLongBits(other.preu)) {
            return false;
        }
        if (Double.doubleToLongBits(this.superficie) != Double.doubleToLongBits(other.superficie)) {
            return false;
        }
        if (this.capacitat != other.capacitat) {
            return false;
        }
        if (this.ocupat != other.ocupat) {
            return false;
        }
        if (!Objects.equals(this.codi, other.codi)) {
            return false;
        }
        return Objects.equals(this.establiment.getCodi(), other.establiment.getCodi());
    }

}
