/*
• Hielaguisantes: Lanza guisantes helados que ralentizan a los zombis, mas no los congela. Tiene un
coste de 175 soles.
 */
package plantsVsZombies;
//poner como subclase? --> tal vez no pq no necesita un lanzaguiasntes de base

public class Hielaguisantes extends Planta {
    protected boolean realentiza;
    
    public Hielaguisantes(int x, int y){
        super(x,y);
        this.danio=175;
        this.realentiza=true;
        this.id='H';
        
    }
}
