/*
tienen distinta vida los distintos lanzaguisantes?? 
 */
package plantsVsZombies;
public class Planta {
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
    public void ataque(Planta p, PlantsVsZombies juego){
        boolean foundZombie=false;
        int i = p.x;
        int j=p.y;
        Tablero t = juego.tablero;
        while (!foundZombie){
            //si encuentra un zombie, le hace daño y sale del bucle 
            if (t.tableroZ[i][j]!=null){
                t.tableroZ[i][j].setVida(t.tableroZ[i][j].getVida()- p.danio);
                foundZombie=true;
                if (p.danio > 0) {
                    System.out.println(p.id + " le hizo " + p.danio + " daño a: " + t.tableroZ[i][j].id);
                }
                if (t.tableroZ[i][j].vida==0){
                    System.out.println(t.tableroZ[i][j].id + " ha muerto x_x");
                    t.tableroZ[i][j]=null;
                }
                else if (p.danio > 0){
                    System.out.println("Vida de: "+ t.tableroZ[i][j].id + " : " + t.tableroZ[i][j].vida);
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
    public void atacar(Planta p, Tablero t){
        boolean foundZombie=false;
        int i = p.x;
        int j=p.y;
        while (!foundZombie){
            //si encuentra un zombie, le hace daño y sale del bucle 
            if (t.tableroZ[i][j]!=null){
                t.tableroZ[i][j].setVida(t.tableroZ[i][j].getVida()- p.danio);
                foundZombie=true;
                if (p.danio > 0) {
                    System.out.println(p.id + " le hizo " + p.danio + " daño a: " + t.tableroZ[i][j].id);
                }
                if (t.tableroZ[i][j].vida==0){
                    System.out.println(t.tableroZ[i][j].id + " ha muerto x_x");
                    t.tableroZ[i][j]=null;
                }
                else if (p.danio > 0){
                    System.out.println("Vida de: "+ t.tableroZ[i][j].id + " : " + t.tableroZ[i][j].vida);
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

    
             