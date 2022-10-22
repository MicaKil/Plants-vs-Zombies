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
        this.id='P';
        this.vida=100;
        this.danio=25;
        this.costo=100;
        this.x=x;
        this.y=y;
    }
    

    
}
