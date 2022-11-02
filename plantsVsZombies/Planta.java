/*
clase abstracta de las plantas, implementa interfaz ataque para sus ataques
 */
package plantsVsZombies;
public abstract class Planta implements Ataques { 
    protected char id; //nombre que aparece en la pantalla
    protected int vida;
    protected int danio;
    protected int costo;
    protected int x; 
    protected int y;
    
    public Planta(int x, int y){
        this.vida=100;
        this.danio=25;
        this.costo=100;
        this.x=x;
        this.y=y;
    }
    
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

