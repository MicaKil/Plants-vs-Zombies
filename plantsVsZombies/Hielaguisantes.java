/*
Hielaguisantes: Lanza guisantes helados que ralentizan a los zombis, mas no los congela. Tiene un
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
    public void atacar(Planta p, Juego juego){
        Jardin t = juego.jardin;
        boolean foundZombie=false;
        int i = p.x;
        int j=p.y;
        while (!foundZombie){
            if (t.jardinZ[i][j]!=null){
                foundZombie=true;
                //si encuentra un zombie se fija si está realentizado
                //si no está realentizado (=0) lo realentiza
                if (t.jardinZ[i][j].getRalentizado() == 0) {
                    System.out.printf("- El hielagusantes en la posición (%d,%d) ha ralentizado a: '%s' en la posición (%d,%d).\n",
                            p.getX() + 1, p.getY() + 1, t.jardinZ[i][j].getId(), i + 1, j + 1);
                    t.jardinZ[i][j].setRalentizado(2);
                }
                //si está realentizado (>0) le hace daño normal 
                t.jardinZ[i][j].setVida(t.jardinZ[i][j].getVida()- this.danio);
                int vidaActual = t.jardinZ[i][j].getVida() - p.getDanio();
                t.jardinZ[i][j].setVida(vidaActual);
                System.out.printf("- El hielagusantes en la posición (%d,%d) le hizo %d de daño a: '%s' en la posición (%d,%d).\n",
                        p.getX() + 1, p.getY() + 1, p.getDanio(), t.jardinZ[i][j].getId(), i + 1, j + 1);
                //si la vida llega o baja de 0 el zombie muere, avisa que pasó
                if (t.jardinZ[i][j].vida <= 0) {
                    System.out.println("  - El zombie '" + t.jardinZ[i][j].getId() + "' ha muerto x_x");
                    juego.setTotalZombies(juego.getTotalZombies() - 1);
                    t.jardinZ[i][j] = null;
                } else {
                    //si sigue con vida avisa cuanta vida tiene y en que posicion está
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
