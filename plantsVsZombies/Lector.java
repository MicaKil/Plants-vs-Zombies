/*
Zombi Lector: Es un zombi que lleva un periódico. Cuando destruyen su periódico se enoja y salta
hasta la planta para comer a tus plantas.
 */
package plantsVsZombies;

public class Lector extends Zombie {
    public Lector(int coorX) {
        super(coorX);
        this.id = 'l';
        this.vida = 125;
    }
    @Override
    protected void caminar(Jardin jardin, Juego juego, Zombie zombie)  {
        int i = zombie.getX();
        int j = zombie.getY();
        if (zombie.getVida() > 100) {
            if (j > 0 && jardin.jardinZ[i][j - 1] == null && jardin.jardinP[i][j - 1] == null) {
                zombie.setY(j - 1); //cambiamos la coor 'y' del zombie
                jardin.jardinZ[i][j - 1] = jardin.jardinZ[i][j]; //lo movemos en el tablero
                jardin.jardinZ[i][j] = null; //borramos donde estaba antes
                // si al caminar queda al lado de una planta...
                if ((j - 2) >= 0 && jardin.jardinP[i][j - 2] != null) {
                    jardin.jardinZ[i][j - 1].setAtacando(true); //deja de caminar porque va a estar atacando
                }
            } else if (j == 0) { // si el zombie se encuentra en el limite derecho
                jardin.jardinZ[i][j] = null; //borramos donde estaba antes
                System.out.println("Se ha pasado un zombie! (>_<)");
                juego.setVidas(juego.getVidas() - 1);
            }
        } else {
            saltar(jardin, juego, zombie);
            System.out.println("  - El zombie 'l' ha perdido su periódico! Su nuevo ID es 'z'. ");
        }
    }

    private static void saltar(Jardin jardin, Juego juego, Zombie zombie) {
        int i = zombie.getX();
        int j = zombie.getY();
        int k = j - 1;
        boolean encontroLugar = false;
        while (k >= 0 && jardin.jardinP[i][k] == null && jardin.jardinZ[i][k] == null) {
            encontroLugar = true;
            k--;
        }
        if (k < 0) { // si se sale del tablero
            jardin.jardinZ[i][j] = null; //lo borramos de la posicion anterior
            System.out.println("Se ha pasado un zombie! (>_<)");
            juego.setVidas(juego.getVidas() - 1);
        } else {
            if (!encontroLugar) { // si no encontró lugar
                k = j; // se queda donde está
            } else {
                k++;
                System.out.printf("- El zombie 'l' en la posición (%d,%d) ha saltado a la posición (%d,%d)! \n", i + 1, j + 1, i + 1, k + 1);
            }
            Zombie nuevoZ = new Zombie(i); // creamos al nuevo zombie
            nuevoZ.setVida(zombie.getVida());
            nuevoZ.setY(k);
            if (jardin.jardinP[i][k - 1] != null) {
                nuevoZ.setAtacando(true); }
            jardin.jardinZ[i][j] = null; //lo borramos de la posicion anterior
            jardin.jardinZ[i][k] = nuevoZ;
        }
    }
}
