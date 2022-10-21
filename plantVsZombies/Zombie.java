/*
Zombi: Zombi de jardín común, no tiene nada en especial.
 */
package plantVsZombies;

public class Zombie {
    protected char id; // char con el que se va identificar por pantalla
    protected int x;
    protected int y;
    protected int vida;
    protected int danio; // el daño que realiza al atacar
    protected boolean ralentizado; // si ha sido ralentizado

    public Zombie(int coorX) {
        this.id = 'Z';
        this.x = coorX;
        this.y = 9; //aparece en el extremo derecho
        this.vida = 100;
        this.danio = 25;
        this.ralentizado = false;
    }

    public char getId() {
        return this.id;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getVida() {
        return this.vida;
    }

    public int getDanio() {
        return this.danio;
    }

    public boolean isRalentizado() {
        return this.ralentizado;
    }
}
