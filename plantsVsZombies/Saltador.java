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
    protected void caminar(Jardin jardin, Juego juego, Zombie zombie) {
        int i, j;
        i = zombie.getX();
        j = zombie.getY();
        // si no hay nada adelante avanza
        if (j > 0 && jardin.jardinZ[i][j - 1] == null && jardin.jardinP[i][j - 1] == null) {
            zombie.setY(j - 1); //cambiamos la coor 'y' del zombie
            jardin.jardinZ[i][j - 1] = jardin.jardinZ[i][j]; //lo movemos en el tablero
            jardin.jardinZ[i][j] = null; //borramos donde estaba antes
            // si al caminar queda al lado de una planta...
            if ((j - 2) >= 0 && jardin.jardinP[i][j - 2] != null ) {
                if (this.haSaltado == 2) {
                    this.haSaltado--;
                } else if (this.haSaltado == 1) {
                    //System.out.println("up 1");
                    this.haSaltado--;
                    saltar(jardin, juego, zombie);
                }
            }
        // si hay algo a su izquierda..
        } if ((j - 1) >= 0 && jardin.jardinP[i][j - 1] != null ) {
            if (this.haSaltado == 2) {
                this.haSaltado--; // en el proximo turno salta
            } else if (this.haSaltado == 1) {
                saltar(jardin, juego, zombie);
            }
        } else if (j == 0) { // si el zombie se encuentra en el limite derecho
            jardin.jardinZ[i][j] = null; //borramos donde estaba antes
            System.out.println("Se ha pasado un zombie! (>_<)");
            juego.setVidas(juego.getVidas() - 1);
        }
    }
    private void saltar(Jardin jardin, Juego juego, Zombie zombie) {
        int i = zombie.getX();
        int j = zombie.getY();
        if ((j - 2) < 0) { // si al saltar sale del tablero
            jardin.jardinZ[i][j] = null; //borramos donde estaba antes
            System.out.println("Se ha pasado un zombie! (>_<)");
            juego.setVidas(juego.getVidas() - 1);
        } else {
            if (jardin.jardinP[i][j - 2] == null && jardin.jardinZ[i][j - 2] == null) { // si no hay nada
                zombie.setY(j - 2);
                jardin.jardinZ[i][j - 2] = zombie; // realiza el salto
                jardin.jardinZ[i][j] = null; //borramos donde estaba antes
                System.out.printf("- El zombie 's' en la posición (%d,%d) ha saltado a la posición (%d,%d). Su nuevo ID es 'z'.\n", i + 1, j + 1, i + 1, j - 1);
            } else {
                System.out.printf("- El zombie 's' en la posición (%d,%d) no ha podido saltar ya que no hay lugar. Su nuevo ID es 'z'.\n", i + 1, j + 1);
                jardin.jardinZ[i][j].setAtacando(true);
            }
            // creamos al nuevo zombie
            Zombie nuevoZ = new Zombie(i);
            nuevoZ.setVida(zombie.getVida());
            nuevoZ.setY(zombie.getY());
            nuevoZ.setAtacando(zombie.isAtacando());
            jardin.jardinZ[i][zombie.getY()] = nuevoZ;
        }
    }
}
