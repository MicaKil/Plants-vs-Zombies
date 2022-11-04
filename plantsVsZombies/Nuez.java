/*
Nuez: Bloquea el paso de los zombis hasta ser devorada, aunque hay zombis que pueden saltarla --> que zombies?.
Tiene un coste de 50 soles.
 */
package plantsVsZombies;
public class Nuez extends Planta {
    public Nuez(int x, int y){
        super(x,y);
        this.costo=50;
        this.danio=0;
        this.vida=200; //probar
        this.id='N';
    }
    
    @Override
    public void atacar(Planta p, Juego juego){ //bloquea el paso del saltador
        int x = p.getX();
        int y = p.getY();
        Jardin j = juego.jardin;

        if (j.jardinZ[x][y + 1] != null && j.jardinZ[x][y + 1].getId() == 's') {
            System.out.printf("- La nuez en (%d,%d) ha bloqueado el salto del zombie a su derecha! Su nuevo ID es 'z'.\n", x + 1, y + 1);
            // creamos al nuevo zombie
            Zombie zombie = j.jardinZ[x][y + 1];
            Zombie nuevoZ = new Zombie(x); // creamos al nuevo zombie
            nuevoZ.setVida(zombie.getVida());
            nuevoZ.setY(y + 1);
            nuevoZ.setAtacando(true); //al estar al lado de la nuez ataca
            j.jardinZ[x][y + 1] = nuevoZ;

        }
    }    
}
