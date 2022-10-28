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
    Tablero tablero = new Tablero();
    public PlantsVsZombies() {}

    public void iniciar() {
        //Modelado del juego con el usuario participando
        //Tablero tablero = new Tablero(); //tablero vacío
        tablero.mostrarTablero(this);
        // creamos el menu de juego
        Tienda tienda = new Tienda();

        while (this.cantMovimientos < 51.0 && this.vidas > 0) {
            tienda.comprarPlantas(this, tablero);
            this.cantMovimientos ++; // suma uno al terminar de comprar

            // Zombies
            // ---------------------------------------------------

            faseZombies();
            tablero.mostrarTablero(this);
            esperar();

            //ataque de las plantas
            // ---------------------------------------------------
            if (vidas > 0 && this.totalPlantas > 0 ){// no tiene sentido que ataquen si ya se perdió
                fasePlantas();
                tablero.mostrarTablero(this);
                esperar();
            }

            this.cantMovimientos ++; // suma uno al terminar la fase de ataque

            // chequear estado del juego
            // ---------------------------------------------------
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

    public void faseZombies() {
        System.out.println();
        System.out.println("**************************************************************************************");
        System.out.println("Los zombies están avanzando!!!");
        // modificar generacion de zombies a no tan pseudo aleatorio
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
    }

    public void fasePlantas() {
        System.out.println();
        System.out.println("**************************************************************************************");
        System.out.println("Ataque de las plantas!");
        tablero.ataquePlantas(this);
    }

    public void esperar(){ // para que se puedan ver las jugadas de las plantas y zombies
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public int getSoles() {

        return soles;
    }
    public void setSoles(int soles) {

        this.soles = soles;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getCantGirasoles() {
        return cantGirasoles;
    }

    public void setCantGirasoles(int cantGirasoles) {
        this.cantGirasoles = cantGirasoles;
    }

    public double getCantMovimientos() {
        return cantMovimientos;
    }

    public void setCantMovimientos(double cantMovimientos) {
        this.cantMovimientos = cantMovimientos;
    }

    public int getTotalZombies() {
        return totalZombies;
    }

    public void setTotalZombies(int totalZombies) {
        this.totalZombies = totalZombies;
    }

    public int getTotalPlantas() {
        return totalPlantas;
    }

    public void setTotalPlantas(int totalPlantas) {
        this.totalPlantas = totalPlantas;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
}
