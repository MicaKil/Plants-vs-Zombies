//Lo cree solo para testear que lo que estoy haciendo tiene sentido

package plantsVsZombies;

public class MainTest {
    public static void main(String[] args) {
        Tablero T = new Tablero();
        T.mostrarTablero();
        T.crearZombie();
        T.mostrarTablero();
        T.avanzarZombies();
        T.mostrarTablero();
        T.crearZombie();
        T.mostrarTablero();
        T.avanzarZombies();
        T.mostrarTablero();
        T.crearZombie();
        T.crearZombie();
        T.crearZombie();
        T.mostrarTablero();
    }

}
