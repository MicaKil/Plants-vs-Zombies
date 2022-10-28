
package plantsVsZombies;

import java.util.ArrayList;
import java.util.Scanner;

public class Tienda {
    public Tienda() {}
    public void comprarPlantas(PlantsVsZombies juego, Tablero tablero) {
        //Modelado del juego con el usuario participando
        boolean comprarFlag = juego.getSoles() >= 25; //si tiene menos de 25 soles no puede comprar
        boolean flagPlantas;
        boolean cadaCinco = ((((juego.cantMovimientos)/2.0)+1.0)%5.0==0.0);  //para que aparezca la tienda de dave cada 5 turmos
        Scanner read = new Scanner(System.in);
        int[] posPlanta;
        //bucle de compra
        while (comprarFlag) {
            //en el primer turno solo puede comprar girasoles
            if (juego.cantMovimientos==0){
                System.out.println("En el primer turno solo puede comprar girasoles.");
            }
            //agregar opcion para cancelar, volver a elegir coordenada, salir del menu de compra
            if (cadaCinco){
                        System.out.println("*** Está disponible la Tienda de Crazy Dave! ***"); // ヽ(・∀・)ﾉ  se ve como ???
                        System.out.println("Para comprar en la tienda, ingrese el menu de compra.");
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
                if (juego.cantMovimientos == 0){
                    planta = "1";
                }
                else{

                    System.out.println("Ingrese el número correspondiente a la planta que desea comprar.");
                    System.out.println("""
                            1: Girasol - Costo: 50 soles
                            2: Lanzaguisantes - Costo: 100 soles
                            3: Repetidora - Costo: 200 soles
                            4: Hielaguisantes - Costo: 175 soles
                            5: Nuez - Costo: 50 soles
                            6: Patatapum - Costo: 25 soles
                            7: Petacereza - Costo: 150 soles""");
                    if (cadaCinco){
                        System.out.println("8: Tienda de Crazy Dave! O.O");
                    }
                    System.out.println("9: Salir del menú de compra.");
                planta = read.nextLine();
                }
                try {
                    switch (planta) {
                        case "1":
                            if (juego.getSoles() < 50) {
                                System.out.println("No le alcanza para comprar un Girasol.");
                            } else {
                                System.out.println("Eligió el Girasol.");
                                //obtener coordenadas de la nueva planta
                                posPlanta = pedirPos("el girasol");
                                Girasol g = new Girasol(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setSoles(juego.getSoles() - g.getCosto());
                                //agregar planta al tablero
                                tablero.plantar(g, juego);
                                System.out.printf("ID de la planta: %s. \n", g.id);
                                juego.totalPlantas ++;
                                tablero.mostrarTablero(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "2":
                            if (juego.getSoles() < 100) {
                                System.out.println("No le alcanza para comprar el Lanzaguisantes.");
                            } else {
                                System.out.println("Eligió el Lanzaguisantes.");
                                //obtener coordenadas de la nueva planta
                                posPlanta = pedirPos("el lanzaguisantes");
                                Lanzaguisantes l = new Lanzaguisantes(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setSoles(juego.getSoles() - l.getCosto());
                                //agregar planta al tablero
                                tablero.plantar(l, juego);
                                System.out.printf("ID de la planta: %s. \n", l.id);
                                juego.totalPlantas ++;
                                tablero.mostrarTablero(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "3":
                            if (juego.getSoles() < 200) {
                                System.out.println("No le alcanza para comprar la Repetidora.");
                            } else {
                                System.out.println("Eligió la Repetidora.");
                                posPlanta = pedirPos("la repetidora");
                                Repetidora r = new Repetidora(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setSoles(juego.getSoles() - r.getCosto());
                                //agregar planta al tablero
                                tablero.plantar(r, juego);
                                System.out.printf("ID de la planta: %s. \n", r.id);
                                juego.totalPlantas ++;
                                tablero.mostrarTablero(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "4":
                            if (juego.getSoles() < 175) {
                                System.out.println("No le alcanza para comprar el Hielaguisantes.");
                            } else {
                                System.out.println("Eligió el Hielaguisantes");
                                posPlanta = pedirPos("el hielaguisantes");
                                Hielaguisantes h = new Hielaguisantes(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setSoles(juego.getSoles() - h.getCosto());
                                //agregar planta al tablero
                                tablero.plantar(h, juego);
                                System.out.printf("ID de la planta: %s. \n", h.id);
                                juego.totalPlantas ++;
                                tablero.mostrarTablero(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "5":
                            if (juego.getSoles() < 100) {
                                System.out.println("No le alcanza para comprar la Nuez.");
                            } else {
                                System.out.println("Eligió la Nuez.");
                                posPlanta = pedirPos("la nuez");
                                Nuez n = new Nuez(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setSoles(juego.getSoles() - n.getCosto());
                                //agregar planta al tablero
                                tablero.plantar(n, juego);
                                System.out.printf("ID de la planta: %s. \n", n.id);
                                juego.totalPlantas ++;
                                tablero.mostrarTablero(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "6":
                            if (juego.getSoles() < 100) {
                                System.out.println("No le alcanza para comprar el Patatapum");
                            } else {
                                System.out.println("Eligió el Patatapum");
                                posPlanta = pedirPos("el patatapum");
                                Patatapum pt = new Patatapum(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setSoles(juego.getSoles() - pt.getCosto());
                                //agregar planta al tablero
                                tablero.plantar(pt, juego);
                                System.out.printf("ID de la planta: %s. \n", pt.id);
                                juego.totalPlantas ++;
                                tablero.mostrarTablero(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "7":
                            if (juego.getSoles() < 100) {
                                System.out.println("No le alcanza para comprar la Petacereza.");
                            } else {
                                System.out.println("Eligió la Petacereza.");
                                posPlanta = pedirPos("la petacereza");
                                Petacereza p = new Petacereza(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setSoles(juego.getSoles() - p.getCosto());
                                //agregar planta al tablero
                                tablero.plantar(p, juego);
                                System.out.printf("ID de la planta: %s. \n", p.id);
                                juego.totalPlantas ++;
                                tablero.mostrarTablero(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "8":
                            boolean flagDave=true;
                            while (flagDave){
                                System.out.println("Bienvenido a la tienda de Crazy Dave! ");
                                System.out.println("Tenemos ofertas muy especiales! (OuO)/ "); //(✧∀✧) se muestra en pantalla como ???
                                System.out.println("Ingrese el número correspondiente a la planta que quiere comprar.");
                                System.out.println("""
                                        1: Birasol - Costo 150 soles + Girasol
                                        2: Gasoseta - Costo 150 soles + Patatapum
                                        3: Guisantralladora - Costo 250 soles + Repetidora""");
                                System.out.println("Si no tenés las plantas base necesarias o ya no querés comprar podes salir con E/e y volver a entrar después de comprar.");
                                String rta= read.nextLine();
                                switch (rta) {
                                    case "E", "e" -> {
                                        System.out.println("Saliendo de la tienda de Crazy Dave.");
                                        flagDave = false;
                                    }
                                    case "1" -> {
                                        //buscar donde hay girasoles y darle solo esas opciones
                                        System.out.println("Eligió Birasol");
                                        int[] posicion = new int[2];
                                        ArrayList<int[]> arrayPosiciones = new ArrayList<>();
                                        for (int i = 0; i < 5; i++) {
                                            for (int j = 0; j < 10; j++) {
                                                if ((tablero.tableroP[i][j] != null) && (tablero.tableroP[i][j] instanceof Girasol)) {
                                                    posicion[0] = tablero.tableroP[i][j].x;
                                                    posicion[1] = tablero.tableroP[i][j].y;
                                                    arrayPosiciones.add(posicion);
                                                }
                                            }
                                        }

                                        //agregar que si no hay posiciones, se salga de la tienda
                                        int cantPosiciones = 0;
                                        boolean flagPosiciones = false;
                                        String pos = "";
                                        while (!flagPosiciones) {
                                            System.out.println("Ingrese el número correspondiente a la opción de coordenadas para plantar su birasol.");
                                            for (int p = 0; p < arrayPosiciones.size(); p++) {
                                                System.out.println("Opción " + p + 1 + arrayPosiciones.get(p)[0] + " " + arrayPosiciones.get(p)[1]);
                                                cantPosiciones++;
                                            }
                                            pos = read.nextLine();
                                            try {
                                                int intPos = Integer.parseInt(pos);
                                                flagPosiciones = true;
                                            } catch (NumberFormatException ex) {
                                                System.out.println("Debe ingresar un número de las opciones.");
                                            }
                                        }
                                        //plantar en posicion opcion-1
                                        Birasol b = new Birasol(arrayPosiciones.get(Integer.parseInt(pos) - 1)[0], arrayPosiciones.get(Integer.parseInt(pos) - 1)[1]);
                                        //agregar planta al tablero
                                        //arreglar que no se guardan bien las posiciones
                                        //T.tableroP[i][j]=null;
                                        tablero.plantar(b, juego);
                                        System.out.printf("ID de la planta: %s. \n", b.id);
                                        juego.totalPlantas ++;
                                        tablero.mostrarTablero(juego);
                                    }
                                    case "2" -> {
                                        //buscar donde hay patatapum y darle esas opciones
                                        System.out.println("Eligió gasoseta.");
                                        int[] posicionG = new int[2];
                                        ArrayList<int[]> arrayPosicionesG = new ArrayList<>();
                                        for (int i = 0; i < 5; i++) {
                                            for (int j = 0; j < 10; j++) {
                                                if ((tablero.tableroP[i][j] != null) && (tablero.tableroP[i][j] instanceof Patatapum)) {
                                                    posicionG[0] = tablero.tableroP[i][j].x;
                                                    posicionG[1] = tablero.tableroP[i][j].y;
                                                    arrayPosicionesG.add(posicionG);
                                                }
                                            }
                                        }
                                        int cantPosicionesG = 0;
                                        boolean flagPosicionesG = false;
                                        String posG = "";
                                        while (!flagPosicionesG) {
                                            System.out.println("Ingrese el número correspondiente a la opción de coordenadas para plantar su Gasoseta.");
                                            for (int p = 0; p < arrayPosicionesG.size(); p++) {
                                                System.out.println("Opción " + p + 1 + arrayPosicionesG.get(p)[0] + " " + arrayPosicionesG.get(p)[1]);
                                                cantPosicionesG++;
                                            }
                                            posG = read.nextLine();
                                            try {
                                                int intPos = Integer.parseInt(posG);
                                                flagPosicionesG = true;
                                            } catch (NumberFormatException ex) {
                                                System.out.println("Debe ingresar un número de las opciones.");
                                            }
                                        }
                                        //plantar en posicion opcion-1
                                        Gasoseta g = new Gasoseta(arrayPosicionesG.get(Integer.parseInt(posG) - 1)[0], arrayPosicionesG.get(Integer.parseInt(posG) - 1)[1]);
                                        //agregar planta al tablero
                                        tablero.plantar(g, juego);
                                        System.out.printf("ID de la planta: %s. \n", g.id);
                                        juego.totalPlantas ++;
                                        tablero.mostrarTablero(juego);
                                    }
                                    case "3" -> {
                                        //buscar donde hay Repetidoras y darle solo esas opciones
                                        System.out.println("Eligió Guisantralladora.");
                                        int[] posicionT = new int[2];
                                        ArrayList<int[]> arrayPosicionesT = new ArrayList<>();
                                        for (int i = 0; i < 5; i++) {
                                            for (int j = 0; j < 10; j++) {
                                                if ((tablero.tableroP[i][j] != null) && (tablero.tableroP[i][j] instanceof Guisantralladora)) {
                                                    posicionT[0] = tablero.tableroP[i][j].x;
                                                    posicionT[1] = tablero.tableroP[i][j].y;
                                                    arrayPosicionesT.add(posicionT);
                                                }
                                            }
                                        }
                                        int cantPosicionesT = 0;
                                        boolean flagPosicionesT = false;
                                        String posT = "";
                                        while (!flagPosicionesT) {
                                            System.out.println("Ingrese el número correspondiente a la opción de coordenadas para plantar su Guisantralladora.");
                                            for (int p = 0; p < arrayPosicionesT.size(); p++) {
                                                // te esta concatenando los números en vez de sumarlos proba con printf o print
                                                System.out.println("Opción " + p + 1 + arrayPosicionesT.get(p)[0] + " " + arrayPosicionesT.get(p)[1]);
                                                cantPosicionesT++;
                                            }
                                            posT = read.nextLine();
                                            try {
                                                int intPos = Integer.parseInt(posT);
                                                flagPosicionesT = true;
                                            } catch (NumberFormatException ex) {
                                                System.out.println("Debe ingresar un número de las opciones.");
                                            }
                                        }
                                        //plantar en posicion opcion-1
                                        Guisantralladora t = new Guisantralladora(arrayPosicionesT.get(Integer.parseInt(posT) - 1)[0], arrayPosicionesT.get(Integer.parseInt(posT) - 1)[1]);
                                        //agregar planta al tablero
                                        tablero.plantar(t, juego);
                                        System.out.printf("ID de la planta: %s. \n", t.id);
                                        juego.totalPlantas ++;
                                        tablero.mostrarTablero(juego);
                                    }
                                    default -> System.out.println("No eligió una opción correcta.");
                                }
                            }
                            flagPlantas=false;
                            break;
                        case "9":
                            System.out.println("Saliendo del menú de compra.");
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

    private static int[] pedirPos(String tipoPlanta) {
        int fila, columna;
        int[] coor = new int[2];
        boolean coorFlag = true;
        Scanner read = new Scanner(System.in);

        // pedir fila
        while (coorFlag) {
            System.out.printf("Ingrese la fila de %s (fila = de 1 a 5)\n", tipoPlanta);
            try {
                fila = Integer.parseInt(read.nextLine());
                switch (fila) {
                    case 1, 2, 3, 4, 5 -> {
                        System.out.printf("Colocará a %s en la fila %d.\n",tipoPlanta, fila);
                        coor[0] = fila;
                        coorFlag = false;
                    }
                    default -> System.out.println("Fila inválida, ingrese de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número del 1 al 5.");
            }
        }

        // pedir columna

        coorFlag = true; // se vuelve a colocar en true
        while (coorFlag) {
            System.out.printf("Ingrese la columna de %s (columna = de 1 a 9)\n", tipoPlanta);
            try {
                columna = Integer.parseInt(read.nextLine());
                switch (columna) {
                    case 1, 2, 3, 4, 5, 6, 7, 8, 9 -> {
                        System.out.printf("Colocará a %s en la columna %d.\n",tipoPlanta, columna);
                        coor[1] = columna;
                        coorFlag = false;
                    }
                    default -> {
                        if (columna == 10)
                            System.out.println("La columna 10 está reservada para los zombies.");
                        else
                            System.out.println("Columna inválida, ingrese de nuevo.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número del 1 al 9.");
            }
        }
        return coor;
    }
}

