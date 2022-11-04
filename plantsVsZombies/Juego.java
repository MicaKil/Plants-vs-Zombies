// Clase del juego PvsZ

package plantsVsZombies;

import java.util.Random;
import java.util.Scanner;

public class Juego {

    protected int soles = 200;
    protected int vidas = 3;

    protected int turno = 1;
    protected int totalZombies = 0;
    protected int totalPlantas = 0;
    protected int cantGirasoles = 0;
    protected int cantPatatapum = 0;
    protected int cantRepetidora = 0;
    protected int hordaCounter = 0;
    protected boolean puedeSalirHorda = true;

    Jardin jardin = new Jardin(); //asociacion
    public Juego() {}

    public void iniciar() {
        Tienda tienda = new Tienda(); // creamos la tienda de juego

        while (this.turno <= 25 && this.vidas > 0) {
            System.out.printf("Turno N°%d \n", turno);
            jardin.mostrarJardin(this);

            // tienda
            tienda.comprarPlantas(this, jardin);

            // Zombies
            // ---------------------------------------------------

            faseZombies();
            jardin.mostrarJardin(this);
            esperar();

            //plantas
            // ---------------------------------------------------
            if (vidas > 0 && this.totalPlantas > 0) {// no tiene sentido que ataquen si ya se perdió
                fasePlantas();
                jardin.mostrarJardin(this);
                esperar();
            }

            // chequear fin juego
            // ---------------------------------------------------
            if (this.vidas <= 0 || this.turno >= 25) {
                finJuego();
            } else {
                System.out.printf("Ha finalizado el turno N°%d. \n", turno);
                this.turno ++;
                System.out.println();
                System.out.println("======================================================================================");
                System.out.println();
            }
        }
    }

    private void faseZombies() {
        this.puedeSalirHorda = this.turno >= 5 && this.turno < 25; // a los 5 turnos puede salir

        System.out.println("**************************************************************************************");
        System.out.println("Los zombies avanzan!");
        System.out.println();

        if (this.hordaCounter > 3) { // si hay una horda en proceso
            hordaEvento();
        } else {
            if (this.hordaCounter > 0) {
                this.hordaCounter--;
            }
            if (this.totalZombies == 0) {//si no hay zombies solo se crean nuevos zombies
                jardin.crearZombie(this); // para crear al menos un zombie
                this.totalZombies++;
            } else
                jardin.avanzarZombies(this); // caminan y atacan los zombies

            if (jardin.columnaNotLlena()) { //si hay espacio en la columna 10
                Random rand = new Random();
                int numZombies = rand.nextInt(4); //pueden apareces hasta 4 (sin incluir) zombies
                this.totalZombies += numZombies;
                for (int i = 0; i < numZombies; i++) {
                    jardin.crearZombie(this);
                }
            }
        }
    }

    private void hordaEvento() {
        if (this.hordaCounter > 7) {
            System.out.printf("Quedan %d turnos para que llegue la horda! \n", (this.hordaCounter - 6));
        } else if (this.hordaCounter == 7) {
            System.out.println("Queda 1 turno para que llegue la horda!");
        }
        if (this.hordaCounter == 6) { // podria ser max horda menos 2
            System.out.println("LA HORDA HA LLEGADO!!!");
        }
        jardin.avanzarZombies(this); // caminan y atacan los zombies
        if (this.hordaCounter <= 6 && jardin.columnaNotLlena()) { //si hay espacio en la columna 10
            for (int i = 0; i < 5; i++) {
                jardin.crearZombie(this); // por tres turnos se llenan las columnas
                this.totalZombies += 5;
            }
        }
        this.hordaCounter--;
        if (this.hordaCounter == 3) { // tres turnos de cortesía
            System.out.println("La horda ha terminado! :,,)");
        }
    }

    private void fasePlantas() {
        System.out.println();
        System.out.println("**************************************************************************************");
        System.out.println("Las plantas atacan!");
        System.out.println();

        jardin.ataquePlantas(this);
    }

    protected static void esperar(){ // para que se puedan ver las jugadas de las plantas y zombies
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }*/
        Scanner rtaSeguir = new Scanner(System.in);
        System.out.println("Presione cualquier tecla para seguir.");
        rtaSeguir.nextLine();
        System.out.println();
    }

    private void finJuego() {
        if (this.vidas <= 0) {
            System.out.println("Has perdido! :c");
            Scanner rtaSalir = new Scanner(System.in);
            System.out.println("Presione cualquier tecla para salir.");
            rtaSalir.nextLine();
        } else if (this.turno >= 25) {
            System.out.println("Has ganado! :)");
            this.turno++; //sino sigue jeje
            Scanner rtaSalir = new Scanner(System.in);
            System.out.println("Presione cualquier tecla para salir.");
            rtaSalir.nextLine();
        }
    }

    // GETTERS Y SETTERS
    // -----------------------------------------------------------------------------
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
    public int getHordaCounter() {
        return hordaCounter;
    }
    public void setHordaCounter(int hordaCounter) {
        this.hordaCounter = hordaCounter;
    }
    public int getTurno() {
        return turno;
    }
}
