/*
Zombi saltador de pértiga: Salta sobre la primera planta que encuentra con su pértiga para luego
caminar. Nuez-Cáscara Rabias puede detener su salto.
 */
package plantsVsZombies;

public class Saltador extends Zombie {
    public Saltador(int coorX) {
        super(coorX);
        this.id = 's';
    }
    protected boolean haSaltado = false;
    /* fail de salto
    public void saltar (Tablero tablero, Zombie zombie) {
        int j = 9;
        int i = zombie.y;
         // el zombie no ha saltado

        while (!haSaltado && j >= 0) {
            if (tablero.tableroP[i][j] != null) { // si hay una planta
                haSaltado = true;
                if (tablero.tableroP[i][j].id != 'N') { //si una nuez interrumpe su salto
                    while (tablero.tableroZ[i][j] != null && tablero.tableroP[i][j] != null && j >= 9) { //cae a la derecha de esta o en el espacio vació más próximo
                        j++;
                    }
                }
                else { //si no hay una nuez entonces
                    while (tablero.tableroZ[i][j] != null && tablero.tableroP[i][j] != null && j >= 0) { //salta a la izquierda de esta o en el espacio vació más próximo
                        j--;
                    }
                }
            }
            j--;
        }
    } */
}
