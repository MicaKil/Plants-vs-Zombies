/*
• Patatapum: Una mina de papa que explota cuando un zombi la pisa, tarda un poco en activarse.
Tiene un coste de 25 soles.
 */
package plantsVsZombies;

public class Patatapum extends Planta {
    protected int tiempoActivacion=1; //1 turno para que explote
    protected boolean explotar=false;
    public Patatapum(int x, int y){
        super(x,y);
        this.costo=25;
        this.danio=75; 
        this.id='P';
    }
    
    @Override
    public void atacar(Planta p, PlantsVsZombies juego){
        Tablero t = juego.tablero;
        int i = p.x;
        int j=p.y;
        if (this.explotar){
            //lo elimino del tablero para no tener problemas con los char
            t.tableroP[i][j]=null;
            juego.setCantPatatapum(juego.getCantPatatapum() - 1); // se resta en uno la cantidad actual
            t.tableroZ[i][j].setVida(t.tableroZ[i][j].getVida()-p.danio);

            System.out.println("PUM!");           
        }
        else {
            //se activa si hay un zombie adelante
            if (t.tableroZ[i][j+1]!=null && t.tableroZ[i][j + 1] != null){
                this.explotar=true;
            }
        
        }
    }
}
    

