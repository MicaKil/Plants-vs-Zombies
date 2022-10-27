/*
Zombi: Zombi de jardín común, no tiene nada en especial.
 */
package plantsVsZombies;

public class Zombie {
    protected char id; // char con el que se va identificar por pantalla
    protected int x;
    protected int y;
    protected int vida;
    protected int danio; // el daño que realiza al atacar
    protected boolean ralentizado; // si ha sido ralentizado

    protected boolean atacando;

    public Zombie(int coorX) {
        this.id = 'z';
        this.x = coorX;
        this.y = 9; //aparece en el extremo derecho
        this.vida = 100;
        this.danio = 25;
        this.ralentizado = false;
        this.atacando = false;
    }

    protected void avanzar(Tablero tablero, PlantsVsZombies juego, Zombie zombie){
        int i, j;
        i = zombie.getX();
        j = zombie.getY();
        if (zombie.ralentizado) { // si estaba ralentizado...
            zombie.ralentizado = false; // se lo desralentiza
        } else { // si no
            if (zombie.atacando) { //si el zombie está atacando
                tablero.tableroP[i][j - 1].vida -= tablero.tableroZ[i][j].danio; // le quita vida a la planta
                //System.out.println("vida planta " + tablero.tableroP[i][j-1].vida);
                if (tablero.tableroP[i][j - 1].vida <= 0) { // si mata a la planta
                    tablero.tableroP[i][j - 1] = null; //la eliminamos
                    System.out.println("Un zombie ha comido a una planta!");
                    zombie.atacando = false;
                }
            } else { //si no está atacando...
                // si no hay nada adelante avanza
                if (j > 0 && tablero.tableroZ[i][j - 1] == null && tablero.tableroP[i][j - 1] == null) {
                    zombie.y -= 1; //cambiamos la coor y del zombie
                    tablero.tableroZ[i][j - 1] = tablero.tableroZ[i][j]; //lo movemos en el tablero
                    tablero.tableroZ[i][j] = null; //borramos donde estaba antes
                    // si al caminar queda al lado de una planta...
                    if ((j - 2) >= 0 && tablero.tableroP[i][j - 2] != null) {
                        tablero.tableroZ[i][j - 1].atacando = true; //deja de caminar porque va a estar atacando
                    }
                } else if (j == 0) { // si el zombie se encuentra en el limite derecho
                    tablero.tableroZ[i][j] = null; //borramos donde estaba antes
                    System.out.println("Se ha pasado un zombie! (>_<)");
                    juego.vidas--;
                }
            }
        }
    }


    // Getters y setters

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
    public void setVida(int vida) {        this.vida = vida;}
    public int getDanio() {
        return this.danio;
    }
    public void setRalentizado(boolean ralentizado) {
        this.ralentizado = ralentizado;
    }
}
