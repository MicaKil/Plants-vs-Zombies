/*
• Lanzaguisantes: Dispara guisantes de uno en uno a los zombis una vez que entran en su carril. Tiene
un coste de 100 soles.
 */
package plantasvszombies;

public class Lanzaguisantes extends Planta {
    public Lanzaguisantes(int x, int y){
        super(x,y);
        this.id='L';
    }
    
    //hacer desde tablero que llame a disparar? 
    private void Disparar(){
        System.out.println("Hago: " + this.danio);
        //puede retornar el daño y que se use para restarlo al zombie
        //puede buscar el zombie mas cercano y restarle el daño (entrando al array en la fila x y el primer objeto zombie le afecta)
    }   
    
    //método morir? 
    
    //metodo generar sol, aumenta la cantidad de sol del jugador
    
    //agregar clase jugador? 
}
