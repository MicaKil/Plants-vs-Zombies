/*
• Guisantralladora: Dispara cuatro guisantes a la vez. Para plantarla en el patio se necesita estar
plantada sobre una Repetidora. Su coste es de 250 soles.
 */
package plantVsZombies;

//poner como subclase pq necesita al lanzaguisantes para plantar
public class Guisantralladora extends Planta {
    protected boolean predecesor; //plantear bien esta exigencia
    public Guisantralladora(int x, int y){
        super(x,y);
        this.danio=200;
        this.costo=250;
    }
}
