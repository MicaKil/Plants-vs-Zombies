package plantsVsZombies;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {
    //protected ArrayList<Object> arr = new ArrayList <Object>();
    protected Planta[][] tableroP = new Planta[5][10]; // tablero en el que van a estar los objetos planta
    protected Zombie[][] tableroZ = new Zombie[5][10]; // tablero en el que van a estar los objetos zombies
    protected int soles = 200;
    protected int vidas = 3;
    protected int cantGirasoles=0;
    //protected int cantBirasoles=0;
    
    public Tablero() {}

    // ------------------------------------------------
    // Métodos para mostrar por pantalla
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public void mostrarTablero() {
        System.out.printf("Soles: %d \n", this.soles);
        System.out.printf("Vidas: %d \n", this.vidas);
        System.out.println(ANSI_RED + "Zombies: A: Abanderado, C: Caracono, B: Caracubo, L: Lector, S: Saltador" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Plantas: B: Birasol, G: Girasol, L: Lanzaguisantes, R: Repetidora, H: Hielaguisantes, N: Nuez, P: Patatapum, C: Petacereza" + ANSI_RESET);

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

    // ------------------------------------------------
    // Métodos para los Zombies
    public void crearZombie() {
        Random rand = new Random();

        int tipoZombie = rand.nextInt(5); // genera un numero random desde 0 a 5 (sin tomar el 5)

        int fila;
        do { // en caso de que se genere un zombie en una casilla ocupada
            fila = rand.nextInt(5); // fila en la que va a aparecer el nuevo zombie
        } while (this.tableroZ[fila][9] != null);

        Zombie nuevoZombie = null;
        switch (tipoZombie) {
            case 0 -> nuevoZombie = new Zombie(fila);
            case 1 -> nuevoZombie = new Caracubo(fila);
            case 2 -> nuevoZombie = new Caracono(fila);
            case 3 -> nuevoZombie = new Saltador(fila);
            case 4 -> nuevoZombie = new Lector(fila);
            default -> System.out.println("Error al crear zombie.");
        }
        this.tableroZ[fila][9] = nuevoZombie; //colocamos el zombie en la tabla
    }

    // caminan y atacan los zombies del tablero
    public void avanzarZombies() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.tableroZ[i][j] != null) {
                    if (this.tableroZ[i][j].ralentizado) { // si estaba ralentizado...
                        this.tableroZ[i][j].ralentizado = false; // se lo desralentiza
                    } else { // si no
                        if (this.tableroZ[i][j].atacando) { //si el zombie está atacando
                            this.tableroP[i][j - 1].vida -= this.tableroZ[i][j].danio; // le quita vida a la planta
                            //System.out.println("vida planta " + this.tableroP[i][j-1].vida);
                            if (this.tableroP[i][j - 1].vida <= 0) { // si mata a la planta
                                this.tableroP[i][j - 1] = null; //la eliminamos
                                System.out.println("Un zombie ha comido a una planta! (> ﹏ <)");
                                this.tableroZ[i][j].atacando = false;
                            }
                        } else { //si no está atacando...
                            // si no hay nada adelante avanza
                            if (j > 0 && this.tableroZ[i][j - 1] == null && this.tableroP[i][j - 1] == null) {
                                this.tableroZ[i][j].y -= 1;
                                this.tableroZ[i][j - 1] = this.tableroZ[i][j];
                                this.tableroZ[i][j] = null;
                                // si al caminar queda al lado de una planta...
                                if ((j - 2) >= 0 && this.tableroP[i][j - 2] != null) {
                                    this.tableroZ[i][j - 1].atacando = true; //deja de caminar porque va a estar atacando
                                }
                            } else if (j == 0) {
                                this.tableroZ[i][j] = null;
                                System.out.println("Se ha pasado un zombie!");
                                this.vidas--;
                            }
                        }
                    }
                }
            }
        }
    }

    // ------------------------------------------------
    // Métodos de las plantas
    public void plantar(Planta p){
        this.tableroP[p.x][p.y]=p;
        if (p instanceof Girasol){
            this.cantGirasoles++;
        }
        if (p instanceof Birasol){
            this.cantGirasoles=this.cantGirasoles+2;
        }

        // si hay un zombie al lado de la planta...
        if (this.tableroZ[p.x][p.y + 1] != null) {
            this.tableroZ[p.x][p.y + 1].atacando = true; // va a empezar a atacarla
        }

    }
    
    //metodo atacar de las plantas
    public void ataquePlantas(){
       for (int i=0; i<5; i++ ){
            for (int j=0; j<10; j++){
                if(this.tableroP[i][j]!=null){
                    this.tableroP[i][j].atacar(this.tableroP[i][j], this);
                }
            }
        }
    }
    // ------------------------------------------------
    // Getters y Setters
    public int getSoles() {
        return soles;
    }

    public void setSoles(int soles) {
        this.soles = soles;
    }
}