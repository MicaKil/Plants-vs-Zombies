package plantVsZombies;

public class Lector extends Zombie {
    boolean tienePeriodico;
    public Lector(int coorX) {
        super(coorX);
        this.id = 'L';
        this.tienePeriodico = true;
    }
}
