// Clase del juego PvsZ

package plantsVsZombies;

import java.util.Random;
import java.util.Scanner;

public class PlantsVsZombies {

    protected int soles = 10000;//200;
    protected int vidas = 3;
    protected double cantMovimientos = 0.0; // es double/float porque sino las operaciones de / y % resultan en la operacion entera
    protected int totalZombies = 0;
    protected int totalPlantas = 0;
    protected int cantGirasoles=0;
    protected int cantPatatapum= 0;
    protected int cantRepetidora = 0;
    protected int horda = 0;

    Tablero tablero = new Tablero();
    public PlantsVsZombies() {}

    public void iniciar() {
        //Modelado del juego con el usuario participando
        //Tablero tablero = new Tablero(); //tablero vacío
        tablero.mostrarTablero(this);
        // creamos el menu de juego
        Tienda tienda = new Tienda();

        while (this.cantMovimientos < 51.0 && this.vidas > 0) {
            //System.out.println("h " + this.horda);
            /*System.out.println("g " + this.getCantGirasoles());
            System.out.println("p " + this.getCantPatatapum());
            System.out.println("r " + this.getCantRepetidora());
            */
            tienda.comprarPlantas(this, tablero);
            System.out.println();

            this.cantMovimientos++; // suma uno al terminar de comprar

            // Zombies
            // ---------------------------------------------------

            faseZombies();
            tablero.mostrarTablero(this);
            esperar();

            //ataque de las plantas
            // ---------------------------------------------------
            if (vidas > 0 && this.totalPlantas > 0) {// no tiene sentido que ataquen si ya se perdió
                fasePlantas();
                tablero.mostrarTablero(this);
                esperar();
            }

            this.cantMovimientos++; // suma uno al terminar la fase de ataque

            System.out.println("======================================================================================");
            System.out.println();

            // chequear estado del juego
            // ---------------------------------------------------
            if (vidas <= 0) {
                System.out.println("Has perdido! :c");
                Scanner rtaSalir = new Scanner(System.in);
                System.out.println("Presione cualquier tecla para salir.");
                rtaSalir.nextLine();
            }

            if (cantMovimientos >= 50 && vidas > 0) {
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
        System.out.println("Los zombies avanzan!");
        if (this.horda == 3) {
            System.out.println("LA HORDA HA LLEGADO!!!");
        }
        // modificar generacion de zombies a no tan pseudo aleatorio
        Random rand = new Random();

        int inicio = 0;
        if (this.totalZombies == 0 && this.horda == 0) //si no hay zombies solo se crean nuevos zombies
            inicio = 1;
        else
            // caminan y atacan los zombies
            tablero.avanzarZombies(this);

        if (this.horda == 0) {
            int numZombies = rand.nextInt(inicio, 4); //num de zombies a aparecer
            this.totalZombies += numZombies;

            for (int i = 0; i < numZombies; i++) {
                tablero.crearZombie(this);
            }
        } else if (this.horda > 0){
            if (this.horda < 4) {
                for (int i = 0; i < 5; i++) {
                    tablero.crearZombie(this);
                }
            }
            this.horda-- ;
            if (this.horda == 0) {
                System.out.println();
                System.out.println("La horda ha terminado! :,,)");
                System.out.println();
            }
        }
    }

    public void fasePlantas() {
        System.out.println();
        System.out.println("**************************************************************************************");
        System.out.println("Las plantas atacan!");
        tablero.ataquePlantas(this);
    }

    public void esperar(){ // para que se puedan ver las jugadas de las plantas y zombies
        try {
            Thread.sleep(1000);
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

    public int getCantPatatapum() {
        return cantPatatapum;
    }

    public void setCantPatatapum(int cantPatatapum) {
        this.cantPatatapum = cantPatatapum;
    }

    public int getCantRepetidora() {
        return cantRepetidora;
    }

    public void setCantRepetidora(int cantRepetidora) {
        this.cantRepetidora = cantRepetidora;
    }

    public double getCantMovimientos() {
        return cantMovimientos;
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

    public int getHorda() {
        return horda;
    }

    public void setHorda(int horda) {
        this.horda = horda;
    }
}
