/*
• Birasol: Proporciona el doble de soles que el Girasol. Se necesita estar plantada sobre un Girasol.
Su coste inicial de 150 soles.
*/
package plantasvszombies;
//poner como subclase de girasol? 
public class Birasol extends Planta {
    protected int generaSol;
    protected boolean predecesor; //plantear bien exigencia
    public Birasol (int x, int y){
        super(x,y);
        this.generaSol=50;
        this.costo=150;
        this.danio=0;
        
    }
    
}