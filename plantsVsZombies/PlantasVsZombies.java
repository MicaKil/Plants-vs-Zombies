
package plantasvszombies;

import java.util.Scanner;


public class PlantasVsZombies {

    public static void main(String[] args) {
        
        String opcion;
        Scanner read = new Scanner(System.in);
        
        System.out.println("Bienvenido a Plantas vs Zombies");
        System.out.println("Si desea jugar presione 1");
        System.out.println("Si desea ver 50 movimientos presione 2");
        
        boolean flag=true;
        
        while (flag){
            opcion = read.nextLine();
            try {
                int opcionInt=Integer.parseInt(opcion);
                switch(opcionInt){
                    case 1: System.out.println("Eligió jugar");
                        flag=false;
                        break;
                    case 2: System.out.println("Eligió ver 50 movimientos");
                        flag=false;
                        break;
                    default: System.out.println("No eligió una opción correcta");
                    System.out.println("Si desea jugar presione 1");
                    System.out.println("Si desea ver 50 movimientos presione 2");
                        break;
                }
            }
            catch (NumberFormatException e){
                System.out.println("Debe ingresar un número");
                System.out.println("Si desea jugar presione 1");
                System.out.println("Si desea ver 50 movimientos presione 2");
            }
        }
        

        //Tablero tablero = new Tablero();
        //tablero.mostrarTablero();
        

    }
}
