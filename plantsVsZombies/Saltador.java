/*
Zombi saltador de pértiga: Salta sobre la primera planta que encuentra con su pértiga para luego
caminar. Nuez-Cáscara Rabias puede detener su salto.
 */
package plantsVsZombies;

public class Saltador extends Zombie {
    protected int haSaltado;

    public Saltador(int coorX) {
        super(coorX);
        haSaltado = 2;
        this.id = 's';
    }


    @Override
    protected void caminar(Tablero tablero, PlantsVsZombies juego, Zombie zombie) {
        //System.out.println("caminar");
        //System.out.println("s " + this.haSaltado);
        int i, j;
        i = zombie.getX();
        j = zombie.getY();
        // si no hay nada adelante avanza
        if (j > 0 && tablero.tableroZ[i][j - 1] == null && tablero.tableroP[i][j - 1] == null) {
            zombie.setY(j - 1); //cambiamos la coor 'y' del zombie
            tablero.tableroZ[i][j - 1] = tablero.tableroZ[i][j]; //lo movemos en el tablero
            tablero.tableroZ[i][j] = null; //borramos donde estaba antes
            // si al caminar queda al lado de una planta...
            if ((j - 2) >= 0 && tablero.tableroP[i][j - 2] != null ) {
                //calcularSalto(tablero, juego, zombie, 2);
                //System.out.println("here"); //COMENTADO PORQUE PUEDE FALLAR
                if (tablero.tableroP[i][j - 2].getId() == 'N') {// si tiene una nuez adelante...
                    this.haSaltado = 0; //se cancela el salto
                    System.out.printf("- La nuez en (%d,%d) ha bloqueado el salto del zombie a su derecha!\n", i + 1, j - 1);
                }
                if (this.haSaltado == 0) { // si ya ha saltado ataca
                    //System.out.println("up 0");
                    tablero.tableroZ[i][j - 1].setAtacando(true);
                }
                else if (this.haSaltado == 2) {
                    //System.out.println("up 2");
                    this.haSaltado--; // en el proximo turno salta
                }
                else if (this.haSaltado == 1) {
                    //System.out.println("up 1");
                    this.haSaltado--;
                    saltar(tablero, juego, zombie);
                }
            } // si hay algo a su izquierda..
        } if ((j - 1) >= 0 && tablero.tableroP[i][j - 1] != null ) {
            //System.out.println("here 2");
            //calcularSalto(tablero, juego, zombie, 1);
            if (tablero.tableroP[i][j - 1].getId() == 'N') {// si tiene una nuez adelante...
                this.haSaltado = 0; //se cancela el salto
                System.out.printf("- La nuez en (%d,%d) ha bloqueado el salto del zombie a su derecha!\n", i + 1, j - 1);
            }
            if (this.haSaltado == 0) { // si ya ha saltado ataca
               //System.out.println("0");
                tablero.tableroZ[i][j].setAtacando(true);
            } else if (this.haSaltado == 2) {
                //System.out.println("2");
                this.haSaltado--; // en el proximo turno salta
            } else if (this.haSaltado == 1) {
                //System.out.println("1");
                saltar(tablero, juego, zombie);
            }
        } else if (j == 0) { // si el zombie se encuentra en el limite derecho
            tablero.tableroZ[i][j] = null; //borramos donde estaba antes
            System.out.println("Se ha pasado un zombie! (>_<)");
            juego.setVidas(juego.getVidas() - 1);
        }
    }
    private void saltar(Tablero tablero, PlantsVsZombies juego, Zombie zombie) {
        this.haSaltado = 0;
        //System.out.println("salto");
        int i = zombie.getX();
        int j = zombie.getY();
        //System.out.println("w " + (j-2));
        if ((j - 2) < 0) { // si al saltar sale del tablero
            tablero.tableroZ[i][j] = null; //borramos donde estaba antes
            System.out.println("Se ha pasado un zombie! (>_<)");
            juego.setVidas(juego.getVidas() - 1);
        } else {
            if (tablero.tableroP[i][j - 2] == null && tablero.tableroZ[i][j - 2] == null) { // si no hay nada
                zombie.setY(j - 2);
                tablero.tableroZ[i][j - 2] = zombie; // realiza el salto
                tablero.tableroZ[i][j] = null; //borramos donde estaba antes
                System.out.printf("El zombie 's' en la posición (%d,%d) ha saltado.\n", i + 1, j + 1);
            } else {
                System.out.printf("El zombie 's' en la posición (%d,%d) no ha podido saltar ya que no hay lugar.\n", i + 1, j + 1);
                tablero.tableroZ[i][j].setAtacando(true);
            }
        }


    }
/* NO FUNCA
    private void calcularSalto(Tablero tablero, PlantsVsZombies juego, Zombie zombie, int inc) {
        int i = zombie.getX();
        int j = zombie.getY();

        //System.out.println("here");
        if (tablero.tableroP[i][j - inc].getId() == 'N') {// si tiene una nuez adelante...
            this.haSaltado = 0; //se cancela el salto
            System.out.printf("- La nuez en (%d,%d) ha bloqueado el salto del zombie a su derecha!\n", i + 1, j - 1);
        }
        if (this.haSaltado == 0) { // si ya ha saltado ataca
            //System.out.println("up 0");
            tablero.tableroZ[i][j - (inc + 1)].setAtacando(true);
        }
        else if (this.haSaltado == 2) {
            //System.out.println("up 2");
            this.haSaltado--; // en el proximo turno salta
        }
        else if (this.haSaltado == 1) {
            //System.out.println("up 1");
            saltar(tablero, juego, zombie);
        }
    } */
}
