/*
Zombi Caracubo: Su cubo lo hace el más resistente de los zombis comunes y corriente. Magnetoseta
puede quitarle el cubo.
 */

package plantsVsZombies;
public class Caracubo extends Zombie {
    public Caracubo (int coorX) {
        super(coorX);
        this.vida = 200;
        this.id = 'B'; //por Bucket
    }
}
