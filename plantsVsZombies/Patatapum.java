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
        this.id='T';
    }
    
    @Override
    public void atacar(Planta p, Zombie[][] z, Planta[][] t){
        int i = p.x;
        int j=p.y;
        if (this.explotar){
            t[i][j]=null;
            z[i][j].setVida(z[i][j].getVida()-p.danio);
            System.out.println("PUM!");           
        }
        else {
            if (z[i][j+1]!=null && z[i][j+1] instanceof Zombie){
                this.explotar=true;
            }
        
        }
    }
}
    

