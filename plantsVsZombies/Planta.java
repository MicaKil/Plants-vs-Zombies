/*
tienen distinta vida los distintos lanzaguisantes?? buena pregunta
 */
package plantsVsZombies;
public class Planta {  //// PODRÍA SER UNA CLASE ABSTRACTA
    protected char id; //nombre que aparece en la pantalla
    protected int vida;
    protected int danio;
    protected int costo;
    protected int x; 
    protected int y;
    //protected String predecesor; 
    //max coorY
    //max coorX
    
    public Planta(int x, int y){
        this.vida=100;
        this.danio=25;
        this.costo=100;
        this.x=x;
        this.y=y;
    }
    //recibir el juego desde planta para hacer juego.tablero.setsoles y que no se rompa
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
                    System.out.printf("- La planta '%s' en la posición (%d,%d) le hizo %d de daño a: '%s' en la posición (%d,%d).\n",
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
    

    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDanio() {
        return danio;
    }

    public void setDanio(int danio) {
        this.danio = danio;
    }

    public int getCosto() {
        return costo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}


