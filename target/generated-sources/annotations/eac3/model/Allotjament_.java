package eac3.model;

import eac3.model.Establiment;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-26T00:33:08", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Allotjament.class)
public abstract class Allotjament_ { 

    public static volatile SingularAttribute<Allotjament, Boolean> ocupat;
    public static volatile SingularAttribute<Allotjament, Integer> numero;
    public static volatile SingularAttribute<Allotjament, Double> superficie;
    public static volatile SingularAttribute<Allotjament, Double> preu;
    public static volatile SingularAttribute<Allotjament, Establiment> establiment;
    public static volatile SingularAttribute<Allotjament, String> codi;
    public static volatile SingularAttribute<Allotjament, Integer> capacitat;

}