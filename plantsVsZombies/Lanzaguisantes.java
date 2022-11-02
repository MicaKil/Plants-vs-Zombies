/*
• Lanzaguisantes: Dispara guisantes de uno en uno a los zombis una vez que entran en su carril. Tiene
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
    public void atacar(Planta p, PlantsVsZombies juego){
        boolean foundZombie = false;
        int i = p.getX();
        int j = p.getY();
        Tablero t = juego.tablero;
        while (!foundZombie){
            //si encuentra un zombie, le hace daño y sale del bucle 
            if (t.tableroZ[i][j]!=null){
                foundZombie=true;
                if (p.getDanio() > 0) {
                    int vidaActual = t.tableroZ[i][j].getVida() - p.getDanio();
                    t.tableroZ[i][j].setVida(vidaActual);
                    System.out.printf("- La planta '%s' en la posición (%d,%d) le hizo %d de daño a '%s' en la posición (%d,%d).\n",
                            p.getId(), p.getX() + 1, p.getY() + 1, p.getDanio(), t.tableroZ[i][j].getId(), i + 1, j + 1);
                    if (t.tableroZ[i][j].vida <= 0) {
                        System.out.println("  - El zombie '" + t.tableroZ[i][j].getId() + "' ha muerto x_x");
                        t.tableroZ[i][j] = null;
                    } else {
                        if (t.tableroZ[i][j].getId() == 'c' && vidaActual <= 100) {
                            t.tableroZ[i][j].setId('z');
                            System.out.printf("  - El zombie 'c' ha perdido su cono! Su nuevo ID es 'z'. Vida actual: %d. \n", vidaActual);
                        } else if (t.tableroZ[i][j].getId() == 'b' && vidaActual <= 100) {
                            t.tableroZ[i][j].setId('z');
                            System.out.printf("  -  El zombie 'b' ha perdido su cubeta! Su nuevo ID es 'z'. Vida actual: %d. \n", vidaActual);
                        } else {
                            System.out.println("  - Vida del zombie '" + t.tableroZ[i][j].getId() + "' : " + vidaActual);
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
