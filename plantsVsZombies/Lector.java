/*
Zombi Lector: Es un zombi que lleva un periódico. Cuando destruyen su periódico se enoja y salta
hasta la planta para comer a tus plantas.
 */
package plantsVsZombies;

public class Lector extends Zombie {
    public Lector(int coorX) {
        super(coorX);
        this.id = 'l';
        this.vida = 125;
    }
    @Override
    protected void caminar(Juego juego, Zombie zombie)  {
        Jardin jardin = juego.jardin;
        if (zombie.getVida() > 100) {
            int i, j;
            i = zombie.getX();
            j = zombie.getY();
            // si no hay nada adelante avanza
            if (j > 0 && jardin.jardinZ[i][j - 1] == null && jardin.jardinP[i][j - 1] == null) {
                zombie.setY(j - 1); //cambiamos la coor 'y' del zombie
                jardin.jardinZ[i][j - 1] = jardin.jardinZ[i][j]; //lo movemos en el tablero
                jardin.jardinZ[i][j] = null; //borramos donde estaba antes
                // si al caminar queda al lado de una planta...
                if ((j - 2) >= 0 && jardin.jardinP[i][j - 2] != null) {
                    jardin.jardinZ[i][j - 1].setAtacando(true); //deja de caminar porque va a estar atacando
                }
            } else if (j == 0) { // si el zombie se encuentra en el limite derecho
                jardin.jardinZ[i][j] = null; //borramos donde estaba antes
                System.out.println("Se ha pasado un zombie! (>_<)");
                juego.setVidas(juego.getVidas() - 1);
            }
        } else {
            saltar(juego, zombie);
        }
    }

    private static void saltar(Juego juego, Zombie zombie) {
        Jardin jardin = juego.jardin;
        int x = zombie.getX();
        int y = zombie.getY();
        int k = y - 1;
        boolean encontroLugar = false;
        while (k >= 0 && jardin.jardinP[x][k] == null && jardin.jardinZ[x][k] == null) {
            encontroLugar = true;
            k--;
        }
        if (k < 0) { // si se sale del tablero
            jardin.jardinZ[x][y] = null; //lo borramos de la posicion anterior
            System.out.printf("- El zombie lector 'l' que estaba en la posición (%d,%d) perdió su periódico y ha saltado hacia el fin del jardín!\n", x+1, y+1);
            System.out.println("Se ha pasado un zombie! (>_<)");
            juego.setVidas(juego.getVidas() - 1);
        } else {
            if (!encontroLugar) { // si no encontró lugar
                k = y; // se queda donde está
                System.out.printf("- El zombie lector 'l' en la posición (%d,%d) no ha podido saltar ya que no hay lugar. \n", x + 1, y + 1);
            } else {
                k++;
                System.out.printf("- El zombie lector 'l' que estaba en la posición (%d,%d) ha saltado a la posición (%d,%d)! \n", x + 1, y + 1, x + 1, k + 1);
            }
            System.out.println("  - El zombie lector 'l' ha perdido su periódico! Su nuevo ID es 'z'. ");
            Zombie nuevoZ = new Zombie(x); // creamos al nuevo zombie
            nuevoZ.setVida(zombie.getVida());
            nuevoZ.setY(k);
            if (jardin.jardinP[x][k - 1] != null) {
                nuevoZ.setAtacando(true); }
            jardin.jardinZ[x][y] = null; //lo borramos de la posicion anterior
            jardin.jardinZ[x][k] = nuevoZ; // y colocamos al nuevo
        }
    }

    @Override
    protected void atacar(Juego juego, Zombie zombie) {
        Jardin jardin = juego.jardin;
        if (zombie.getVida() > 100) { //ataca normalmente
            int i, j;
            i = zombie.getX();
            j = zombie.getY();
            int vida = jardin.jardinP[i][j - 1].getVida() - jardin.jardinZ[i][j].getDanio();
            if (vida > 0) {
                jardin.jardinP[i][j - 1].setVida(vida); // le quita vida a la planta
                System.out.printf("- La planta '%s' en la posición (%d,%d) ha recibido %d de danio y su vida actual es %d.\n",
                        jardin.jardinP[i][j - 1].getId(), i + 1, j, jardin.jardinZ[i][j].getDanio(), vida);
            } else { // si mata a la planta
                switch (jardin.jardinP[i][j - 1].getId()) { // si mata a una de estas plantas...
                    case 'G': {
                        juego.setCantGirasoles(juego.getCantGirasol() - 1); // reduce su cantidad en uno
                        break;
                    }
                    case 'R': {
                        juego.setCantRepetidora(juego.getCantRepetidora() - 1);
                        break;
                    }
                    case 'P': {
                        juego.setCantPatatapum(juego.getCantPatatapum() - 1);
                        break;
                    }
                }
                System.out.printf("- Un zombie ha comido a la planta '%s' que estaba en la posición (%d,%d)! T-T\n", jardin.jardinP[i][j - 1].getId(), i + 1, j - 1);
                juego.setTotalPlantas(juego.getTotalPlantas() - 1);
                jardin.jardinP[i][j - 1] = null; //la eliminamos
                zombie.setAtacando(false); //deja de atacar
            }
        } else {
            saltar(juego, zombie);
        }
    }
}
