package eac3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="establiment")
public class Establiment implements Serializable {

    @Id
    private String codi;
    @Column(length=100)
    private String nom;
    private String descripcio;
    private int estrelles;
    @Column(length = 50)
    private String pais;
    @Column(length = 50)
    private String ciutat;
    @Column(name="codi_postal")
    private int codiPostal;
    private String carrer;
    private int numero;
    private String telefon;
    private String email;
    private String web;
    @ElementCollection
    private List<String> serveis;
    @OneToMany(mappedBy  = "establiment", cascade = CascadeType.ALL)
    private List<Allotjament> allotjaments = new ArrayList<>();

    public Establiment() {
    }

    public Establiment(String codi, String nom, String descripcio, int estrelles, String pais, String ciutat, int codiPostal, String carrer, int numero, String telefon, String email, String web, List<String> serveis) {
        this.codi = codi;
        this.nom = nom;
        this.descripcio = descripcio;
        this.estrelles = estrelles;
        this.pais = pais;
        this.ciutat = ciutat;
        this.codiPostal = codiPostal;
        this.carrer = carrer;
        this.numero = numero;
        this.telefon = telefon;
        this.email = email;
        this.web = web;
        this.serveis = serveis;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getEstrelles() {
        return estrelles;
    }

    public void setEstrelles(int estrelles) {
        this.estrelles = estrelles;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public int getCodiPostal() {
        return codiPostal;
    }

    public void setCodiPostal(int codiPostal) {
        this.codiPostal = codiPostal;
    }

    public String getCarrer() {
        return carrer;
    }

    public void setCarrer(String carrer) {
        this.carrer = carrer;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public List<String> getServeis() {
        return serveis;
    }

    public void setServeis(List<String> serveis) {
        this.serveis = serveis;
    }

    public List<Allotjament> getAllotjaments() {
        return allotjaments;
    }

    public void setAllotjaments(List<Allotjament> allotjaments) {
        this.allotjaments = allotjaments;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.codi);
        hash = 53 * hash + Objects.hashCode(this.nom);
        hash = 53 * hash + Objects.hashCode(this.descripcio);
        hash = 53 * hash + this.estrelles;
        hash = 53 * hash + Objects.hashCode(this.pais);
        hash = 53 * hash + Objects.hashCode(this.ciutat);
        hash = 53 * hash + this.codiPostal;
        hash = 53 * hash + Objects.hashCode(this.carrer);
        hash = 53 * hash + this.numero;
        hash = 53 * hash + Objects.hashCode(this.telefon);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.web);
        hash = 53 * hash + Objects.hashCode(this.serveis);
        hash = 53 * hash + Objects.hashCode(this.allotjaments);
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
        final Establiment other = (Establiment) obj;
        if (this.estrelles != other.estrelles) {
            return false;
        }
        if (this.codiPostal != other.codiPostal) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.codi, other.codi)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.descripcio, other.descripcio)) {
            return false;
        }
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        if (!Objects.equals(this.ciutat, other.ciutat)) {
            return false;
        }
        if (!Objects.equals(this.carrer, other.carrer)) {
            return false;
        }
        if (!Objects.equals(this.telefon, other.telefon)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.web, other.web)) {
            return false;
        }
        if (!Objects.equals(this.serveis, other.serveis)) {
            return false;
        }
        return Objects.equals(this.allotjaments, other.allotjaments);
    }
}
