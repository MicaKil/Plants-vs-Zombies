/*
Lanzaguisantes: Dispara guisantes de uno en uno a los zombis una vez que entran en su carril. Tiene
un coste de 100 soles.
 */
package plantsVsZombies;

public class Lanzaguisantes extends Planta {
    public Lanzaguisantes(int x, int y){
        super(x,y);
        this.id='L';
        this.costo=100;
    }
    
    @Override
    public void atacar(Planta p, Juego juego){
        boolean foundZombie = false;
        int i = p.getX();
        int j = p.getY();
        Jardin t = juego.jardin;
        while (!foundZombie){
            //si encuentra un zombie, le hace daño y sale del bucle 
            if (t.jardinZ[i][j]!=null){
                foundZombie=true;
                if (p.getDanio() > 0) {
                    //si la planta hace daño avisa cuanto hizo y a quien  
                    int vidaActual = t.jardinZ[i][j].getVida() - p.getDanio();
                    t.jardinZ[i][j].setVida(vidaActual);
                    System.out.printf("- La planta '%s' en la posición (%d,%d) le hizo %d de daño a '%s' en la posición (%d,%d).\n",
                            p.getId(), p.getX() + 1, p.getY() + 1, p.getDanio(), t.jardinZ[i][j].getId(), i + 1, j + 1);
                    //si se queda sin vida avisa que murió el zombie
                    if (t.jardinZ[i][j].vida <= 0) {
                        System.out.println("  - El zombie '" + t.jardinZ[i][j].getId() + "' ha muerto x_x");
                        t.jardinZ[i][j] = null;
                    } else {
                        //mismo ataque pero revisa si es caracono o caracubo y cambia el id
                        if (t.jardinZ[i][j].getId() == 'c' && vidaActual <= 100) {
                            t.jardinZ[i][j].setId('z');
                            System.out.printf("  - El zombie 'c' ha perdido su cono! Su nuevo ID es 'z'. Vida actual: %d. \n", vidaActual);
                        } else if (t.jardinZ[i][j].getId() == 'b' && vidaActual <= 100) {
                            t.jardinZ[i][j].setId('z');
                            System.out.printf("  -  El zombie 'b' ha perdido su cubeta! Su nuevo ID es 'z'. Vida actual: %d. \n", vidaActual);
                        } else {
                            System.out.println("  - Vida del zombie '" + t.jardinZ[i][j].getId() + "' : " + vidaActual);
                        }
                    }
                }
            }
            else {
                if (j<9){
                    j++;
                }
                else{
                    foundZombie=true;
                }
            }
        }
    }
}
