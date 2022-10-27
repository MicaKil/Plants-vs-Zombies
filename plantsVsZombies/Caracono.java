/*
Zombi Caracono: Su cono lo hace un poco más resistente que el zombi básico.
 */

package plantsVsZombies;
public class Caracono extends Zombie {
    public Caracono(int coorX) {
        super(coorX);
        this.id = 'c';
        this.vida = 150;
    }

}
