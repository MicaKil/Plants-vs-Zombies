/*
• Hielaguisantes: Lanza guisantes helados que ralentizan a los zombis, mas no los congela. Tiene un
coste de 175 soles.
 */
package plantsVsZombies;
//poner como subclase? --> tal vez no pq no necesita un lanzaguiasntes de base

public class Hielaguisantes extends Planta {
    private boolean realentiza;
    
    public Hielaguisantes(int x, int y){
        super(x,y);
        this.danio=175;
        this.realentiza=true;
        this.id='H';
        
    }
    
    @Override
    public void atacar(Planta p, PlantsVsZombies juego){
        Tablero t = juego.tablero;
        boolean foundZombie=false;
        int i = p.x;
        int j=p.y;
        while (!foundZombie){
            //si encuentra un zombie, le hace daño y sale del bucle 
            if (t.tableroZ[i][j]!=null){
                t.tableroZ[i][j].setVida(t.tableroZ[i][j].getVida()- p.danio);
                t.tableroZ[i][j].setRalentizado(true);
                foundZombie=true;
                System.out.println(p.id +" le hizo "+p.danio+ " daño a: " + t.tableroZ[i][j].id);
                if (t.tableroZ[i][j].vida==0){
                    System.out.println(t.tableroZ[i][j].id + " Ha muerto");
                    t.tableroZ[i][j]=null;
                }
                else {
                    System.out.println("Vida de: "+ t.tableroZ[i][j].id + " : " + t.tableroZ[i][j].vida);
                }
            }
            else {
                if (j<9){
                    j++;
                }
                else{
                    foundZombie=true;
                }
                
            }
        }
    }
}
