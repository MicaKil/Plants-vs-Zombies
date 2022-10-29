
package plantsVsZombies;

import java.util.Scanner;

public class Tienda {
    public Tienda() {}
    public void comprarPlantas(PlantsVsZombies juego, Tablero tablero) {
        //Modelado del juego con el usuario participando
        boolean comprarFlag = juego.getSoles() >= 25; //si tiene menos de 25 soles no puede comprar
        boolean flagPlantas;
        boolean cada5 = true; //((((juego.getCantMovimientos())/2.0)+1.0)%5.0==0.0);  //para que aparezca la tienda de dave cada 5 turmos
        Scanner read = new Scanner(System.in);
        int[] posPlanta;
        //bucle de compra
        while (comprarFlag) {
            //en el primer turno solo puede comprar girasoles
            if (juego.getCantMovimientos()==0){
                System.out.println("En el primer turno solo puede comprar girasoles.");
            }
            //agregar opcion para cancelar, volver a elegir coordenada, salir del menu de compra
            if (cada5){
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
                if (juego.getCantMovimientos() == 0){
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
                    if (cada5){
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

                                do { //comprueba que no haya zombies ni plantas en el casillero
                                    posPlanta = pedirPos("el girasol");
                                    if (!(isCasillaVacia(juego.tablero, posPlanta)))
                                        System.out.println("El casillero está ocupado, vuelva a elegir");
                                } while (!(isCasillaVacia(juego.tablero, posPlanta)));

                                posPlanta = pedirPos("el girasol");
                                //comprueba que no haya zombies ni plantas en el casillero
                                boolean tableroVacio = comprobarTableroVacío(juego.tablero, posPlanta);
                                while (tableroVacio){
                                    System.out.println("El casillero está ocupado, vuelva a elegir");
                                    posPlanta = pedirPos("el girasol");
                                    tableroVacio = comprobarTableroVacío(juego.tablero, posPlanta);
                                }

                                Girasol g = new Girasol(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setCantGirasoles(juego.getCantGirasoles() + 1);
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
                                do { //comprueba que no haya zombies ni plantas en el casillero
                                    posPlanta = pedirPos("el lanzaguisantes");
                                    if (!(isCasillaVacia(juego.tablero, posPlanta)))
                                        System.out.println("El casillero está ocupado, vuelva a elegir");
                                } while (!(isCasillaVacia(juego.tablero, posPlanta)));
                               
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
                                //obtener coordenadas de la nueva planta
                                do { //comprueba que no haya zombies ni plantas en el casillero
                                    posPlanta = pedirPos("la repetidora");
                                    if (!(isCasillaVacia(juego.tablero, posPlanta)))
                                        System.out.println("El casillero está ocupado, vuelva a elegir");
                                } while (!(isCasillaVacia(juego.tablero, posPlanta)));

                                Repetidora r = new Repetidora(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setCantRepetidora(juego.getCantRepetidora() + 1);
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
                                //obtener coordenadas de la nueva planta
                                do { //comprueba que no haya zombies ni plantas en el casillero
                                    posPlanta = pedirPos("el hielaguisantes");
                                    if (!(isCasillaVacia(juego.tablero, posPlanta)))
                                        System.out.println("El casillero está ocupado, vuelva a elegir");
                                } while (!(isCasillaVacia(juego.tablero, posPlanta)));

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
                            if (juego.getSoles() < 50) {
                                System.out.println("No le alcanza para comprar la Nuez.");
                            } else {
                                System.out.println("Eligió la Nuez.");
                                //obtener coordenadas de la nueva planta
                                do { //comprueba que no haya zombies ni plantas en el casillero
                                    posPlanta = pedirPos("la nuez");
                                    if (!(isCasillaVacia(juego.tablero, posPlanta)))
                                        System.out.println("El casillero está ocupado, vuelva a elegir");
                                } while (!(isCasillaVacia(juego.tablero, posPlanta)));

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
                            if (juego.getSoles() < 25) {
                                System.out.println("No le alcanza para comprar el Patatapum");
                            } else {
                                System.out.println("Eligió el Patatapum");
                                //obtener coordenadas de la nueva planta
                                do { //comprueba que no haya zombies ni plantas en el casillero
                                    posPlanta = pedirPos("el patatapum");
                                    if (!(isCasillaVacia(juego.tablero, posPlanta)))
                                        System.out.println("El casillero está ocupado, vuelva a elegir");
                                } while (!(isCasillaVacia(juego.tablero, posPlanta)));

                                Patatapum pt = new Patatapum(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setCantPatatapum(juego.getCantPatatapum() + 1);
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
                            if (juego.getSoles() < 150) {
                                System.out.println("No le alcanza para comprar la Petacereza.");
                            } else {
                                System.out.println("Eligió la Petacereza.");
                                //obtener coordenadas de la nueva planta
                                do { //comprueba que no haya zombies ni plantas en el casillero
                                    posPlanta = pedirPos("la petacereza");
                                    if (!(isCasillaVacia(juego.tablero, posPlanta)))
                                        System.out.println("El casillero está ocupado, vuelva a elegir");
                                } while (!(isCasillaVacia(juego.tablero, posPlanta)));

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
                                        if (juego.getCantGirasoles() == 0) {
                                            System.out.println("Necesitas girasoles para colocar un birasol.");
                                            flagDave = false;
                                        } else {
                                            //buscar donde hay girasoles y darle solo esas opciones
                                            System.out.println("Eligió Birasol");
                                            int[][] posGirasoles = buscarPlantas('G', juego);
                                            System.out.println("Posiciones disponibles: ");
                                            for (int i = 0; i < juego.getCantGirasoles(); i++) {
                                                System.out.printf("%d. ", i + 1);
                                                for (int j = 0; j < 2; j++) {
                                                    System.out.printf("%d ", posGirasoles[i][j] + 1);
                                                }
                                                System.out.println();
                                            }
                                            
                                            boolean posicionCorrecta=false;
                                            while (!posicionCorrecta){
                                                System.out.println("Elija la posición que desee de las anteriores");
                                                String pos = read.nextLine();
                                                if (Integer.parseInt(pos)>juego.getCantGirasoles()){
                                                    System.out.println("No es una opción correcta");
                                                }
                                                else{
                                                    int i,j;
                                                    i=posGirasoles[Integer.parseInt(pos)-1][0];
                                                    j=posGirasoles[Integer.parseInt(pos)-1][1];
                                                    tablero.tableroP[i][j]=null;
                                                    Birasol b = new Birasol(i,j);
                                                    juego.setSoles(juego.getSoles() - b.getCosto());
                                                    //agregar planta al tablero
                                                    tablero.plantar(b, juego);
                                                    System.out.printf("ID de la planta: %s. \n", b.id);
                                                    juego.totalPlantas ++;
                                                    tablero.mostrarTablero(juego);
                                                    posicionCorrecta = true; // termina la compra actual
                                                }
                                            }
                                        }
                                        break;
                                    }
                                    case "2" -> {
                                        if (juego.getCantPatatapum() == 0) {
                                            System.out.println("Necesita al menos un patatapum para colocar una gasoseta.");
                                            flagDave = false;
                                        } else {
                                            //buscar donde hay girasoles y darle solo esas opciones
                                            System.out.println("Eligió gasoseta");
                                            int[][] posPatatapum = buscarPlantas('P', juego);
                                            System.out.println("Posiciones disponibles: ");
                                            for (int i = 0; i < juego.getCantGirasoles(); i++) {
                                                System.out.printf("%d. ", i + 1);
                                                for (int j = 0; j < 2; j++) {
                                                    System.out.printf("%d ", posPatatapum[i][j] + 1);
                                                }
                                                System.out.println();
                                            }
                                            boolean posicionCorrecta=false; 
                                            while (!posicionCorrecta){
                                                System.out.println("Elija la posición que desee de las anteriores");
                                                String pos = read.nextLine();
                                                if (Integer.parseInt(pos)>juego.getCantPatatapum()){
                                                    System.out.println("No es una opción correcta");
                                                }
                                                else{
                                                    int i,j;
                                                    i=posPatatapum[Integer.parseInt(pos)-1][0];
                                                    j=posPatatapum[Integer.parseInt(pos)-1][1];
                                                    tablero.tableroP[i][j]=null;
                                                    Patatapum p = new Patatapum(i,j);
                                                    juego.setSoles(juego.getSoles() - p.getCosto());
                                                    //agregar planta al tablero
                                                    tablero.plantar(p, juego);
                                                    System.out.printf("ID de la planta: %s. \n", p.id);
                                                    juego.totalPlantas ++;
                                                    tablero.mostrarTablero(juego);
                                                    posicionCorrecta = true; // termina la compra actual
                                                }
                                            }
                                        }
                                    }
                                    case "3" -> {
                                        if (juego.getCantRepetidora() == 0) {
                                            System.out.println("Necesitas una repetidora para colocar una guisantralladora.");
                                            flagDave = false;
                                        } else {
                                            //buscar donde hay girasoles y darle solo esas opciones
                                            System.out.println("Eligió guisantralladora.");
                                            int[][] posRepetidora = buscarPlantas('R', juego);
                                            System.out.println("Posiciones disponibles: ");
                                            for (int i = 0; i < juego.getCantRepetidora(); i++) {
                                                System.out.printf("%d. ", i + 1);
                                                for (int j = 0; j < 2; j++) {
                                                    System.out.printf("%d ", posRepetidora[i][j] + 1);
                                                }
                                                System.out.println();
                                            }
                                            boolean posicionCorrecta=false; 
                                            while (!posicionCorrecta){
                                                System.out.println("Elija la posición que desee de las anteriores");
                                                String pos = read.nextLine();
                                                if (Integer.parseInt(pos)>juego.getCantRepetidora()){
                                                    System.out.println("No es una opción correcta");
                                                }
                                                else{
                                                    int i,j;
                                                    i=posRepetidora[Integer.parseInt(pos)-1][0];
                                                    j=posRepetidora[Integer.parseInt(pos)-1][1];
                                                    tablero.tableroP[i][j]=null; 
                                                    Guisantralladora g = new Guisantralladora (i,j);
                                                    juego.setSoles(juego.getSoles() - g.getCosto());
                                                    //agregar planta al tablero
                                                    tablero.plantar(g, juego);
                                                    System.out.printf("ID de la planta: %s. \n", g.id);
                                                    juego.totalPlantas ++;
                                                    tablero.mostrarTablero(juego);
                                                    posicionCorrecta = true; // termina la compra actual
                                                }
                                            }
                                        }
                                    }
                                    default -> System.out.println("No eligió una opción correcta.");
                                }
                            }
                            flagPlantas=false;
                            break;
                        case "9":
                            System.out.println("Saliendo del menú de compra.");
                            flagPlantas = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número");
                    }
                comprarFlag = juego.getSoles() >= 25;
                if (!comprarFlag) {
                    System.out.println("No hay soles suficientes para realizar una compra.");
                }
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
    private static int[][] buscarPlantas(char c, PlantsVsZombies juego) {
        int n;
        Tablero tablero = juego.tablero;
        switch (c) {
            case 'G' -> n = juego.getCantGirasoles();
            case 'R' -> n = juego.getCantRepetidora();
            case 'P' -> n = juego.getCantPatatapum();
            default -> {
                System.out.println("Error."); //es para mi
                return null;
            }
        }
        int[][] posiciones = new int[n][2];
        int fila = 0;
        int i = 0;
        int j;
        while (i < 5 && fila <= n) {
            j = 0;
            while (j < 9 && fila <= n){
                if (tablero.tableroP[i][j] != null && tablero.tableroP[i][j].getId() == c) {
                    posiciones[fila][0] = i;
                    posiciones[fila][1] = j;
                    fila ++;
                }
                j++;
            }
            i++;
        }
        return posiciones;
    }
    //comprueba que no haya zombies ni plantas en el casillero
    //recibe el tablero y la posición de la nueva planta
    private boolean isCasillaVacia(Tablero t, int[] pos){
        //si los tableros de zombies y plantas están vacíos retorna true
        return t.tableroP[pos[0] - 1][pos[1] - 1] == null && t.tableroZ[pos[0] - 1][pos[1] - 1] == null;
    } 
}
