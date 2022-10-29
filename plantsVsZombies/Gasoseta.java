/*
• Gasoseta: Expulsa gases alrededor de una área de 3x3 (9 cuadros). Se tiene que plantar sobre
Patatapum. Su coste es de 150 soles.
 */
package plantsVsZombies;

public class Gasoseta extends Planta {
    protected int rango;
    public Gasoseta(int x, int y){
        super(x,y);
        this.costo=150;
        this.rango=3;
        this.danio=20;
        this.id = 'O';
         
    }
    @Override
    public void atacar(Planta p, PlantsVsZombies juego){
        Tablero t = juego.tablero;
        int x = p.x;
        int y=p.y;
        int limInfX, limInfY, limSupX, limSupY;
        if ((x-1)<0){
            limInfX=x;
        }
        else{
            limInfX=x-1;
        }
        if ((x + 1) > 4) {
            limSupX = x;
        }
        else {
            limSupX = x + 1;
        }
        if ((y-1)<0){
            limInfY=y;
        }
        else {
            limInfY=y-1;
        }
        if ((y+1)<0){
            limSupY=y;
        }
        else{
            limSupY=y+1;
        }
        
        int i,j;
        i=limInfX;
        while (i<=limSupX){
            j=limInfY;
            while (j<=limSupY){
                if (t.tableroZ[i][j]!=null){
                    int vidaActual = t.tableroZ[i][j].getVida() - this.danio;
                    t.tableroZ[i][j].setVida(vidaActual);
                    System.out.printf("- La gasoseta en la posición (%d,%d) le hizo %d de daño a: '%s' en la posición (%d,%d).\n",
                            p.getX() + 1, p.getY() + 1, p.getDanio(), t.tableroZ[i][j].getId(), i + 1, j + 1);
                    
                    if (t.tableroZ[i][j].vida <= 0) {
                        System.out.println("  - El zombie '" + t.tableroZ[i][j].getId() + "' ha muerto x_x");
                        t.tableroZ[i][j] = null;
                    }
                    else {
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
                
                j++;
            }
            i++;
        }
    }
}
