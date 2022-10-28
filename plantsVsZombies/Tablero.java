package plantsVsZombies;

import java.util.Random;

public class Tablero {
    //protected ArrayList<Object> arr = new ArrayList <Object>();
    protected Planta[][] tableroP = new Planta[5][10]; // tablero en el que van a estar los objetos planta
    protected Zombie[][] tableroZ = new Zombie[5][10]; // tablero en el que van a estar los objetos zombies


    public Tablero() {}

    // ------------------------------------------------
    // Métodos para mostrar por pantalla
    //public static final String ANSI_RESET = "\u001B[0m";
    //public static final String ANSI_RED = "\u001B[31m";
    //public static final String ANSI_GREEN = "\u001B[32m";

    public void mostrarTablero(PlantsVsZombies juego) {
        System.out.println();
        int turnos = (int) juego.cantMovimientos; // lo paso a int para hacer la división entera
        //System.out.println("======================================================================================");
        System.out.printf("Turno N°%d \n", ((turnos + 1) /2) + 1);
        System.out.printf("Soles: %d \n", juego.soles);
        System.out.printf("Vidas: %d \n", juego.vidas);
        System.out.println("Zombies: z: Zombie, a: Abanderado, c: Caracono, b: Caracubo, l: Lector, s: Saltador");
        System.out.println("Plantas: B: Birasol, G: Girasol, L: Lanzaguisantes, R: Repetidora, H: Hielaguisantes, \n" +
                           "         N: Nuez, P: Patatapum, C: Petacereza, O: Gasoseta, U: Guisantralladora");
        //System.out.println("======================================================================================");

        /*
        System.out.println(ANSI_RED + "Zombies: A: Abanderado, C: Caracono, B: Caracubo, L: Lector, S: Saltador" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Plantas: B: Birasol, G: Girasol, L: Lanzaguisantes, R: Repetidora, H: Hielaguisantes, N: Nuez, P: Patatapum, C: Petacereza" + ANSI_RESET);
        */

        System.out.println("Tablero del juego:");
        System.out.println();
        System.out.println("    1 2 3 4 5 6 7 8 9 10");
        System.out.println("   ---------------------");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%d | ", i + 1);
            for (int j = 0; j < 10; j++) {
                if (this.tableroP[i][j] != null) {
                    System.out.print(this.tableroP[i][j].id + " " );
                    // System.out.print(ANSI_GREEN + this.tableroP[i][j].id + " " + ANSI_RESET);
                } else if (this.tableroZ[i][j] != null) {
                    System.out.print(this.tableroZ[i][j].id + " ");
                    // System.out.print(ANSI_GREEN + this.tableroP[i][j].id + " " + ANSI_RESET);
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
        /*if (tipoZombie == 3) { // si es saltador se fija primero que haya espacio para saltar en la fila
            int i = 9;
            while (i >= 0 && this.tableroP[fila][i] == null) {
                i --;
            }
            if (!(this.tableroP[fila][i] instanceof Nuez) && ((i-1) >= 0)) {
                if (this.tableroP[fila][i - 1] == null) { // si hay espacio detrás de la planta
                    this.tableroZ[fila][9] = nuevoZombie; // lo colocamos en esa fila
                }
            }

        } else {
            this.tableroZ[fila][9] = nuevoZombie; //colocamos el zombie en la tabla
        }   */
        this.tableroZ[fila][9] = nuevoZombie;
    }

    // caminan y atacan los zombies del tablero
    public void avanzarZombies(PlantsVsZombies juego) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.tableroZ[i][j] != null) {
                    this.tableroZ[i][j].avanzar(this, juego, this.tableroZ[i][j]);
                }
            }
        }
    }

    // ------------------------------------------------
    // Métodos de las plantas
    public void plantar(Planta p, PlantsVsZombies juego){
        this.tableroP[p.x][p.y]=p;
        if (p instanceof Girasol){
            juego.cantGirasoles++;
        }
        if (p instanceof Birasol){
            juego.cantGirasoles=juego.cantGirasoles+2;
        }

        // si hay un zombie al lado de la planta...
        if (this.tableroZ[p.x][p.y + 1] != null) {
            this.tableroZ[p.x][p.y + 1].atacando = true; // va a empezar a atacarla
        }
    }
    
    //metodo atacar de las plantas
    public void ataquePlantas(PlantsVsZombies juego){
       for (int i=0; i<5; i++ ){
            for (int j=0; j<10; j++){
                if(this.tableroP[i][j]!=null){ // no se generan soles
                    //if (this.tableroP[i][j] instanceof Girasol || this.tableroP[i][j] instanceof Birasol) {
                       // this.tableroP[i][j].Girasol.atacar(this.tableroP[i][j], juego); // trate de llamar al metodo sobrecargado
                    //} else {
                        this.tableroP[i][j].atacar(this.tableroP[i][j], this);
                    //}
                }
            }
        }
    }

    // ------------------------------------------------
    // Getters y Setters

}