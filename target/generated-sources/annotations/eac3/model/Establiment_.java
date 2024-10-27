package eac3.model;

import eac3.model.Allotjament;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-26T00:33:08", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Establiment.class)
public class Establiment_ { 

    public static volatile SingularAttribute<Establiment, Integer> estrelles;
    public static volatile SingularAttribute<Establiment, Integer> numero;
    public static volatile SingularAttribute<Establiment, String> descripcio;
    public static volatile SingularAttribute<Establiment, String> nom;
    public static volatile SingularAttribute<Establiment, String> pais;
    public static volatile SingularAttribute<Establiment, Integer> codiPostal;
    public static volatile SingularAttribute<Establiment, String> web;
    public static volatile SingularAttribute<Establiment, String> ciutat;
    public static volatile SingularAttribute<Establiment, String> telefon;
    public static volatile SingularAttribute<Establiment, String> carrer;
    public static volatile ListAttribute<Establiment, String> serveis;
    public static volatile ListAttribute<Establiment, Allotjament> allotjaments;
    public static volatile SingularAttribute<Establiment, String> email;
    public static volatile SingularAttribute<Establiment, String> codi;

}