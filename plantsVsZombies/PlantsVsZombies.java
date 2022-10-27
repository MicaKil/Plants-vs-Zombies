package plantsVsZombies;

import java.util.Random;
public class PlantsVsZombies {

    protected int soles = 200;
    protected int vidas = 3;
    protected int cantGirasoles=0;
    public int cantMovimientos = 0;
    public int totalZombies = 0;
    public PlantsVsZombies() {}

    public void iniciar() {
        //Modelado del juego con el usuario participando
        Tablero tablero = new Tablero(); //tablero vacío
        tablero.mostrarTablero(this);
        // creamos el menu de juego
        Menu menu = new Menu();

        while (this.cantMovimientos < 51 && this.vidas > 0) {
            menu.mostrarMenu(this, tablero);
            // suma uno al terminar de comprar
            this.cantMovimientos ++;

            // Zombies
            // ---------------------------------------------------

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

            tablero.mostrarTablero(this);
            cantMovimientos ++; // suma uno al terminar de moverse los zombies

            //ataque de las plantas
            // ---------------------------------------------------
            if (vidas > 0)
                tablero.ataquePlantas();
            if (vidas <= 0)
                System.out.println("Ha perdido! :c");

        }
    }
    public int getSoles() {
        return soles;
    }
    public void setSoles(int soles) {
        this.soles = soles;
    }
}
