import entity.Equipo;
import entity.Jugador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {

    static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    static final EntityManager entityManager = factory.createEntityManager();
    static final EntityTransaction transaction = entityManager.getTransaction();

    public static void main(String[] args) {
        // Creamos un equipo
        Equipo equipo = new Equipo();
        equipo.setNombre("Barça");
        equipo.setEstadio("Camp Nou");

        // Creamos un jugador
        Jugador jugador = new Jugador();
        jugador.setNombre("Lamine");
        jugador.setEstatura(168f);
        jugador.setPeso(68f);
        jugador.setIdEquipo(equipo);

        // Añadimos cada dato
        System.out.println("> Añadiendo datos...");
        addEquipo(equipo);
        addJugador(jugador);

        // Obtenemos cada dato
        System.out.println("\n> Datos añadidos:");
        System.out.println("EQUIPOS: " + getAllEquipos());
        System.out.println("JUGADORES: " + getAllJugadores());

        // Actualizamos cada dato
        System.out.println("\n> Actualizando datos...");
        equipo.setNombre("Real Madrid");
        equipo.setEstadio("Bernabéu");
        jugador.setNombre("Vinícius");
        jugador.setEstatura(170f);
        jugador.setPeso(70f);
        updateEquipo(equipo);
        updateJugador(jugador);

        // Mostramos cada dato actualizado
        System.out.println("\n> Datos actualizados:");
        System.out.println("EQUIPOS: " + getAllEquipos());
        System.out.println("JUGADORES: " + getAllJugadores());

        // Borramos los datos
        System.out.println("\n> Borrando datos...");
        deleteEquipo(equipo); // el jugador será borrado directamente al borrar el equipo
        System.out.println("\n> Datos borrados!");
        System.out.println("EQUIPOS... " + getAllEquipos());
        System.out.println("JUGADORES... " + getAllJugadores());

        // Cerramos los recursos correspondientes
        closeEntityManagerAndFactory();
    }

    // -------------------
    //     CRUD EQUIPO
    // -------------------
    private static Equipo getEquipoById(int id) {
        transaction.begin();
        Equipo equipo = entityManager.find(Equipo.class, id);
        transaction.commit();
        return equipo;
    }

    private static List<Equipo> getAllEquipos() {
        transaction.begin();
        List<Equipo> equipos = entityManager.createQuery("FROM Equipo", Equipo.class).getResultList();
        transaction.commit();
        return equipos;
    }

    private static void addEquipo(Equipo equipo) {
        transaction.begin();
        entityManager.persist(equipo);
        transaction.commit();
    }

    private static void updateEquipo(Equipo equipo) {
        transaction.begin();
        entityManager.merge(equipo);
        transaction.commit();
    }

    private static void deleteEquipo(Equipo equipo) {
        transaction.begin();
        entityManager.remove(equipo);
        transaction.commit();
    }

    // --------------------
    //     CRUD JUGADOR
    // --------------------
    private static Jugador getJugadorById(int id) {
        transaction.begin();
        Jugador jugador = entityManager.find(Jugador.class, id);
        transaction.commit();
        return jugador;
    }

    private static List<Jugador> getAllJugadores() {
        transaction.begin();
        List<Jugador> jugadores = entityManager.createQuery("FROM Jugador", Jugador.class).getResultList();
        transaction.commit();
        return jugadores;
    }

    private static void addJugador(Jugador jugador) {
        transaction.begin();
        entityManager.persist(jugador);
        transaction.commit();
    }

    private static void updateJugador(Jugador jugador) {
        transaction.begin();
        entityManager.merge(jugador);
        transaction.commit();
    }

    private static void deleteJugador(Jugador jugador) {
        transaction.begin();
        entityManager.remove(jugador);
        transaction.commit();
    }

    // Cierre de EntityManager y EntityManagerFactory
    private static void closeEntityManagerAndFactory() {
        entityManager.close();
        factory.close();
    }
}
