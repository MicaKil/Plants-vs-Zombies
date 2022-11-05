// Clase del juego PvsZ

package plantsVsZombies;

import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

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
    protected boolean puedeSalirHorda = false;
    protected boolean haSalidoHorda = false;
    protected Jardin jardin = new Jardin(); //asociacion
    protected Tienda tienda = new Tienda();

    public Juego() {}

    public void iniciar() {
        while (this.turno <= 25 && this.vidas > 0) {
            System.out.printf("Turno N°%d \n", turno);
            // tienda
            mostrarMenu();

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
                System.out.println();
                this.turno ++;
                System.out.println("#########################################################################################################");
                System.out.println();
            }
        }
    }

    private void faseZombies() {
        this.puedeSalirHorda = this.turno >= 5 && this.turno < 25; // a los 5 turnos puede salir la horda

        System.out.println("*********************************************************************************************************");
        System.out.println("Los zombies avanzan!");

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

            if (jardin.filasLibres() > 0) { //si hay espacio en la columna 10
                Random rand = new Random();
                int bound = Math.min(jardin.filasLibres(), 4); // toma el min entre los espacios libres en la columna 10 y 4
                // ej: si tenemos solo un 1 espacio vacío no pueden salir 3 zombies -> bucle infinito
                int numZombies = rand.nextInt(bound); //pueden apareces hasta 4 (sin incluir) zombies
                this.totalZombies += numZombies;
                for (int i = 0; i < numZombies; i++) {
                    jardin.crearZombie(this);
                }
            }
        }
    }

    private void hordaEvento() {
        if (this.hordaCounter > 7) {
            System.out.println();
            System.out.printf("QUEDAN %d TURNOS PARA QUE LLEGUE LA HORDA! \n", (this.hordaCounter - 6));
            System.out.println();
        } else if (this.hordaCounter == 7) {
            System.out.println();
            System.out.println("QUEDA 1 TURNO PARA QUE LLEGUE LA HORDA!");
            System.out.println();
        } else if (this.hordaCounter == 6) { // podria ser max horda menos 2
            System.out.println();
            System.out.println("LA HORDA HA LLEGADO!!!");
            System.out.println();
        }

        jardin.avanzarZombies(this); // caminan y atacan los zombies
        int filas = jardin.filasLibres();

        if (this.hordaCounter <= 6 && filas > 0) { //si hay espacio en la columna 10
            for (int i = 0; i < filas; i++) {
                jardin.crearZombie(this); // por tres turnos se llenan las columnas
                this.totalZombies += filas;
            }
        }
        this.hordaCounter--;
        if (this.hordaCounter == 3) { // tres turnos de cortesía
            System.out.println();
            System.out.println("LA HORDA HA TERMINADO! :,,)");
        }
    }

    // Plantas--------------------------------------------------------------------------------------

    private void fasePlantas() {
        System.out.println("*********************************************************************************************************");
        System.out.println("Turno de las plantas!");
        System.out.println();

        jardin.ataquePlantas(this);
    }

    // --------------------------------------------------------------------------------------------

    private void mostrarMenu() {
        boolean menuFlag = true;
        do {
            jardin.mostrarJardin(this);

            boolean cada5 = ((this.turno%5.0) == 0);  //para que aparezca la tienda de dave cada 5 turmos
            Scanner read = new Scanner(System.in);
            if (this.turno == 1){
                System.out.println("En el primer turno solo puede comprar girasoles.");
            }
            //agregar opcion para cancelar, volver a elegir coordenada
            System.out.println();
            if (cada5){
                System.out.println(" *** ESTÁ DISPONIBLE LA TIENDA DE CRAZY DAVE! *** ");
                System.out.println("Para comprar en la tienda, ingrese el menu de compra.");
            } else if ((5 - this.turno%5) > 1){
                System.out.printf("Quedan %d turnos para que aparezca la tienda de Crazy Dave!\n", (5 - this.turno%5));
            } else {
                System.out.println("Queda 1 turno para que aparezca la tienda de Crazy Dave!");
            }
            System.out.println();
            if (this.soles < 25) {
                System.out.println("No tienes los soles suficientes para realizar una compra. :(");
                System.out.println("Si desea leer una guía del juego ingrese 'L' o 'l'. Sino apriete cualquier otra tecla.");
                String guia = read.nextLine();
                if (guia.equals("L") || guia.equals("l")) {
                    mostrarGuia();
                } else {
                    menuFlag = false;
                }
            } else {
                System.out.println("Tiene: " + this.soles + " soles disponibles para comprar.");
                System.out.println("'S'/'s': Si desea comprar ingrese S o s. Si desea continuar sin comprar apriete cualquier otra tecla.");
                System.out.println("'L'/'l': Si desea leer una guía del juego ingrese 'L' o 'l'");
                System.out.println("Si no desea hacer lo anterior apriete cualquier otra tecla.");
                String menu = read.nextLine();
                if (menu.equals("S") || menu.equals("s")) {
                    tienda.comprarPlantas(this, jardin);
                } else if (menu.equals("L") || menu.equals("l")) {
                    mostrarGuia();
                } else {
                    menuFlag = false;
                }
            }
        } while (menuFlag);
    }

    protected static void esperar(){ // para que se puedan ver las jugadas de las plantas y zombies
        Scanner rtaSeguir = new Scanner(System.in);
        System.out.println("Presione cualquier tecla para continuar.");
        rtaSeguir.nextLine();
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

    private static void mostrarGuia() {
        System.out.println("=========================================================================================================");
        System.out.println("El estilo de juego es similar a otros juegos del tipo tower defense. Tenemos un tablero que tiene\n" +
                "5 carriles por donde atacan las plantas y 10 casilleros por carril donde se puede posicionar plantas o zombies. \n" +
                "Al inicio se arranca con girasoles siempre para empezar a producir soles que nos ayuden a\n" +
                "comprar nuevas plantas. Por otro lado, los zombies aparecen por el lado derecho invadiendo algún carril\n" +
                "aleatoriamente. El movimiento de los zombies siempre es lento y de a un casillero. Las plantas atacan\n" +
                "siempre de izquierda a derecha y depende del rango de ataque será la activación de las mismas.\n" +
                "Se pierde una vida cuando se pasa un zombie. Si la vida llega a cero el juego termina y se pierde la partida.\n" +
                "El juego se gana si se llega al turno 25 con vida mayor a cero.\n" + "\n" +
                " ~ Plantas ~\n" +
                "Son los personajes que te ayudarán a combatir los zombis y proteger tu jardín.\n" +
                " - Lanzaguisantes: Dispara guisantes de uno en uno a los zombis una vez que entran en su carril. \n" +
                " - Girasol: Produce soles con un valor de 25 para que puedas sembrar otras plantas.\n" +
                " - Petacereza: Provoca una explosión en un área de 3x3 capaz de matar a casi cualquier zombi.\n" +
                " - Nuez: Bloquea el paso de los zombis hasta ser devorada.\n" +
                " - Patatapum: Una mina de papa que explota cuando un zombi la pisa, tarda un poco en activarse.\n" +
                " - Hielaguisantes: Lanza guisantes helados que ralentizan a los zombis, mas no los congela.\n" +
                " - Repetidora: Es como el lanzaguisantes, con la diferencia de que dispara 2 guisantes a la vez. \n" + "\n" +
                " ~ Tienda de Crazy Dave ~\n" +
                "La tienda de Crazy Dave aparece cada 5 turnos.\n" +
                "Las siguientes plantas se consiguen comprandolas en dicha tienda. Son modificaciones para\n" +
                "una planta inicial en específico. Para usarse se deben plantar sobre la planta indicada.\n" +
                " - Guisantralladora: Dispara cuatro guisantes a la vez. Para plantarla en el patio se necesita estar\n" +
                "   plantada sobre una Repetidora.\n" +
                " - Birasol: Proporciona el doble de soles que el Girasol. Se necesita estar plantada sobre un Girasol.\n" +
                " - Gasoseta: Expulsa gases alrededor de una área de 3x3. Se tiene que plantar sobre Patatapum. \n" + "\n" +
                " ~ Zombies ~\n" +
                "Son los enemigos que hay que combatir:\n" +
                " - Zombi: Zombi de jardín común, no tiene nada en especial.\n" +
                " - Zombi Abanderado: Marca la llegada de una horda de zombis. Tras su llegado por dos turno no salen\n" +
                "   zombis y luego llega la horda.\n" +
                " - Zombi Caracono: Su cono lo hace un poco más resistente que el zombi básico.\n" +
                " - Zombi saltador de pértiga: Salta sobre la primera planta que encuentra con su pértiga para luego\n" +
                "   caminar. La Nuez puede detener su salto.\n" +
                " - Zombi Caracubo: Su cubo lo hace el más resistente de los zombis comunes y corriente.\n" +
                " - Zombi Lector: Es un zombi que lleva un periódico. Cuando destruyen su periódico se enoja y salta\n" +
                "   hasta la planta para comer a tus plantas.");
        System.out.println("=========================================================================================================");
        esperar();
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
    public int getCantGirasol() {
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

    public void setHaSalidoHorda(boolean haSalidoHorda) {
        this.haSalidoHorda = haSalidoHorda;
    }
}
