/*
• Girasol: Produce soles con un valor de 25 para que puedas sembrar otras plantas, su producción de
soles demora en los niveles nocturnos. Tiene un coste de 50 soles.
• Birasol: Proporciona el doble de soles que el Girasol. Se necesita estar plantada sobre un Girasol.
Su coste inicial de 150 soles.
 */
package plantasvszombies;


public class Girasol extends Planta {
    protected int generaSol;
    //demora en nivel nocturno
    
    public Girasol (int x, int y){
        super(x,y);
        this.generaSol=25;
        this.costo=50;
        this.danio=0;
        this.id='G';
        
    }
}
