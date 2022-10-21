/*
• Gasoseta: Expulsa gases alrededor de una área de 3x3 (9 cuadros). Se tiene que plantar sobre
Patatapum. Su coste es de 150 soles.
 */
package plantVsZombies;

public class Gasoseta extends Planta {
    protected int rango;
    public Gasoseta(int x, int y){
        super(x,y);
        this.costo=150;
        this.rango=3;
        //daño del gas? 
        //subclase de patatapum? 
    }
    
}
