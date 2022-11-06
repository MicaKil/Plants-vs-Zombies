/*
Hielaguisantes: Lanza guisantes helados que ralentizan a los zombis, mas no los congela. Tiene un
coste de 175 soles.
 */
package plantsVsZombies;

public class Hielaguisantes extends Planta {

    public Hielaguisantes(int x, int y){
        super(x,y);
        this.costo=175;
        this.danio = 15;
        this.id='H';
    }
    
    @Override
    public void atacar(Planta p, Juego juego){
        Jardin j = juego.jardin;
        boolean foundZombie=false;
        int x = p.getX();
        int y = p.getY();
        while (!foundZombie){
            if (j.jardinZ[x][y]!=null){
                foundZombie=true;
                //si encuentra un zombie se fija si está realentizado
                //si no está realentizado (=0) lo realentiza
                if (j.jardinZ[x][y].getRalentizado() == 0) {
                    System.out.printf("- El hielagusantes en la posición (%d,%d) ha ralentizado a: '%s' en la posición (%d,%d).\n",
                            p.getX() + 1, p.getY() + 1, j.jardinZ[x][y].getId(), x + 1, y + 1);
                    j.jardinZ[x][y].setRalentizado(2);
                }
                //si está realentizado (>0) le hace daño normal 
                j.jardinZ[x][y].setVida(j.jardinZ[x][y].getVida()- this.danio);
                int vidaActual = j.jardinZ[x][y].getVida() - p.getDanio();
                j.jardinZ[x][y].setVida(vidaActual);
                System.out.printf("- El hielagusantes en la posición (%d,%d) le hizo %d de daño a: '%s' en la posición (%d,%d).\n",
                        p.getX() + 1, p.getY() + 1, p.getDanio(), j.jardinZ[x][y].getId(), x + 1, y + 1);
                //si la vida llega o baja de 0 el zombie muere, avisa que pasó
                if (j.jardinZ[x][y].vida <= 0) {
                    System.out.println("  - El zombie '" + j.jardinZ[x][y].getId() + "' ha muerto x_x");
                    juego.setTotalZombies(juego.getTotalZombies() - 1);
                    j.jardinZ[x][y] = null;
                } else {
                    //si sigue con vida avisa cuanta vida tiene y en que posicion está
                    if (j.jardinZ[x][y].getId() == 'c' && vidaActual <= 100) {
                        j.jardinZ[x][y].setId('z');
                        System.out.printf("  - El zombie 'c' ha perdido su cono! Su nuevo ID es 'z'. Vida actual: %d. \n", vidaActual);
                    } else if (j.jardinZ[x][y].getId() == 'b' && vidaActual <= 100) {
                        j.jardinZ[x][y].setId('z');
                        System.out.printf("  -  El zombie 'b' ha perdido su cubeta! Su nuevo ID es 'z'. Vida actual: %d. \n", vidaActual);
                    } else {
                        System.out.println("  - Vida del zombie '" + j.jardinZ[x][y].getId() + "' : " + vidaActual);
                    }
                }

            }
            else {
                if (y<9){
                    y++;
                }
                else{
                    foundZombie=true;
                }
            }
        }
    }
}
