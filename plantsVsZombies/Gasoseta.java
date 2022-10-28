/*
• Gasoseta: Expulsa gases alrededor de una área de 3x3 (9 cuadros). Se tiene que plantar sobre
Patatapum. Su coste es de 150 soles.
 */
package plantsVsZombies;

public class Gasoseta extends Planta {
    protected int rango;
    public Gasoseta(int x, int y){
        super(x,y);
        this.costo=150;
        this.rango=3;
        this.danio=20;
        this.id = 'O';
         
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
    }
}
