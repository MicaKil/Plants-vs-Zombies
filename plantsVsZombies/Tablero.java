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
        int turnos = (int) juego.cantMovimientos; // lo paso a int para hacer la división entera
        System.out.println();
        //System.out.println("======================================================================================");
        System.out.printf("Turno N°%d \n", ((turnos + 1) /2) + 1);
        System.out.printf("Soles: %d \n", juego.soles);
        System.out.printf("Vidas: %d \n", juego.vidas);
        System.out.println("Zombies: z: Zombie, a: Abanderado, c: Caracono, b: Caracubo, l: Lector, s: Saltador");
        System.out.println("Plantas: B: Birasol, G: Girasol, L: Lanzaguisantes, R: Repetidora, H: Hielaguisantes, \n" +
                           "         N: Nuez, P: Patatapum, C: Petacereza, O: Gasoseta, U: Guisantralladora");
        System.out.println();
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
    public void crearZombie(PlantsVsZombies juego) {
        boolean cadaX = true; //((((juego.getCantMovimientos())/2.0)+1.0)%5.0==0.0); // cada X turnos x porque no estoy segura
        Random rand = new Random();
        int up = 5;
        if (cadaX && juego.getHorda() == 0) { //cada X puede aparecer el abanderado si ha terminado una horda
            up = 6;
        }
        int tipoZombie = rand.nextInt(up);

        int fila;
        do { // en caso de que se genere un zombie en una casilla ocupada // genera un numero random desde 0 a 5 (sin tomar el 5)
            fila = rand.nextInt(5); // fila en la que va a aparecer el nuevo zombie
        } while (this.tableroZ[fila][9] != null);

        Zombie nuevoZombie;
        switch (tipoZombie) {
            case 0 -> {
                nuevoZombie = new Zombie(fila);
                this.tableroZ[fila][9] = nuevoZombie;
            }
            case 1 -> { // caracono
                nuevoZombie = new Zombie(fila);
                nuevoZombie.setId('c');
                nuevoZombie.setVida(150);
                this.tableroZ[fila][9] = nuevoZombie;
            }
            case 2 -> { // caracubo
                nuevoZombie = new Zombie(fila);
                nuevoZombie.setId('b');
                nuevoZombie.setVida(200);
                this.tableroZ[fila][9] = nuevoZombie;
            }
            case 3 -> {
                nuevoZombie = new Saltador(fila);
                this.tableroZ[fila][9] = nuevoZombie;
            }
            case 4 -> {
                nuevoZombie = new Lector(fila);
                this.tableroZ[fila][9] = nuevoZombie;
            }
            case 5 -> {
                nuevoZombie = new Zombie(fila);
                nuevoZombie.setId('a');
                this.tableroZ[fila][9] = nuevoZombie;
                System.out.println();
                System.out.println("HORDA INCOMING!!!!!!"); // son las 4am no se me ocurre otra cosa
                System.out.println();
                juego.setHorda(5); // debería ser un número <= al de cada X
            }
            default -> System.out.println("Error al crear zombie.");
        }

    }
    // HACER METODO CREAR HORDA???
    // COMO ZOMBIE PERO SIN RAND SOLO EN ORDEN PARA MEJORAR LA COMPLEJIDAD


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
        /*if (p instanceof Girasol){
            juego.cantGirasoles++;
        }
        if (p instanceof Birasol){
            juego.cantGirasoles=juego.cantGirasoles+2;
        }*/

        // si hay un zombie al lado de la planta...
        if (this.tableroZ[p.x][p.y + 1] != null && this.tableroZ[p.x][p.y + 1].getId() != 's')  {
            this.tableroZ[p.x][p.y + 1].atacando = true; // va a empezar a atacarla
        }
    }
    
    //metodo atacar de las plantas
    public void ataquePlantas(PlantsVsZombies juego){
       for (int i=0; i<5; i++ ){
            for (int j=0; j<10; j++){
                if(this.tableroP[i][j]!=null){
                    this.tableroP[i][j].atacar(this.tableroP[i][j], juego);
                }
            }
        }
    }

    // ------------------------------------------------
    // Getters y Setters

}