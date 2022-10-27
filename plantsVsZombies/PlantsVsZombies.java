// Clase del juego PvsZ

package plantsVsZombies;

import java.util.Random;
import java.util.Scanner;

public class PlantsVsZombies {

    protected int soles = 200;
    protected int vidas = 3;
    protected int cantGirasoles=0;
    protected double cantMovimientos = 0; // es double/float porque sino las operaciones de / y % resultan en la operacion entera
    protected int totalZombies = 0;
    protected int totalPlantas = 0;
    public PlantsVsZombies() {}

    public void iniciar() {
        //Modelado del juego con el usuario participando
        Tablero tablero = new Tablero(); //tablero vacío
        tablero.mostrarTablero(this);
        // creamos el menu de juego
        Menu menu = new Menu();

        while (this.cantMovimientos < 51.0 && this.vidas > 0) {
            menu.mostrarMenu(this, tablero);
            //System.out.println("menu cant movimientos " + this.cantMovimientos);
            this.cantMovimientos ++; // suma uno al terminar de comprar

            // Zombies
            // ---------------------------------------------------

            // modificar generacion de zombies a no tan pseudo aleatorio
            System.out.println("**************************************************************************************");
            System.out.println("Los zombies están avanzando!!!");
            Random rand = new Random();
            int inicio = 0;
            if (totalZombies == 0) //si no hay zombies solo se crean nuevos zombies
                inicio = 1;
            else
                // caminan y atacan los zombies
                tablero.avanzarZombies(this);

            int numZombies = rand.nextInt(inicio,4); //num de zombies a aparecer
            totalZombies += numZombies;

            for (int i = 0; i < numZombies; i++) {
                tablero.crearZombie();
            }

            tablero.mostrarTablero(this);


            //ataque de las plantas
            // ---------------------------------------------------
            if (vidas > 0 && this.totalPlantas > 0 ){// no tiene sentido que ataquen si ya se perdió
                System.out.println("**************************************************************************************");
                System.out.println("Ataque de las plantas!");
                tablero.ataquePlantas();
                tablero.mostrarTablero(this);
            }


            //System.out.println("ataque cant movimientos " + this.cantMovimientos);
            this.cantMovimientos ++; // suma uno al terminar la fase de ataque

            // ver si se ha perdido
            if (vidas <= 0) {
                System.out.println("Has perdido! :c");
                Scanner rtaSalir = new Scanner(System.in);
                System.out.println("Presione cualquier tecla para salir.");
                rtaSalir.nextLine();
            }

            if (cantMovimientos >= 50 && vidas>0) {
                System.out.println("Has ganado! :)");
                Scanner rtaSalir = new Scanner(System.in);
                System.out.println("Presione cualquier tecla para salir.");
                rtaSalir.nextLine();
            }

        }
    }
    public int getSoles() {
        return soles;
    }
    public void setSoles(int soles) {
        this.soles = soles;
    }
}
