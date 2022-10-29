/*
• Hielaguisantes: Lanza guisantes helados que ralentizan a los zombis, mas no los congela. Tiene un
coste de 175 soles.
 */
package plantsVsZombies;
//poner como subclase? --> tal vez no pq no necesita un lanzaguiasntes de base

public class Hielaguisantes extends Planta {

    public Hielaguisantes(int x, int y){
        super(x,y);
        this.costo=175;
        this.danio = 10;
        this.id='H';
    }
    
    @Override
    public void atacar(Planta p, PlantsVsZombies juego){
        Tablero t = juego.tablero;
        boolean foundZombie=false;
        int i = p.x;
        int j=p.y;
        while (!foundZombie){
            //si encuentra un zombie, le hace daño y sale del bucle 
            if (t.tableroZ[i][j]!=null){
                foundZombie=true;
                if (t.tableroZ[i][j].getRalentizado() == 0) {
                    System.out.printf("- El hielagusantes en la posición (%d,%d) ha ralentizado a: '%s' en la posición (%d,%d).\n",
                            p.getX() + 1, p.getY() + 1, t.tableroZ[i][j].getId(), i + 1, j + 1);
                    t.tableroZ[i][j].setRalentizado(2);
                }
                t.tableroZ[i][j].setVida(t.tableroZ[i][j].getVida()- this.danio);
                int vidaActual = t.tableroZ[i][j].getVida() - p.getDanio();
                t.tableroZ[i][j].setVida(vidaActual);
                System.out.printf("- El hielagusantes en la posición (%d,%d) le hizo %d de daño a: '%s' en la posición (%d,%d).\n",
                        p.getX() + 1, p.getY() + 1, p.getDanio(), t.tableroZ[i][j].getId(), i + 1, j + 1);
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
