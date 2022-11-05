/*
Patatapum: Una mina de papa que explota cuando un zombi la pisa, tarda un poco en activarse.
Tiene un coste de 25 soles.
 */
package plantsVsZombies;

public class Patatapum extends Planta {
    protected boolean explotar=false;
    public Patatapum(int x, int y){
        super(x,y);
        this.costo=25;
        this.danio=75; 
        this.id='P';
    }
    /*el primer turno con zombie delante no hace nada, el segundo turno explota*/
    @Override
    public void atacar(Planta p, Juego juego){
        Jardin t = juego.jardin;
        int i = p.getX();
        int j=p.getY() ;
        if (this.explotar){
            //se elimina del tablero 
            t.jardinP[i][j]=null;
            juego.setCantPatatapum(juego.getCantPatatapum() - 1); // se resta en uno la cantidad actual
            //hace daño al zombie de la posición j+1 de la misma linea (en frente/a la derecha)
            int vidaActual = t.jardinZ[i][j + 1].getVida() - this.danio;
            t.jardinZ[i][j + 1].setVida(vidaActual);
            System.out.printf("- El patatapum en la posición (%d,%d) le hizo %d de daño a: '%s' en la posición (%d,%d).\n",
                    i + 1, j + 1, this.danio, t.jardinZ[i][j + 1].getId(), i + 1, j + 2);
            //si muere el zombie avisa
            if (t.jardinZ[i][j + 1].vida <= 0) {
                System.out.println("  - El zombie '" + t.jardinZ[i][j + 1].getId() + "' ha muerto x_x");
                juego.setTotalZombies(juego.getTotalZombies() - 1);
                t.jardinZ[i][j + 1] = null;
            } else {
                //detalles de caracono y caracubo
                t.jardinZ[i][j + 1].setAtacando(false); // deja de atacar
                if (t.jardinZ[i][j + 1].getId() == 'c' && vidaActual <= 100) {
                    t.jardinZ[i][j + 1].setId('z');
                    System.out.printf("  - El zombie 'c' ha perdido su cono! Su nuevo ID es 'z'. Vida actual: %d. \n", vidaActual);
                } else if (t.jardinZ[i][j + 1].getId() == 'b' && vidaActual <= 100) {
                    t.jardinZ[i][j + 1].setId('z');
                    System.out.printf("  -  El zombie 'b' ha perdido su cubeta! Su nuevo ID es 'z'. Vida actual: %d. \n", vidaActual);
                } else {
                    System.out.println("  - Vida del zombie '" + t.jardinZ[i][j + 1].getId() + "' : " + vidaActual);
                }
            }
            System.out.println("PUM!");           
        }
        else {
            //se activa si hay un zombie adelante
            if (t.jardinZ[i][j + 1]!=null && t.jardinZ[i][j + 1] != null){
                this.explotar=true;
            }
        
        }
    }
}
    

