/*
Zombi Lector: Es un zombi que lleva un periódico. Cuando destruyen su periódico se enoja y salta
hasta la planta para comer a tus plantas.
 */
package plantVsZombies;

public class Lector extends Zombie {
    boolean tienePeriodico;
    public Lector(int coorX) {
        super(coorX);
        this.id = 'L';
        this.tienePeriodico = true;
    }
}
