/*
• Nuez: Bloquea el paso de los zombis hasta ser devorada, aunque hay zombis que pueden saltarla --> que zombies?.
Tiene un coste de 50 soles.
 */
package plantsVsZombies;
/// borraría la clase y haría como en caracano y caracububo
public class Nuez extends Planta {
    public Nuez(int x, int y){
        super(x,y);
        this.costo=50;
        this.danio=0;
        this.vida=200; //probar
        this.id='N';
    }
    
    @Override
    public void atacar(Planta p, Juego juego){
        //no hace nada
    }    
}
