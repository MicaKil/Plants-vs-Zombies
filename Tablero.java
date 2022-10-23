
package plantasvszombies;
import java.util.Random;

public class Tablero {
    // no se si sería privado o protegido
    protected Planta[][] tableroP = new Planta[5][10]; // tablero en el que van a estar los objetos planta
    protected Zombie[][] tableroZ = new Zombie[5][10]; // tablero en el que van a estar los objetos zombies
    protected int soles=200;
    
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public void mostrarTablero() {
        System.out.println("Tablero del juego:");
        System.out.println();
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        System.out.println("   ____________________");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%d | ", i + 1);
            for (int j = 0; j < 10; j++) {
                if (this.tableroP[i][j] != null) {
                    System.out.print(ANSI_GREEN + this.tableroP[i][j].id + " " + ANSI_RESET);
                } else if (this.tableroZ[i][j] != null) {
                    System.out.print(ANSI_RED + this.tableroZ[i][j].id + " " + ANSI_RESET);
                } else
                    System.out.print("_ ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void crearZombie() {
        Random rand = new Random();
        int n = rand.nextInt(5); // genera un numero random desde 0 a 5 (sin tomar el 5)
        int fila;
        // en caso de que se genere un zombie en una casilla ocupada
        do {
            fila = rand.nextInt(5); // fila en la que va a aparecer el nuevo zombie
        } while (this.tableroZ[fila][9] != null);

        Zombie nuevoZombie = null;
        switch (n) {
            case 0 -> nuevoZombie = new Zombie(fila);
            case 1 -> nuevoZombie = new Caracubo(fila);
            case 2 -> nuevoZombie = new Caracono(fila);
            case 3 -> nuevoZombie = new Saltador(fila);
            case 4 -> nuevoZombie = new Lector(fila);
            default -> System.out.println("Error al crear zombie.");
        }
        this.tableroZ[fila][9] = nuevoZombie; //colocamos el zombie en la tabla
    }

    // caminan todos los zombies del tablero
    public void avanzarZombies() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.tableroZ[i][j] != null && this.tableroZ[i][j].avanzar) {
                    this.tableroZ[i][j].y -= 1;
                    this.tableroZ[i][j - 1] = this.tableroZ[i][j];
                    this.tableroZ[i][j] = null;
                }
            }
        }
    }
    public Tablero() {}

    public void plantar(Planta p){
        this.tableroP[p.x][p.y]=p;
    
    }
    public int getSoles() {
        return soles;
    }

    public void setSoles(int soles) {
        this.soles = soles;
    }
    
    
    
}