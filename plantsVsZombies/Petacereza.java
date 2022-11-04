/*
Petacereza: Provoca una explosión en un área de 3x3 capaz de matar a casi cualquier zombi. Tiene
un coste de 150 soles.
 */
package plantsVsZombies;

public class Petacereza extends Planta {
    
    public Petacereza(int x, int y){
        super(x,y);
        this.danio=150;
        this.id='C';
        this.costo=150;
    }
    
    //hace 150 de daño a cada zombie que esté dentro del rango 3x3
    @Override
    public void atacar(Planta p, Juego juego){
        Jardin t = juego.jardin;
        int x = p.x;
        int y=p.y;
        //verifica limites para no salirse del tablero
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
                if (t.jardinZ[i][j]!=null){
                    //si encuentra un zombie le hace daño y avisa
                    int vidaActual = t.jardinZ[i][j].getVida() - this.danio;
                    t.jardinZ[i][j].setVida(vidaActual);
                    System.out.printf("- La petacereza en la posición (%d,%d) le hizo %d de daño a: '%s' en la posición (%d,%d).\n",
                            p.getX() + 1, p.getY() + 1, p.getDanio(), t.jardinZ[i][j].getId(), i + 1, j + 1);
                    //si el zombie se queda sin vida avisa y lo elimina del tablero
                    if (t.jardinZ[i][j].vida <= 0) {
                        System.out.println("  - El zombie '" + t.jardinZ[i][j].getId() + "' ha muerto x_x");
                        juego.setTotalZombies(juego.getTotalZombies() - 1);
                        t.jardinZ[i][j] = null;
                    } else {
                        //detalles de caracono y caracubo 
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
                j++;
            }
            i++;
        }
        //una vez que explota se elimina del tablero de plantas
        t.jardinP[x][y]=null;
    }
}
