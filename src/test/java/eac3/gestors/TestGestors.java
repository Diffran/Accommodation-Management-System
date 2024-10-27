/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package eac3.gestors;

import eac3.model.Habitacio;
import eac3.model.Bungalow;
import eac3.model.Establiment;
import eac3.model.Allotjament;
import eac3.model.Parcela;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author joan
 */
@TestMethodOrder(OrderAnnotation.class)
public class TestGestors {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa2425s1");

    private static List<Establiment> llistaEstabliments;
    private static List<Allotjament> llistaAllotjaments;

    public TestGestors() {
    }

    @BeforeAll
    public static void setUpClass() {
        incialitzaBD();

        llistaEstabliments = creaLlistaEstabliments();
        llistaAllotjaments = creaLlistaAllotjaments();

        for (Allotjament allotjament : llistaAllotjaments) {
            allotjament.getEstabliment().getAllotjaments().add(allotjament);

        }
    }

    private static void incialitzaBD() {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.createQuery("DELETE FROM Establiment").executeUpdate();

        em.createQuery("DELETE FROM Allotjament").executeUpdate();

        em.getTransaction().commit();

        em.close();

    }

    private static List<Establiment> creaLlistaEstabliments() {

        // Instanciem els establiments
        List<Establiment> establiments = new ArrayList<>();

        // Hotels
        establiments.add(new Establiment("H001", "Hotel Costa Brava", "Hotel amb vista al mar", 4, "Espanya", "Platja d'Aro", 17250, "Carrer del Mar", 10, "972 123 456", "info@hotelcostabrava.com", "www.hotelcostabrava.com", List.of("Piscina", "WiFi", "Restaurant")));
        establiments.add(new Establiment("H002", "Hotel Montserrat", "Hotel en la muntanya", 3, "Espanya", "Monistrol de Montserrat", 8293, "Carrer de la Muntanya", 5, "938 765 432", "info@hotelmontserrat.com", "www.hotelmontserrat.com", List.of("Senderisme", "Restaurant", "Spa")));
        establiments.add(new Establiment("H003", "Hotel Barcelona", "Hotel al centre de Barcelona", 5, "Espanya", "Barcelona", 8002, "Carrer de la Rambla", 15, "933 123 456", "info@hotelbarcelona.com", "www.hotelbarcelona.com", List.of("Gimnàs", "Restaurant", "Wifi gratuït")));
        establiments.add(new Establiment("H004", "Hotel Sant Cugat", "Hotel familiar", 4, "Espanya", "Sant Cugat del Vallès", 8172, "Carrer de la Verda", 12, "936 765 432", "info@hotelsantcugat.com", "www.hotelsantcugat.com", List.of("Terrassa", "Activitats infantils", "WiFi gratuït")));
        establiments.add(new Establiment("H005", "Hotel Tarragona", "Hotel amb piscina", 4, "Espanya", "Tarragona", 43001, "Carrer de la Piscina", 7, "977 123 456", "info@hoteltarragona.com", "www.hoteltarragona.com", List.of("Piscina", "Spa", "Restaurant")));
        establiments.add(new Establiment("H006", "Hotel Sitges", "Hotel a la platja", 5, "Espanya", "Sitges", 8870, "Carrer de la Platja", 4, "938 987 654", "info@hotelsitges.com", "www.hotelsitges.com", List.of("Gimnàs", "Spa", "Vistes al mar")));

        // Càmpings
        establiments.add(new Establiment("C001", "Camping la Sirena", "Camping familiar", 2, "Espanya", "Cerdanyola del Vallès", 8290, "Carrer del Camping", 1, "935 123 456", "info@campingsirena.com", "www.campingsirena.com", List.of("Jardí", "Bar", "Activitats infantils")));
        establiments.add(new Establiment("C002", "Camping La Floresta", "Camping en un bosc", 1, "Espanya", "Banyoles", 17820, "Carrer del Bosc", 8, "972 654 321", "info@campinglafloresta.com", "www.campinglafloresta.com", List.of("Barbacoa", "Excursions", "Animació")));
        establiments.add(new Establiment("C003", "Camping El Sol", "Camping a la platja", 2, "Espanya", "Cambrils", 43850, "Carrer de la Platja", 6, "977 123 456", "info@campingelsol.com", "www.campingelsol.com", List.of("Piscina", "Activitats d'aigua", "Restaurant")));
        establiments.add(new Establiment("C004", "Camping La Mer", "Camping al costat del mar", 2, "Espanya", "L'Ametlla de Mar", 43860, "Carrer del Mar", 9, "977 765 432", "info@campinglamer.com", "www.campinglamer.com", List.of("Platges", "Activitats marines", "Animació infantil")));
        establiments.add(new Establiment("C005", "Camping Montsant", "Camping de muntanya", 1, "Espanya", "Cornudella de Montsant", 43361, "Carrer de les Muntanyes", 2, "977 456 789", "info@campingmontsant.com", "www.campingmontsant.com", List.of("Senderisme", "Ciclisme", "Barbacoa")));
        establiments.add(new Establiment("C006", "Camping PortAventura", "Camping temàtic", 1, "Espanya", "Salou", 43840, "Carrer PortAventura", 11, "977 123 987", "info@campingportaventura.com", "www.campingportaventura.com", List.of("Parc d'atraccions", "Piscina", "Restaurant")));

        return establiments;
    }

    private static List<Allotjament> creaLlistaAllotjaments() {
        // Instanciem allotjaments
        List<Allotjament> allotjaments = new ArrayList<>();

        // Habitacions
        allotjaments.add(new Habitacio("H001H01", 1, 100.0, 20.0, 2, false, llistaEstabliments.get(0), "Standard", 2, true));
        allotjaments.add(new Habitacio("H001H02", 2, 110.0, 20.0, 2, false, llistaEstabliments.get(0), "Deluxe", 2, false));
        allotjaments.add(new Habitacio("H002H01", 1, 90.0, 15.0, 1, false, llistaEstabliments.get(1), "Executiva", 1, true));
        allotjaments.add(new Habitacio("H002H02", 2, 95.0, 15.0, 1, false, llistaEstabliments.get(1), "Standard", 1, false));
        allotjaments.add(new Habitacio("H003H01", 1, 120.0, 20.0, 2, false, llistaEstabliments.get(2), "Suite", 2, true));
        allotjaments.add(new Habitacio("H003H02", 2, 150.0, 25.0, 3, false, llistaEstabliments.get(2), "Aparthotel", 3, true));

        allotjaments.add(new Habitacio("H004H01", 1, 105.0, 22.0, 2, false, llistaEstabliments.get(3), "Standard", 2, true));
        allotjaments.add(new Habitacio("H004H02", 2, 115.0, 22.0, 2, false, llistaEstabliments.get(3), "Deluxe", 2, false));
        allotjaments.add(new Habitacio("H005H01", 1, 130.0, 24.0, 2, false, llistaEstabliments.get(4), "Executiva", 2, true));
        allotjaments.add(new Habitacio("H005H02", 2, 140.0, 24.0, 2, false, llistaEstabliments.get(4), "Suite", 2, false));
        allotjaments.add(new Habitacio("H006H01", 1, 160.0, 25.0, 2, false, llistaEstabliments.get(5), "Suite", 2, true));
        allotjaments.add(new Habitacio("H006H02", 2, 170.0, 25.0, 3, false, llistaEstabliments.get(5), "Aparthotel", 3, true));

        allotjaments.add(new Habitacio("H001H03", 3, 120.0, 20.0, 2, false, llistaEstabliments.get(0), "Deluxe", 2, true));
        allotjaments.add(new Habitacio("H002H03", 3, 85.0, 15.0, 1, false, llistaEstabliments.get(1), "Standard", 1, false));
        allotjaments.add(new Habitacio("H003H03", 3, 135.0, 20.0, 2, false, llistaEstabliments.get(2), "Executiva", 2, true));
        allotjaments.add(new Habitacio("H004H03", 3, 145.0, 22.0, 2, false, llistaEstabliments.get(3), "Suite", 2, false));
        allotjaments.add(new Habitacio("H005H03", 3, 155.0, 24.0, 2, false, llistaEstabliments.get(4), "Aparthotel", 3, true));
        allotjaments.add(new Habitacio("H006H03", 3, 165.0, 25.0, 2, false, llistaEstabliments.get(5), "Standard", 2, true));

        // Bungalows
        allotjaments.add(new Bungalow("C001B01", 1, 75.0, 30.0, 4, false, llistaEstabliments.get(6), true, LocalDate.now().minusMonths(1)));
        allotjaments.add(new Bungalow("C001B02", 2, 80.0, 30.0, 4, false, llistaEstabliments.get(6), false, LocalDate.now().minusMonths(2)));
        allotjaments.add(new Bungalow("C002B01", 1, 70.0, 30.0, 4, false, llistaEstabliments.get(7), true, LocalDate.now().minusMonths(1)));
        allotjaments.add(new Bungalow("C002B02", 2, 75.0, 30.0, 4, false, llistaEstabliments.get(7), false, LocalDate.now().minusMonths(2)));
        allotjaments.add(new Bungalow("C003B01", 1, 100.0, 30.0, 4, false, llistaEstabliments.get(8), true, LocalDate.now().minusMonths(1)));
        allotjaments.add(new Bungalow("C003B02", 2, 105.0, 30.0, 4, false, llistaEstabliments.get(8), true, LocalDate.now().minusMonths(2)));

        allotjaments.add(new Bungalow("C004B01", 1, 110.0, 35.0, 4, false, llistaEstabliments.get(9), true, LocalDate.now().minusMonths(1)));
        allotjaments.add(new Bungalow("C004B02", 2, 115.0, 35.0, 4, false, llistaEstabliments.get(9), true, LocalDate.now().minusMonths(2)));
        allotjaments.add(new Bungalow("C005B01", 1, 120.0, 30.0, 4, false, llistaEstabliments.get(10), true, LocalDate.now().minusMonths(1)));
        allotjaments.add(new Bungalow("C005B02", 2, 125.0, 30.0, 4, false, llistaEstabliments.get(10), true, LocalDate.now().minusMonths(2)));
        allotjaments.add(new Bungalow("C006B01", 1, 130.0, 35.0, 4, false, llistaEstabliments.get(11), true, LocalDate.now().minusMonths(1)));
        allotjaments.add(new Bungalow("C006B02", 2, 135.0, 35.0, 4, false, llistaEstabliments.get(11), true, LocalDate.now().minusMonths(2)));

        // Parceles
        allotjaments.add(new Parcela("C001P01", 1, 75.0, 25.0, 6, false, llistaEstabliments.get(6), true, false, false));
        allotjaments.add(new Parcela("C001P02", 2, 80.0, 20.0, 6, false, llistaEstabliments.get(6), false, true, true));
        allotjaments.add(new Parcela("C002P01", 1, 70.0, 15.0, 6, false, llistaEstabliments.get(7), true, false, false));
        allotjaments.add(new Parcela("C002P02", 2, 75.0, 30.0, 6, false, llistaEstabliments.get(7), false, true, true));
        allotjaments.add(new Parcela("C003P01", 1, 80.0, 31.5, 6, false, llistaEstabliments.get(8), true, false, false));
        allotjaments.add(new Parcela("C003P02", 2, 85.0, 22.0, 6, false, llistaEstabliments.get(8), false, true, true));

        allotjaments.add(new Parcela("C004P01", 1, 90.0, 25.0, 6, false, llistaEstabliments.get(9), true, false, false));
        allotjaments.add(new Parcela("C004P02", 2, 95.0, 25.0, 6, false, llistaEstabliments.get(9), false, true, true));
        allotjaments.add(new Parcela("C005P01", 1, 100.0, 22.0, 6, false, llistaEstabliments.get(10), true, false, false));
        allotjaments.add(new Parcela("C005P02", 2, 105.0, 40.0, 6, false, llistaEstabliments.get(10), false, true, true));
        allotjaments.add(new Parcela("C006P01", 1, 110.0, 20.0, 6, false, llistaEstabliments.get(11), true, false, false));
        allotjaments.add(new Parcela("C006P02", 2, 115.0, 20.0, 6, false, llistaEstabliments.get(11), false, true, true));

        return allotjaments;
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @Order(1)
    public void testAfegirEstabliments() throws GestorException {
        EntityManager em = emf.createEntityManager();
        GestorEstabliment gestorEstabliment = new GestorEstabliment(em);

        for (Establiment establiment : llistaEstabliments) {
            gestorEstabliment.inserir(establiment);
        }

        assertThrows(GestorException.class, () -> {
            gestorEstabliment.inserir(llistaEstabliments.get(0));
        });

        em.close();

    }

    @ParameterizedTest
    @ValueSource(strings = {"Cambrils", "Salou", "Tarragona", "Manresa", "Sitges"})
    @Order(2)
    public void testConsultaEstabliments(String ciutat) {
        EntityManager em = emf.createEntityManager();
        GestorEstabliment gestorEstabliment = new GestorEstabliment(em);

        List<Establiment> result = gestorEstabliment.consultaEstabliments(ciutat);
        em.close();

        List<Establiment> expResult = llistaEstabliments.stream().sorted(Comparator.comparing(Establiment::getCodi)).filter(e -> e.getCiutat().equals(ciutat)).toList();
        result.sort(Comparator.comparing(Establiment::getCodi));

        assertEquals(expResult, result);
    }

    @Test
    @Order(3)
    void testConsultaAllotjaments() {
        EntityManager em = emf.createEntityManager();
        GestorAllotjament gestorAllotjament = new GestorAllotjament(em);

        List<Allotjament> result = gestorAllotjament.consultaAllotjaments();
        List<Allotjament> expResult = llistaAllotjaments
                .stream().sorted(Comparator.comparing(Allotjament::getCodi)).toList();

        result.sort(Comparator.comparing(Allotjament::getCodi));

        assertEquals(expResult, result);

        assertThrows(GestorException.class, () -> {
            gestorAllotjament.consultaAllotjamentsLliures("---");
        });

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})
    @Order(4)
    void testConsultaAllotjamentsLliures(int indexEstabliment) throws GestorException {
        EntityManager em = emf.createEntityManager();
        GestorAllotjament gestorAllotjament = new GestorAllotjament(em);

        List<Allotjament> result = gestorAllotjament.consultaAllotjamentsLliures(llistaEstabliments.get(indexEstabliment).getCodi());
        List<Allotjament> expResult = llistaEstabliments.get(indexEstabliment).getAllotjaments()
                .stream().sorted(Comparator.comparing(Allotjament::getCodi)).filter(a -> !a.isOcupat()).toList();

        result.sort(Comparator.comparing(Allotjament::getCodi));

        assertEquals(expResult, result);

        assertThrows(GestorException.class, () -> {
            gestorAllotjament.consultaAllotjamentsLliures("---");
        });

    }

    @Test
    @Order(5)
    void testConsultaHabitacions() {
        EntityManager em = emf.createEntityManager();
        GestorHabitacio gestorHabitacio = new GestorHabitacio(em);

        List<Habitacio> expResult = llistaAllotjaments.stream().sorted(Comparator.comparing(Allotjament::getCodi))
                .filter(m -> m instanceof Habitacio)
                .map(m -> (Habitacio) m)
                .toList();
        List<Habitacio> result = gestorHabitacio.consultaHabitacions();

        result.sort(Comparator.comparing(Allotjament::getCodi));

        assertEquals(expResult, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    @Order(6)
    void testConsultaHabitacions(int capacitat) {
        EntityManager em = emf.createEntityManager();
        GestorHabitacio gestorHabitacio = new GestorHabitacio(em);

        List<Habitacio> expResult = llistaAllotjaments.stream().sorted(Comparator.comparing(Allotjament::getCodi))
                .filter(m -> m instanceof Habitacio)
                .map(m -> (Habitacio) m).filter(m -> m.getCapacitat() == capacitat).toList();
        List<Habitacio> result = gestorHabitacio.consultaHabitacions(capacitat);

        result.sort(Comparator.comparing(Allotjament::getCodi));

        assertEquals(expResult, result);
    }

    @Test
    @Order(7)
    void testConsultaParceles() {
        EntityManager em = emf.createEntityManager();
        GestorParcela gestorParcela = new GestorParcela(em);

        List<Parcela> expResult = llistaAllotjaments.stream().sorted(Comparator.comparing(Allotjament::getCodi)).filter(m -> m instanceof Parcela).map(m -> (Parcela) m).toList();
        List<Parcela> result = gestorParcela.consultaParceles();

        result.sort(Comparator.comparing(Allotjament::getCodi));

        assertEquals(expResult, result);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 50",
        "10, 30",
        "15, 25",
        "25, 40"
    })
    @Order(8)
    void testConsultaParcelesInterval(double minSup, double maxSup) {
        EntityManager em = emf.createEntityManager();
        GestorParcela gestorParcela = new GestorParcela(em);

        List<Parcela> expResult = llistaAllotjaments.stream().sorted(Comparator.comparing(Allotjament::getCodi))
                .filter(p -> p instanceof Parcela).map(m -> (Parcela) m)
                .filter(p -> p.getSuperficie() >= minSup && p.getSuperficie() <= maxSup).toList();
        List<Parcela> result = gestorParcela.consultaParceles(minSup, maxSup);

        result.sort(Comparator.comparing(Allotjament::getCodi));

        assertEquals(expResult, result);
    }

    /*
    @Test
    @Order(9)
    void testConsultaBungalows() {
        EntityManager em = emf.createEntityManager();
        GestorBungalow gestorBungalow = new GestorBungalow(em);

        List<Bungalow> expResult = llistaAllotjaments.stream().sorted(Comparator.comparing(Allotjament::getCodi))
                .filter(b -> b instanceof Bungalow).map(m -> (Bungalow) m)
                .toList();
        List<Bungalow> result = gestorBungalow.consultaBungalows();

        result.sort(Comparator.comparing(Allotjament::getCodi));

        assertEquals(expResult, result);
    }
     */
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    @Order(9)
    void testConsultaBungalows(boolean cuinaEquipada) {
        EntityManager em = emf.createEntityManager();
        GestorBungalow gestorBungalow = new GestorBungalow(em);

        List<Bungalow> expResult = llistaAllotjaments.stream().sorted(Comparator.comparing(Allotjament::getCodi))
                .filter(m -> m instanceof Bungalow)
                .map(b -> (Bungalow) b)
                .filter(m -> m.isCuinaEquipada() == cuinaEquipada)
                .toList();
        List<Bungalow> result = gestorBungalow.consultaBungalows(cuinaEquipada);

        result.sort(Comparator.comparing(Allotjament::getCodi));

        assertEquals(expResult, result);
    }

    @ParameterizedTest
    @CsvSource(
            {
                "3, 2",
                "13, 3",
                "23, 2",
                "33, 1"
            })
    @Order(10)
    void testActualitzarCapacitat(int indexAllotjament, int capacitat) throws GestorException {
        EntityManager em = emf.createEntityManager();
        GestorAllotjament gestorAllotjament = new GestorAllotjament(em);

        Allotjament a = llistaAllotjaments.get(indexAllotjament);
        gestorAllotjament.actualitzarCapacitat(a.getCodi(), capacitat);
        a.setPreu(a.getPreu() * capacitat / a.getCapacitat());
        a.setCapacitat(capacitat);

        List<Allotjament> result = gestorAllotjament.consultaAllotjaments();
        List<Allotjament> expResult = llistaAllotjaments;
        result.sort(Comparator.comparing(Allotjament::getCodi));
        expResult.sort(Comparator.comparing(Allotjament::getCodi));

        assertEquals(expResult, result);
        assertThrows(GestorException.class, () -> {
            gestorAllotjament.actualitzarCapacitat("---", capacitat);
        });
        em.close();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    @Order(11)
    void testActualitzaDataManteniment(int months) throws GestorException {
        EntityManager em = emf.createEntityManager();
        GestorBungalow gestorBungalow = new GestorBungalow(em);
        GestorAllotjament gestorAllotjament = new GestorAllotjament(em);

        LocalDate dataManteniment = LocalDate.now().minusMonths(months);
        gestorBungalow.actualitzaDataManteniment(dataManteniment);
        List<Allotjament> result = gestorAllotjament.consultaAllotjaments();
        List<Allotjament> expResult = llistaAllotjaments;

        expResult.stream().filter(a -> a.getClass() == Bungalow.class && dataManteniment.isAfter(((Bungalow) a).getDataManteniment()))
                .forEach(a -> ((Bungalow) a).setDataManteniment(LocalDate.now()));

        result.sort(Comparator.comparing(Allotjament::getCodi));
        expResult.sort(Comparator.comparing(Allotjament::getCodi));

        assertEquals(expResult, result);

        em.close();

    }

    @ParameterizedTest
    @ValueSource(doubles = {30, 25, 24})
    @Order(12)
    void testDobleATriple(double superficie) {
        EntityManager em = emf.createEntityManager();
        GestorHabitacio gestorHabitacio = new GestorHabitacio(em);
        GestorAllotjament gestorAllotjament = new GestorAllotjament(em);

        gestorHabitacio.dobleATriple(superficie);

        List<Allotjament> result = gestorAllotjament.consultaAllotjaments();
        List<Allotjament> expResult = llistaAllotjaments;
        expResult.stream().filter(a -> a.getClass() == Habitacio.class && a.getCapacitat() == 2 && a.getSuperficie() >= superficie)
                .forEach(a -> {
                    a.setCapacitat(3);
                    a.setPreu(a.getPreu() * 3 / 2);
                });

        result.sort(Comparator.comparing(Allotjament::getCodi));

        assertEquals(expResult, result);
        em.close();

    }

    @ParameterizedTest
    @CsvSource({
        "H004,         3",
        "C002,         1.5",
        "C004,         10"
    })
    @Order(13)
    void testDescompteSenseOmbra(String codiEstabliment, double percentatge) throws GestorException {
        EntityManager em = emf.createEntityManager();
        GestorEstabliment gestorEstabliment = new GestorEstabliment(em);
        GestorAllotjament gestorAllotjament = new GestorAllotjament(em);

        llistaAllotjaments.stream()
                .filter(a -> a.getEstabliment().getCodi().equals(codiEstabliment)
                && a.getClass() == Parcela.class
                && !((Parcela) a).isOmbra()
                )
                .forEach(a -> {
                    Parcela p = (Parcela) a;
                    p.setPreu(p.getPreu() * (1 + percentatge / 100.0));
                });

        List<Allotjament> expResult = llistaAllotjaments;
        gestorEstabliment.descompteSenseOmbra(codiEstabliment, percentatge);
        List<Allotjament> result = gestorAllotjament.consultaAllotjaments();
        result.sort(Comparator.comparing(Allotjament::getCodi));

        assertEquals(expResult, result);

        assertThrows(GestorException.class, () -> {
            gestorEstabliment.descompteSenseOmbra("---", percentatge);
        });

        em.close();
    }

    @Test
    @Order(14)
    void testInserirAllotjament() throws GestorException {
        EntityManager em = emf.createEntityManager();
        GestorAllotjament gestorAllotjament = new GestorAllotjament(em);

        Allotjament a = new Parcela("C006P03", 2, 115.0, 20.0, 6, false, llistaEstabliments.get(11), false, true, true);
        llistaEstabliments.get(11).getAllotjaments().add(a);
        llistaAllotjaments.add(a);
        gestorAllotjament.inserir(a);

        assertThrows(GestorException.class, () -> {
            gestorAllotjament.inserir(a);
        });

        em.close();
    }

    @ParameterizedTest
    @ValueSource(ints = {40, 35, 30, 20, 10, 5})
    @Order(15)
    void testEliminarAllotjament(int indexAllotjament) throws GestorException {
        EntityManager em = emf.createEntityManager();
        GestorAllotjament gestorAllotjament = new GestorAllotjament(em);

        String codiAllotjament = llistaAllotjaments.get(indexAllotjament).getCodi();
        gestorAllotjament.eliminar(codiAllotjament);
        llistaAllotjaments.remove(indexAllotjament);

        llistaAllotjaments.sort(Comparator.comparing(Allotjament::getCodi));
        List<Allotjament> result = gestorAllotjament.consultaAllotjaments();
        result.sort(Comparator.comparing(Allotjament::getCodi));
        List<Allotjament> expResult = llistaAllotjaments;

        assertEquals(expResult, result);

        assertThrows(GestorException.class, () -> {
            gestorAllotjament.eliminar(codiAllotjament);
        });
        em.close();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 7, 8})
    @Order(16)
    void tesltEliminarEstabliment(int index) throws GestorException {
        EntityManager em = emf.createEntityManager();
        GestorEstabliment gestorEstabliment = new GestorEstabliment(em);
        GestorAllotjament gestorAllotjament = new GestorAllotjament(em);

        Establiment e = llistaEstabliments.get(index);
        gestorEstabliment.eliminar(e.getCodi());
        llistaAllotjaments.removeIf(a -> a.getEstabliment().getCodi() == e.getCodi());

        List<Allotjament> result = gestorAllotjament.consultaAllotjaments();
        result.sort(Comparator.comparing(Allotjament::getCodi));

        List<Allotjament> expResult = llistaAllotjaments;



        assertEquals(expResult, result);

        assertThrows(GestorException.class, () -> {
            gestorEstabliment.eliminar(e.getCodi());
        });
    }

}
