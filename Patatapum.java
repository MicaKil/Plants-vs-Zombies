/*
• Patatapum: Una mina de papa que explota cuando un zombi la pisa, tarda un poco en activarse.
Tiene un coste de 25 soles.
 */
package plantasvszombies;


public class Patatapum extends Planta {
    protected int tiempoActivacion; //1 turno?
    public Patatapum(int x, int y){
        super(x,y);
        this.costo=25;
        //tiene vida?
        this.danio=50; //cuanto daño hace? en la wiki dice 1800 
        //hace daño solo en el cuadro? (x,y) o en un rango o linea? 
    }
    
}
