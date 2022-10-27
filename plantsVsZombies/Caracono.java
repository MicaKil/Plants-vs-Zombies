/*
Zombi Caracono: Su cono lo hace un poco más resistente que el zombi básico.
 */

package plantsVsZombies;
public class Caracono extends Zombie {
    public Caracono(int coorX) {
        super(coorX);
        this.id = 'c';
        this.vida = 150;
        this.tieneCono = true;
    }

    @Override
    protected void avanzar(Tablero tablero, PlantsVsZombies juego, Zombie zombie){
        int i, j;
        i = zombie.getX();
        j = zombie.getY();
        if (tablero.tableroZ[i][j].tieneCono && this.vida < 150) {
            tablero.tableroZ[i][j].tieneCono = false; //pierde el cono y...
            System.out.printf("El zombie en (%d,%d) ha perdido su cono!\n", i+1, j+1);
            if (tablero.tableroZ[i][j].vida > 50) {
                tablero.tableroZ[i][j].vida -= 50; // la vida extra que le daba
            } else {
                tablero.tableroZ[i][j] = null;
            }
        }
        if (zombie.ralentizado) { // si estaba ralentizado...
            zombie.ralentizado = false; // se lo desralentiza
        } else { // si no
            if (zombie.atacando) { //si el zombie está atacando
                tablero.tableroP[i][j - 1].vida -= tablero.tableroZ[i][j].danio; // le quita vida a la planta
                //System.out.println("vida planta " + tablero.tableroP[i][j-1].vida);
                if (tablero.tableroP[i][j - 1].vida <= 0) { // si mata a la planta
                    tablero.tableroP[i][j - 1] = null; //la eliminamos
                    System.out.println("Un zombie ha comido a una planta! T-T");
                    zombie.atacando = false;
                }
            } else { //si no está atacando...
                // si no hay nada adelante avanza
                if (j > 0 && tablero.tableroZ[i][j - 1] == null && tablero.tableroP[i][j - 1] == null) {
                    zombie.y -= 1; //cambiamos la coor 'y' del zombie
                    tablero.tableroZ[i][j - 1] = tablero.tableroZ[i][j]; //lo movemos en el tablero
                    tablero.tableroZ[i][j] = null; //borramos donde estaba antes
                    // si al caminar queda al lado de una planta...
                    if ((j - 2) >= 0 && tablero.tableroP[i][j - 2] != null) {
                        tablero.tableroZ[i][j - 1].atacando = true; //deja de caminar porque va a estar atacando
                    }
                } else if (j == 0) { // si el zombie se encuentra en el limite derecho
                    tablero.tableroZ[i][j] = null; //borramos donde estaba antes
                    System.out.println("Se ha pasado un zombie! (>_<)");
                    juego.vidas--;
                }
            }
        }
    }

}
