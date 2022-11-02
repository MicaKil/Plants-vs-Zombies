/*
tienen distinta vida los distintos lanzaguisantes?? buena pregunta
 */
package plantsVsZombies;
public abstract class Planta implements Ataques {  //// PODRÍA SER UNA CLASE ABSTRACTA
    protected char id; //nombre que aparece en la pantalla
    protected int vida;
    protected int danio;
    protected int costo;
    protected int x; 
    protected int y;
    //protected String predecesor; 
    //max coorY
    //max coorX
    
    public Planta(int x, int y){
        this.vida=100;
        this.danio=25;
        this.costo=100;
        this.x=x;
        this.y=y;
    }
    
    
    //recibir el juego desde planta para hacer juego.tablero.setsoles y que no se rompa
    
    @Override
    public abstract void atacar(Planta p, PlantsVsZombies juego);
    
    

    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDanio() {
        return danio;
    }

    public void setDanio(int danio) {
        this.danio = danio;
    }

    public int getCosto() {
        return costo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
    
}

