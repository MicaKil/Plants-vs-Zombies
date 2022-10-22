//Lo cree solo para testear que lo que estoy haciendo tiene sentido

package plantsVsZombies;

public class MainTest {
    public static void main(String[] args) {
        Zombie zombie = new Abanderado(9);
        System.out.println(zombie.getX());
        Tablero T = new Tablero();
        T.mostrarTablero();
        T.crearZombie();
        T.mostrarTableroZ();
    }

}
