package plantsVsZombies;

import java.util.Random;

public class Jardin {
    protected Planta[][] jardinP = new Planta[5][10]; // matriz en el que van a estar los objetos planta
    protected Zombie[][] jardinZ = new Zombie[5][10]; // matriz en el que van a estar los objetos zombies

    public Jardin() {}

    // ------------------------------------------------
    // Métodos para mostrar por pantalla


    protected void mostrarJardin(Juego juego) {
        System.out.println();
        System.out.printf("Soles: %d \n", juego.soles);
        System.out.printf("Vidas: %d \n", juego.vidas);
        System.out.println("Zombies: z: Zombie, a: Abanderado, c: Caracono, b: Caracubo, l: Lector, s: Saltador");
        System.out.println("Plantas: B: Birasol, G: Girasol, L: Lanzaguisantes, R: Repetidora, H: Hielaguisantes, \n" +
                           "         N: Nuez, P: Patatapum, C: Petacereza, O: Gasoseta, U: Guisantralladora");
        System.out.println();

        System.out.println("Tablero del juego:");
        System.out.println();
        System.out.println("    1 2 3 4 5 6 7 8 9 10");
        System.out.println("   ---------------------");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%d | ", i + 1);
            for (int j = 0; j < 10; j++) {
                if (this.jardinP[i][j] != null) {
                    System.out.print(this.jardinP[i][j].id + " " );
                } else if (this.jardinZ[i][j] != null) {
                    System.out.print(this.jardinZ[i][j].id + " ");
                } else
                    System.out.print("_ ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // ------------------------------------------------
    // Métodos para los Zombies
    protected void crearZombie(Juego juego) {
        int up = 5;
        if (juego.puedeSalirHorda && juego.getHordaCounter() == 0) { //puede aparecer el abanderado si ha terminado una horda
            up = 6;
        }
        Random rand = new Random();
        int tipoZombie = rand.nextInt(up);

        int fila;
        do { // en caso de que se genere un zombie en una casilla ocupada // genera un numero random desde 0 a 5 (sin tomar el 5)
            fila = rand.nextInt(5); // fila en la que va a aparecer el nuevo zombie
        } while (this.jardinZ[fila][9] != null);

        Zombie nuevoZombie;
        switch (tipoZombie) {
            case 0: {
                nuevoZombie = new Zombie(fila);
                this.jardinZ[fila][9] = nuevoZombie;
                break;
            }
            case 1: { // caracono
                nuevoZombie = new Zombie(fila);
                nuevoZombie.setId('c');
                nuevoZombie.setVida(150);
                this.jardinZ[fila][9] = nuevoZombie;
                break;
            }
            case 2: { // caracubo
                nuevoZombie = new Zombie(fila);
                nuevoZombie.setId('b');
                nuevoZombie.setVida(200);
                this.jardinZ[fila][9] = nuevoZombie;
                break;
            }
            case 3: {
                nuevoZombie = new Saltador(fila);
                this.jardinZ[fila][9] = nuevoZombie;
                break;
            }
            case 4: {
                nuevoZombie = new Lector(fila);
                this.jardinZ[fila][9] = nuevoZombie;
                break;
            }
            case 5: {
                nuevoZombie = new Zombie(fila);
                nuevoZombie.setId('a');
                this.jardinZ[fila][9] = nuevoZombie;
                System.out.println("SE VIENE LA HORDA!!!");
                juego.setHordaCounter(8); // el evento horda dura 7 turnos
                break;
            }
            default: System.out.println("Error al crear zombie.");
        }
    }

    // caminan y atacan los zombies del tablero
    protected void avanzarZombies(Juego juego) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.jardinZ[i][j] != null) {
                    this.jardinZ[i][j].avanzar(this, juego, this.jardinZ[i][j]);
                }
            }
        }
    }

    protected boolean columnaNotLlena() {
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            if (jardinZ[i][9] != null) {
                counter++;
            }
        }
        return counter < 5;
    }

    // ------------------------------------------------
    // Métodos de las plantas
    protected void plantar(Planta p){
        this.jardinP[p.x][p.y]=p;
        // si hay un zombie al lado de la planta...
        if (this.jardinZ[p.x][p.y + 1] != null && this.jardinZ[p.x][p.y + 1].getId() != 's')  {
            this.jardinZ[p.x][p.y + 1].atacando = true; // va a empezar a atacarla
        }
    }
    
    //metodo atacar de las plantas
    protected void ataquePlantas(Juego juego){
       for (int i=0; i<5; i++ ){
            for (int j=0; j<10; j++){
                if(this.jardinP[i][j]!=null){
                    this.jardinP[i][j].atacar(this.jardinP[i][j], juego);
                }
            }
        }
    }

}