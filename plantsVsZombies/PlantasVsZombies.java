
package plantasvszombies;

import java.util.Scanner;


public class PlantasVsZombies {

    public static void main(String[] args) {
        
        String opcion;
        Scanner read = new Scanner(System.in);
        System.out.println("Bienvenido a Plantas vs Zombies");
        
        boolean flag=true;
        while (flag){
            System.out.println("Si desea jugar presione 1");
            System.out.println("Si desea ver 50 movimientos presione 2");
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
                        break;
                }
            }
            catch (NumberFormatException e){
                System.out.println("Debe ingresar un número");
            }
        }
        
        //Modelado del juego con el usuario participando
        System.out.println("Reglas: "); //fijarse de usar archivo? 
        Tablero T = new Tablero();
        T.mostrarTablero();
        
        System.out.println("Tiene: " + T.getSoles() + " soles disponibles para comprar"); 
        //en el primer turno solo puede comprar girasoles
        System.out.println("Si desea comprar plantas ingrese el número correspondiente");
        //usar getters para los precios?
        //poner opcion de ve que hace cada una? 
        //agregar opcion para cancelar, volver a elegir coordenada, salir del menu de compra, si tiene menos de 25 soles no comprar
        flag=true;
        try {
            String compra = read.nextLine();
            //mostrar solo las que les alcanza?
            System.out.println("1: Girasol - Costo: 50 soles" + "\n2: Lanzaguisantes - Costo: 100 soles" 
                + "\n3: Repetidora - Costo: 200 soles" + "\n4: Hielaguisantes - Costo: 175 soles" + 
                "\n5: Nuez - Costo: 50 soles" + "\n6: Patatapum - Costo: 25 soles" + "\n7: Petacereza - Costo: 150 soles");
                int compraInt=Integer.parseInt(compra);
                switch(compraInt){
                    case 1: 
                        if (T.getSoles()<50){
                            System.out.println("No le alcanza para comprar un Girasol");
                        }
                        else {
                            System.out.println("Eligió el Girasol");
                            System.out.println("Ingrese la fila del girasol (fila= de 1 a 5)");
                            String fila = read.nextLine();
                            System.out.println("Ingrese la columna del girasol (fila= de 1 a 10)");
                            String columna = read.nextLine();
                            //hacer try catch aca tambien? :c
                            Girasol g = new Girasol(Integer.parseInt(fila),Integer.parseInt(columna));
                            T.setSoles(T.getSoles()-50);
                            flag=false;
                        }
                        break;
                    
                    case 2: 
                        if (T.getSoles()<100) {
                            System.out.println("No le alcanza para comprar el Lanzaguisantes");
                        }
                        else {
                            System.out.println("Eligió el Lanzaguisantes");
                            T.setSoles(T.getSoles()-100);
                            flag=false;
                        }
                        break;
                   
                    case 3: 
                        if (T.getSoles()<200) {
                            System.out.println("No le alcanza para comprar la Repetidora");
                        }
                        else {
                            System.out.println("Eligió la Repetidora");
                            T.setSoles(T.getSoles()-200);
                            flag=false;
                        }
                        break;
                    
                    case 4: 
                        if (T.getSoles()<175) {
                            System.out.println("No le alcanza para comprar el Hielaguisantes");
                        } 
                        else {
                            System.out.println("Eligió el Hielaguisantes");
                            T.setSoles(T.getSoles()-175);
                            flag=false;
                        }
                        break;
                    
                    case 5: 
                        if (T.getSoles()<100) {
                            System.out.println("No le alcanza para comprar la Nuez");
                        }
                        else {
                            System.out.println("Eligió la Nuez");
                            T.setSoles(T.getSoles()-50);
                            flag=false;
                        }
                        break;
                    
                    case 6: 
                        if (T.getSoles()<100) {
                            System.out.println("No le alcanza para comprar el Patatapum");
                        }
                        else {
                            System.out.println("Eligió el Patatapum");
                            T.setSoles(T.getSoles()-25);
                            flag=false;
                        }
                        break;
                    
                    case 7: 
                        if (T.getSoles()<100) {
                            System.out.println("No le alcanza para comprar la Petacereza");
                        }   
                        else {
                            System.out.println("Eligió la Petacereza");
                            T.setSoles(T.getSoles()-150);
                            flag=false;
                        }
                        break;
                    
                    default: System.out.println("No eligió una opción correcta");
                        break;
                }
            }
            catch (NumberFormatException e){
                System.out.println("Debe ingresar un número");
            }
        
        
        
        /*Zombie zombie = new Abanderado(9);
        
        T.crearZombie();
        
        T.mostrarTableroZ();*/

        

    }
}
