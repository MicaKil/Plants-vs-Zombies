
package plantsVsZombies;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public Menu() {}
    public void mostrarMenu(PlantsVsZombies juego, Tablero tablero) {
        //Modelado del juego con el usuario participando
        boolean comprarFlag = juego.getSoles() >= 25; //si tiene menos de 25 soles no puede comprar
        boolean flagPlantas;
        Scanner read = new Scanner(System.in);
        //bucle de compra
        while (comprarFlag) {
            //en el primer turno solo puede comprar girasoles
            if (juego.cantMovimientos==0){
                System.out.println("En el primer turno solo puede comprar girasoles");
            }
            //agregar opcion para cancelar, volver a elegir coordenada, salir del menu de compra
            if ((((juego.cantMovimientos)/2)+1)%5==0){
                        System.out.println("Está disponible la Tienda de Crazy Dave! "); // ヽ(・∀・)ﾉ  se ve como ???
                        System.out.println("Para comprar en la tienda, ingrese el menu de compra");
                    }
            System.out.println("Tiene: " + juego.getSoles() + " soles disponibles para comprar.");
            System.out.println("Si desea comprar ingrese S o s. Si desea continuar sin comprar apriete cualquier tecla.");
            String compra = read.nextLine();
            switch (compra) {
                case "S", "s" -> flagPlantas = true; //para iniciar el bucle de compra
                default -> {
                    comprarFlag = false;
                    flagPlantas = false;
                }
            }
            //poner opcion de ver que hace cada una?
            while (flagPlantas) {
                String planta;
                if (juego.cantMovimientos==0){
                    planta="1";
                }
                else{

                    System.out.println("Ingrese el número correspondiente a la planta que desea comprar");
                    System.out.println("""
                            1: Girasol - Costo: 50 soles
                            2: Lanzaguisantes - Costo: 100 soles
                            3: Repetidora - Costo: 200 soles
                            4: Hielaguisantes - Costo: 175 soles
                            5: Nuez - Costo: 50 soles
                            6: Patatapum - Costo: 25 soles
                            7: Petacereza - Costo: 150 soles""");
                    if ((((juego.cantMovimientos)/2)+1)%5==0){
                        System.out.println("8: Tienda de Crazy Dave ║ ” ◕ ◯ ◕ ” ║ ");
                    }
                    System.out.println("9: Salir del menú de compra");
                planta = read.nextLine();
                }
                try {
                    switch (planta) {
                        case "1":
                            if (juego.getSoles() < 50) {
                                System.out.println("No le alcanza para comprar un Girasol");
                            } else {
                                String fila = "";
                                String columna = "";
                                System.out.println("Eligió el Girasol");
                                juego.setSoles(juego.getSoles() - 50);
                                //corroborar que ingresa bien la fila
                                boolean filaFlag = true;
                                while (filaFlag) {
                                    System.out.println("Ingrese la fila del girasol (fila= de 1 a 5)");
                                    fila = read.nextLine();
                                    try {
                                        int filaInt = Integer.parseInt(fila);
                                        switch (filaInt) {
                                            case 1, 2, 3, 4, 5:
                                                System.out.println("Colocará el girasol en la fila: " + filaInt);
                                                filaFlag = false;
                                                break;
                                            default:
                                                System.out.println("Fila inexistente, ingrese de nuevo");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Debe ingresar un número del 1 al 5");
                                    }
                                }
                                //corroborar que ingrese bien la columna
                                boolean columnaFlag = true;
                                while (columnaFlag) {
                                    System.out.println("Ingrese la columna del girasol (columna= del 1 al 10)");
                                    columna = read.nextLine();
                                    try {
                                        int columnaInt = Integer.parseInt(columna);
                                        switch (columnaInt) {
                                            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10:
                                                System.out.println("Colocará el girasol en la columna: " + columnaInt);
                                                columnaFlag = false;
                                                break;
                                            default:
                                                System.out.println("Columna inexistente, ingrese de nuevo");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Debe ingresar un número del 1 al 10");
                                    }
                                }
                                Girasol g = new Girasol(Integer.parseInt(fila) - 1, Integer.parseInt(columna) - 1);
                                //agregar planta al tablero
                                tablero.plantar(g, juego);
                                System.out.println("ID de la planta: G");
                                tablero.mostrarTablero(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "2":
                            if (juego.getSoles() < 100) {
                                System.out.println("No le alcanza para comprar el Lanzaguisantes");
                            } else {
                                String fila = "";
                                String columna = "";
                                System.out.println("Eligió el Lanzaguisantes");
                                juego.setSoles(juego.getSoles() - 100);
                                //corroborar que ingresa bien la fila
                                boolean filaFlag = true;
                                while (filaFlag) {
                                    System.out.println("Ingrese la fila del Lanzaguisantes (fila= de 1 a 5)");
                                    fila = read.nextLine();
                                    try {
                                        int filaInt = Integer.parseInt(fila);
                                        switch (filaInt) {
                                            case 1, 2, 3, 4, 5:
                                                System.out.println("Colocará el Lanzaguisantes en la fila: " + filaInt);
                                                filaFlag = false;
                                                break;
                                            default:
                                                System.out.println("Fila inexistente, ingrese de nuevo");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Debe ingresar un número del 1 al 5");
                                    }
                                }
                                //corroborar que ingrese bien la columna
                                boolean columnaFlag = true;
                                while (columnaFlag) {
                                    System.out.println("Ingrese la columna del Lanzaguisantes (columna= del 1 al 10)");
                                    columna = read.nextLine();
                                    try {
                                        int columnaInt = Integer.parseInt(columna);
                                        switch (columnaInt) {
                                            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10:
                                                System.out.println("Colocará el Lanzaguisantes en la columna: " + columnaInt);
                                                columnaFlag = false;
                                                break;
                                            default:
                                                System.out.println("Columna inexistente, ingrese de nuevo");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Debe ingresar un número del 1 al 10");
                                    }
                                }
                                Lanzaguisantes l = new Lanzaguisantes(Integer.parseInt(fila)-1, Integer.parseInt(columna)-1);
                                //agregar planta al tablero
                                tablero.plantar(l, juego);
                                System.out.println("ID de la planta: L");
                                tablero.mostrarTablero(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "3":
                            if (juego.getSoles() < 200) {
                                System.out.println("No le alcanza para comprar la Repetidora");
                            } else {
                                String fila = "";
                                String columna = "";
                                System.out.println("Eligió la Repetidora");
                                juego.setSoles(juego.getSoles() - 200);
                                //corroborar que ingresa bien la fila
                                boolean filaFlag = true;
                                while (filaFlag) {
                                    System.out.println("Ingrese la fila del Repetidora (fila= de 1 a 5)");
                                    fila = read.nextLine();
                                    try {
                                        int filaInt = Integer.parseInt(fila);
                                        switch (filaInt) {
                                            case 1, 2, 3, 4, 5:
                                                System.out.println("Colocará el Repetidora en la fila: " + filaInt);
                                                filaFlag = false;
                                                break;
                                            default:
                                                System.out.println("Fila inexistente, ingrese de nuevo");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Debe ingresar un número del 1 al 5");
                                    }
                                }
                                //corroborar que ingrese bien la columna
                                boolean columnaFlag = true;
                                while (columnaFlag) {
                                    System.out.println("Ingrese la columna del Repetidora (columna= del 1 al 10)");
                                    columna = read.nextLine();
                                    try {
                                        int columnaInt = Integer.parseInt(columna);
                                        switch (columnaInt) {
                                            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10:
                                                System.out.println("Colocará el Repetidora en la columna: " + columnaInt);
                                                columnaFlag = false;
                                                break;
                                            default:
                                                System.out.println("Columna inexistente, ingrese de nuevo");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Debe ingresar un número del 1 al 10");
                                    }
                                }
                                Repetidora r = new Repetidora(Integer.parseInt(fila)-1, Integer.parseInt(columna)-1);
                                //agregar planta al tablero
                                tablero.plantar(r, juego);
                                System.out.println("ID de la planta: R");
                                tablero.mostrarTablero(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "4":
                            if (juego.getSoles() < 175) {
                                System.out.println("No le alcanza para comprar el Hielaguisantes");
                            } else {
                                String fila = "";
                                String columna = "";
                                System.out.println("Eligió el Hielaguisantes");
                                juego.setSoles(juego.getSoles() - 175);
                                //corroborar que ingresa bien la fila
                                boolean filaFlag = true;
                                while (filaFlag) {
                                    System.out.println("Ingrese la fila del Hielaguisantes (fila= de 1 a 5)");
                                    fila = read.nextLine();
                                    try {
                                        int filaInt = Integer.parseInt(fila);
                                        switch (filaInt) {
                                            case 1, 2, 3, 4, 5:
                                                System.out.println("Colocará el Hielaguisantes en la fila: " + filaInt);
                                                filaFlag = false;
                                                break;
                                            default:
                                                System.out.println("Fila inexistente, ingrese de nuevo");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Debe ingresar un número del 1 al 5");
                                    }
                                }
                                //corroborar que ingrese bien la columna
                                boolean columnaFlag = true;
                                while (columnaFlag) {
                                    System.out.println("Ingrese la columna del Hielaguisantes (columna= del 1 al 10)");
                                    columna = read.nextLine();
                                    try {
                                        int columnaInt = Integer.parseInt(columna);
                                        switch (columnaInt) {
                                            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10:
                                                System.out.println("Colocará el Hielaguisantes en la columna: " + columnaInt);
                                                columnaFlag = false;
                                                break;
                                            default:
                                                System.out.println("Columna inexistente, ingrese de nuevo");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Debe ingresar un número del 1 al 10");
                                    }
                                }
                                Hielaguisantes h = new Hielaguisantes(Integer.parseInt(fila)-1, Integer.parseInt(columna)-1);
                                //agregar planta al tablero
                                tablero.plantar(h, juego);
                                System.out.println("ID de la planta: H");
                                tablero.mostrarTablero(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "5":
                            if (juego.getSoles() < 100) {
                                System.out.println("No le alcanza para comprar la Nuez");
                            } else {
                                String fila = "";
                                String columna = "";
                                System.out.println("Eligió la Nuez");
                                juego.setSoles(juego.getSoles() - 50);
                                //corroborar que ingresa bien la fila
                                boolean filaFlag = true;
                                while (filaFlag) {
                                    System.out.println("Ingrese la fila del Nuez (fila= de 1 a 5)");
                                    fila = read.nextLine();
                                    try {
                                        int filaInt = Integer.parseInt(fila);
                                        switch (filaInt) {
                                            case 1, 2, 3, 4, 5:
                                                System.out.println("Colocará el Nuez en la fila: " + filaInt);
                                                filaFlag = false;
                                                break;
                                            default:
                                                System.out.println("Fila inexistente, ingrese de nuevo");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Debe ingresar un número del 1 al 5");
                                    }
                                }
                                //corroborar que ingrese bien la columna
                                boolean columnaFlag = true;
                                while (columnaFlag) {
                                    System.out.println("Ingrese la columna del Nuez (columna= del 1 al 10)");
                                    columna = read.nextLine();
                                    try {
                                        int columnaInt = Integer.parseInt(columna);
                                        switch (columnaInt) {
                                            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10:
                                                System.out.println("Colocará el Nuez en la columna: " + columnaInt);
                                                columnaFlag = false;
                                                break;
                                            default:
                                                System.out.println("Columna inexistente, ingrese de nuevo");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Debe ingresar un número del 1 al 10");
                                    }
                                }
                                Nuez n = new Nuez(Integer.parseInt(fila)-1, Integer.parseInt(columna)-1);
                                //agregar planta al tablero
                                tablero.plantar(n, juego);
                                System.out.println("ID de la planta: N");
                                tablero.mostrarTablero(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "6":
                            if (juego.getSoles() < 100) {
                                System.out.println("No le alcanza para comprar el Patatapum");
                            } else {
                                String fila = "";
                                String columna = "";
                                System.out.println("Eligió el Patatapum");
                                juego.setSoles(juego.getSoles() - 25);
                                //corroborar que ingresa bien la fila
                                boolean filaFlag = true;
                                while (filaFlag) {
                                    System.out.println("Ingrese la fila del Patatapum (fila= de 1 a 5)");
                                    fila = read.nextLine();
                                    try {
                                        int filaInt = Integer.parseInt(fila);
                                        switch (filaInt) {
                                            case 1, 2, 3, 4, 5:
                                                System.out.println("Colocará el Patatapum en la fila: " + filaInt);
                                                filaFlag = false;
                                                break;
                                            default:
                                                System.out.println("Fila inexistente, ingrese de nuevo");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Debe ingresar un número del 1 al 5");
                                    }
                                }
                                //corroborar que ingrese bien la columna
                                boolean columnaFlag = true;
                                while (columnaFlag) {
                                    System.out.println("Ingrese la columna del Patatapum (columna= del 1 al 10)");
                                    columna = read.nextLine();
                                    try {
                                        int columnaInt = Integer.parseInt(columna);
                                        switch (columnaInt) {
                                            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10:
                                                System.out.println("Colocará el Patatapum en la columna: " + columnaInt);
                                                columnaFlag = false;
                                                break;
                                            default:
                                                System.out.println("Columna inexistente, ingrese de nuevo");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Debe ingresar un número del 1 al 10");
                                    }
                                }
                                Patatapum pt = new Patatapum(Integer.parseInt(fila)-1, Integer.parseInt(columna)-1);
                                //agregar planta al tablero
                                tablero.plantar(pt, juego);
                                System.out.println("ID de la planta: T");
                                tablero.mostrarTablero(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "7":
                            if (juego.getSoles() < 100) {
                                System.out.println("No le alcanza para comprar la Petacereza");
                            } else {
                                String fila = "";
                                String columna = "";
                                System.out.println("Eligió la Petacereza");
                                juego.setSoles(juego.getSoles() - 150);
                                //corroborar que ingresa bien la fila
                                boolean filaFlag = true;
                                while (filaFlag) {
                                    System.out.println("Ingrese la fila del Petacereza (fila= de 1 a 5)");
                                    fila = read.nextLine();
                                    try {
                                        int filaInt = Integer.parseInt(fila);
                                        switch (filaInt) {
                                            case 1, 2, 3, 4, 5:
                                                System.out.println("Colocará el Petacereza en la fila: " + filaInt);
                                                filaFlag = false;
                                                break;
                                            default:
                                                System.out.println("Fila inexistente, ingrese de nuevo");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Debe ingresar un número del 1 al 5");
                                    }
                                }
                                //corroborar que ingrese bien la columna
                                boolean columnaFlag = true;
                                while (columnaFlag) {
                                    System.out.println("Ingrese la columna del Petacereza (columna= del 1 al 10)");
                                    columna = read.nextLine();
                                    try {
                                        int columnaInt = Integer.parseInt(columna);
                                        switch (columnaInt) {
                                            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10:
                                                System.out.println("Colocará el Petacereza en la columna: " + columnaInt);
                                                columnaFlag = false;
                                                break;
                                            default:
                                                System.out.println("Columna inexistente, ingrese de nuevo");
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Debe ingresar un número del 1 al 10");
                                    }
                                }
                                Petacereza p = new Petacereza(Integer.parseInt(fila)-1, Integer.parseInt(columna)-1);
                                //agregar planta al tablero
                                tablero.plantar(p, juego);
                                System.out.println("ID de la planta: C");
                                tablero.mostrarTablero(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "8":
                            boolean flagDave=true;
                            while (flagDave){
                                System.out.println("Bienvenido a la tienda de Crazy Dave! ");
                                System.out.println("Tenemos ofertas muy especiales "); //(✧∀✧) se muestra en pantalla como ???
                                System.out.println("Ingrese el número correspondiente a la planta que quiere comprar");
                                System.out.println("1: Birasol - Costo 150 soles + Girasol"
                                    + "\n2: Gasoseta - Costo 150 soles + Patatapum"
                                    + "\n3: Guisantralladora - Costo 250 soles + Repetidora" );
                                System.out.println("Si no tenés las plantas base necesarias o ya no querés comprar podes salir con E y volver a entrar después de comprar.");
                                String rta= read.nextLine();
                                switch (rta){
                                    case "E", "e":
                                        System.out.println("Saliendo de la tienda de Crazy Dave");
                                        flagDave=false;
                                        break;
                                    case "1":
                                        //buscar donde hay girasoles y darle solo esas opciones
                                        System.out.println("Eligió Birasol");
                                        int[] posicion = new int[2];
                                        ArrayList<int[]> arrayPosiciones = new ArrayList<int[]>();
                                        for (int i=0; i<5; i++){
                                            for (int j=0; j<10;j++){
                                                if ((tablero.tableroP[i][j]!=null) && (tablero.tableroP[i][j] instanceof Girasol)){
                                                    posicion[0]=tablero.tableroP[i][j].x;
                                                    posicion[1]=tablero.tableroP[i][j].y;
                                                    arrayPosiciones.add(posicion);
                                                }
                                            }
                                        }

                                        //agregar que si no hay posiciones, se salga de la tienda
                                        int cantPosiciones=0;
                                        boolean flagPosiciones=false;
                                        String pos="";
                                        while (!flagPosiciones){
                                            System.out.println("Ingrese el número correspondiente a la opción de coordenadas para plantar su birasol");
                                            for (int p=0; p<arrayPosiciones.size();p++){
                                                System.out.println("Opción " + p+1 + arrayPosiciones.get(p)[0] + " " + arrayPosiciones.get(p)[1]);
                                                cantPosiciones++;
                                            }
                                            pos = read.nextLine();
                                            try{
                                                int intPos = Integer.parseInt(pos);
                                                flagPosiciones=true;
                                            }
                                            catch (NumberFormatException ex){
                                                System.out.println("Debe ingresar un número de las opciones");
                                            }
                                        }
                                        //plantar en posicion opcion-1
                                        Birasol b = new Birasol(arrayPosiciones.get(Integer.parseInt(pos)-1)[0], arrayPosiciones.get(Integer.parseInt(pos)-1)[1]);
                                        //agregar planta al tablero
                                        //arreglar que no se guardan bien las posiciones
                                        //T.tableroP[i][j]=null;
                                        tablero.plantar(b, juego);
                                        System.out.println("ID de la planta: B");
                                        tablero.mostrarTablero(juego);
                                        break;

                                    case "2":
                                        //buscar donde hay patatapum y darle esas opciones
                                        System.out.println("Eligió gasoseta");
                                        int[] posicionG = new int[2];
                                        ArrayList<int[]> arrayPosicionesG = new ArrayList<int[]>();
                                        for (int i=0; i<5; i++){
                                            for (int j=0; j<10;j++){
                                                if ((tablero.tableroP[i][j]!=null) && (tablero.tableroP[i][j] instanceof Patatapum)){
                                                    posicionG[0]=tablero.tableroP[i][j].x;
                                                    posicionG[1]=tablero.tableroP[i][j].y;
                                                    arrayPosicionesG.add(posicionG);
                                                }
                                            }
                                        }
                                        int cantPosicionesG=0;
                                        boolean flagPosicionesG=false;
                                        String posG="";
                                        while (!flagPosicionesG){
                                            System.out.println("Ingrese el número correspondiente a la opción de coordenadas para plantar su Gasoseta");
                                            for (int p=0; p<arrayPosicionesG.size();p++){
                                                System.out.println("Opción " + p+1 + arrayPosicionesG.get(p)[0] + " " + arrayPosicionesG.get(p)[1]);
                                                cantPosicionesG++;
                                            }
                                            posG = read.nextLine();
                                            try{
                                                int intPos = Integer.parseInt(posG);
                                                flagPosicionesG=true;
                                            }
                                            catch (NumberFormatException ex){
                                                System.out.println("Debe ingresar un número de las opciones");
                                            }
                                        }
                                        //plantar en posicion opcion-1
                                        Gasoseta g = new Gasoseta(arrayPosicionesG.get(Integer.parseInt(posG)-1)[0], arrayPosicionesG.get(Integer.parseInt(posG)-1)[1]);
                                        //agregar planta al tablero
                                        tablero.plantar(g, juego);
                                        System.out.println("ID de la planta: G");
                                        tablero.mostrarTablero(juego);
                                        break;
                                    case "3":
                                        //buscar donde hay Repetidoras y darle solo esas opciones
                                        System.out.println("Eligió Guisantralladora");
                                        int[] posicionT = new int[2];
                                        ArrayList<int[]> arrayPosicionesT = new ArrayList<int[]>();
                                        for (int i=0; i<5; i++){
                                            for (int j=0; j<10;j++){
                                                if ((tablero.tableroP[i][j]!=null) && (tablero.tableroP[i][j] instanceof Guisantralladora)){
                                                    posicionT[0]=tablero.tableroP[i][j].x;
                                                    posicionT[1]=tablero.tableroP[i][j].y;
                                                    arrayPosicionesT.add(posicionT);
                                                }
                                            }
                                        }
                                        int cantPosicionesT=0;
                                        boolean flagPosicionesT=false;
                                        String posT="";
                                        while (!flagPosicionesT){
                                            System.out.println("Ingrese el número correspondiente a la opción de coordenadas para plantar su Guisantralladora");
                                            for (int p=0; p<arrayPosicionesT.size();p++){
                                                System.out.println("Opción " + p+1 + arrayPosicionesT.get(p)[0] + " " + arrayPosicionesT.get(p)[1]);
                                                cantPosicionesT++;
                                            }
                                            posG = read.nextLine();
                                            try{
                                                int intPos = Integer.parseInt(posG);
                                                flagPosicionesT=true;
                                            }
                                            catch (NumberFormatException ex){
                                                System.out.println("Debe ingresar un número de las opciones");
                                            }
                                        }
                                        //plantar en posicion opcion-1
                                        Guisantralladora t = new Guisantralladora(arrayPosicionesT.get(Integer.parseInt(posT)-1)[0], arrayPosicionesT.get(Integer.parseInt(posT)-1)[1]);
                                        //agregar planta al tablero
                                        tablero.plantar(t, juego);
                                        System.out.println("ID de la planta: T");
                                        tablero.mostrarTablero(juego);
                                        break;

                                    default:
                                        System.out.println("No eligió una opción correcta");
                                        break;
                                }
                            }
                            flagPlantas=false;
                            break;
                        case "9":
                            System.out.println("Saliendo del menú de compra");
                            comprarFlag = false;
                            flagPlantas = false;
                        /*default:
                            System.out.println("No eligió una opción correcta");
                            break;*/
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número");
                    }
            comprarFlag = juego.getSoles() >= 25;
            }
        }
    }
}

