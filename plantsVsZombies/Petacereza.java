/*
• Petacereza: Provoca una explosión en un área de 3x3 capaz de matar a casi cualquier zombi. Tiene
un coste de 150 soles.
 */
package plantsVsZombies;

public class Petacereza extends Planta {
    public Petacereza(int x, int y){
        super(x,y);
        //rango
        this.danio=150;
        this.id='C';
    }
    
}
