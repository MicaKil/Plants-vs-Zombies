/*
• Repetidora: Es como el lanzaguisantes, con la diferencia de que dispara 2 guisantes a la vez. Tiene
un coste de 200 soles
 */
package plantsVsZombies;
//poner como subclase de lanzaguisantes? --> tal vez no pq no necesita un lanzaguisantes de base
public class Repetidora extends Planta {
    public Repetidora(int x, int y){
        super(x,y);
        this.costo=200;
        this.danio=50;
        this.id='R';
    }
}
