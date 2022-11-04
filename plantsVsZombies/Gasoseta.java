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

    /*
    Ataca a los zombies en un rango de 3x3
    usa el tablero de zombies*/
    @Override
    public void atacar(Planta p, Juego juego){
        Jardin t = juego.jardin;
        int x = p.x;
        int y=p.y;
        //revisa si está en alguna esquina y guarda el limite de movimiento que tiene la planta
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
        //avanza desde la posicion anterior (o la que está según el limite indicado anteriormente)
        while (i<=limSupX){
            j=limInfY;
            while (j<=limSupY){
                if (t.jardinZ[i][j]!=null){
                    //si encuentra un zombie en el tablero de zombies le resta vida
                    int vidaActual = t.jardinZ[i][j].getVida() - this.danio;
                    t.jardinZ[i][j].setVida(vidaActual);
                    System.out.printf("- La gasoseta en la posición (%d,%d) le hizo %d de daño a: '%s' en la posición (%d,%d).\n",
                            p.getX() + 1, p.getY() + 1, p.getDanio(), t.jardinZ[i][j].getId(), i + 1, j + 1);
                    //si la vida baja a 0 o de 0 el zombie muere y se elimina del tablero de zombies
                    if (t.jardinZ[i][j].vida <= 0) {
                        System.out.println("  - El zombie '" + t.jardinZ[i][j].getId() + "' ha muerto x_x");
                        t.jardinZ[i][j] = null;
                    }
                    else {
                        //cambia los id de los zombies por si eran caracono o caracubo, ataca de la misma forma que a los otros
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
    }
}
