/*
• Petacereza: Provoca una explosión en un área de 3x3 capaz de matar a casi cualquier zombi. Tiene
un coste de 150 soles.
 */
package plantsVsZombies;

public class Petacereza extends Planta {
    
    protected int rangoX=3; //uno adelante y uno atras
    protected int rangoY=3; 
    public Petacereza(int x, int y){
        super(x,y);
        //rango
        this.danio=150;
        this.id='C';
    }
    @Override
    public void atacar(Planta p, Tablero t){
        int x = p.x;
        int y=p.y;
        int limInfX, limInfY, limSupX, limSupY;
        if ((x-1)<0){
            limInfX=x;
        }
        else{
            limInfX=x-1;
        }
        if ((x + 1) > 4) {
            limSupX = x;
        }
        else {
            limSupX = x + 1;
        }
        if ((y-1)<0){
            limInfY=y;
        }
        else {
            limInfY=y-1;
        }
        if ((y+1)<0){
            limSupY=y;
        }
        else{
            limSupY=y+1;
        }
        
        int i,j;
        i=limInfX;
        while (i<=limSupX){
            j=limInfY;
            while (j<=limSupY){
                if (t.tableroZ[i][j]!=null){
                    t.tableroZ[i][j].setVida(t.tableroZ[i][j].getVida()- p.danio);
                }
                System.out.println(p.id +" le hizo "+p.danio+ " daño a: " + t.tableroZ[i][j].id);
                if (t.tableroZ[i][j].vida==0){
                    System.out.println(t.tableroZ[i][j].id + " Ha muerto");
                    t.tableroZ[i][j]=null;
                }
                else {
                    System.out.println("Vida de: "+ t.tableroZ[i][j].id + " : " + t.tableroZ[i][j].vida);
                }
                j++;
            }
            i++;
        }
        t.tableroP[x][y]=null;
    }
          
  
    
}
