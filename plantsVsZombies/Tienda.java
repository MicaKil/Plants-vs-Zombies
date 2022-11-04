
package plantsVsZombies;

import java.util.Scanner;

public class Tienda {
    public Tienda() {}
    protected void comprarPlantas(Juego juego, Jardin jardin) {
        boolean comprarFlag = juego.getSoles() >= 25; //si tiene menos de 25 soles no puede comprar
        boolean flagPlantas;
        boolean cada5 = ((juego.getTurno()%5.0) == 0);  //para que aparezca la tienda de dave cada 5 turmos
        Scanner read = new Scanner(System.in);
        int[] posPlanta;
        //bucle de compra
        while (comprarFlag) {
            //en el primer turno solo puede comprar girasoles
            if (juego.getTurno() == 1){
                System.out.println("En el primer turno solo puede comprar girasoles.");
            }
            //agregar opcion para cancelar, volver a elegir coordenada
            if (cada5){
                    System.out.println("*** Está disponible la Tienda de Crazy Dave! ***");
                    System.out.println("Para comprar en la tienda, ingrese el menu de compra.");
                    }
            System.out.println("Tiene: " + juego.getSoles() + " soles disponibles para comprar.");
            System.out.println("Si desea comprar ingrese S o s. Si desea continuar sin comprar apriete cualquier tecla.");
            String compra = read.nextLine();
            if (compra.equals("S") || compra.equals("s")) {
                flagPlantas = true;
            } else {
                comprarFlag = false;
                flagPlantas = false;
            }

            //poner opcion de ver que hace cada una?
            while (flagPlantas) {
                String planta;
                if (juego.getTurno() == 1){
                    planta = "1";
                }
                else{

                    System.out.println("Ingrese el número correspondiente a la planta que desea comprar.");
                    System.out.println("1: Girasol - Costo: 50 soles \n" +
                            "2: Lanzaguisantes - Costo: 100 soles \n" +
                            "3: Repetidora - Costo: 200 soles \n" +
                            "4: Hielaguisantes - Costo: 175 soles \n" +
                            "5: Nuez - Costo: 50 soles \n" +
                            "6: Patatapum - Costo: 25 soles \n" +
                            "7: Petacereza - Costo: 150 soles");
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
                                    if (!(isCasillaVacia(juego.jardin, posPlanta)))
                                        System.out.println("El casillero está ocupado, vuelva a elegir");
                                } while (!(isCasillaVacia(juego.jardin, posPlanta)));

                                Girasol g = new Girasol(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setCantGirasoles(juego.getCantGirasoles() + 1);
                                juego.setSoles(juego.getSoles() - g.getCosto());
                                //agregar planta al tablero
                                jardin.plantar(g);
                                System.out.printf("ID de la planta: %s. \n", g.id);
                                juego.totalPlantas ++;
                                jardin.mostrarJardin(juego);
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
                                    if (!(isCasillaVacia(juego.jardin, posPlanta)))
                                        System.out.println("El casillero está ocupado, vuelva a elegir");
                                } while (!(isCasillaVacia(juego.jardin, posPlanta)));
                               
                                Lanzaguisantes l = new Lanzaguisantes(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setSoles(juego.getSoles() - l.getCosto());
                                //agregar planta al tablero
                                jardin.plantar(l);
                                System.out.printf("ID de la planta: %s. \n", l.id);
                                juego.totalPlantas ++;
                                jardin.mostrarJardin(juego);
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
                                    if (!(isCasillaVacia(juego.jardin, posPlanta)))
                                        System.out.println("El casillero está ocupado, vuelva a elegir");
                                } while (!(isCasillaVacia(juego.jardin, posPlanta)));

                                Planta r = new Lanzaguisantes(posPlanta[0] - 1, posPlanta[1] - 1);
                                r.setCosto(200);
                                r.setDanio(50);
                                r.setId('R');
                                juego.setCantRepetidora(juego.getCantRepetidora() + 1);
                                juego.setSoles(juego.getSoles() - r.getCosto());
                                //agregar planta al tablero
                                jardin.plantar(r);
                                System.out.printf("ID de la planta: %s. \n", r.id);
                                juego.totalPlantas ++;
                                jardin.mostrarJardin(juego);
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
                                    if (!(isCasillaVacia(juego.jardin, posPlanta)))
                                        System.out.println("El casillero está ocupado, vuelva a elegir");
                                } while (!(isCasillaVacia(juego.jardin, posPlanta)));

                                Hielaguisantes h = new Hielaguisantes(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setSoles(juego.getSoles() - h.getCosto());
                                //agregar planta al tablero
                                jardin.plantar(h);
                                System.out.printf("ID de la planta: %s. \n", h.id);
                                juego.totalPlantas ++;
                                jardin.mostrarJardin(juego);
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
                                    if (!(isCasillaVacia(juego.jardin, posPlanta)))
                                        System.out.println("El casillero está ocupado, vuelva a elegir");
                                } while (!(isCasillaVacia(juego.jardin, posPlanta)));

                                Nuez n = new Nuez(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setSoles(juego.getSoles() - n.getCosto());
                                //agregar planta al tablero
                                jardin.plantar(n);
                                System.out.printf("ID de la planta: %s. \n", n.id);
                                juego.totalPlantas ++;
                                jardin.mostrarJardin(juego);
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
                                    if (!(isCasillaVacia(juego.jardin, posPlanta)))
                                        System.out.println("El casillero está ocupado, vuelva a elegir");
                                } while (!(isCasillaVacia(juego.jardin, posPlanta)));

                                Patatapum pt = new Patatapum(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setCantPatatapum(juego.getCantPatatapum() + 1);
                                juego.setSoles(juego.getSoles() - pt.getCosto());
                                //agregar planta al tablero
                                jardin.plantar(pt);
                                System.out.printf("ID de la planta: %s. \n", pt.id);
                                juego.totalPlantas ++;
                                jardin.mostrarJardin(juego);
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
                                    if (!(isCasillaVacia(juego.jardin, posPlanta)))
                                        System.out.println("El casillero está ocupado, vuelva a elegir");
                                } while (!(isCasillaVacia(juego.jardin, posPlanta)));

                                Petacereza p = new Petacereza(posPlanta[0] - 1, posPlanta[1] - 1);
                                juego.setSoles(juego.getSoles() - p.getCosto());
                                //agregar planta al tablero
                                jardin.plantar(p);
                                System.out.printf("ID de la planta: %s. \n", p.id);
                                juego.totalPlantas ++;
                                jardin.mostrarJardin(juego);
                                flagPlantas = false; // termina la compra actual
                            }
                            break;

                        case "8":
                            boolean flagDave=true;
                            while (flagDave){
                                System.out.println("Bienvenido a la tienda de Crazy Dave! ");
                                System.out.println("Tenemos ofertas muy especiales! (OuO)/ "); //(✧∀✧) se muestra en pantalla como ???
                                System.out.println("Ingrese el número correspondiente a la planta que quiere comprar.");
                                System.out.println("1: Birasol - Costo 150 soles + Girasol\n" +
                                        "2: Gasoseta - Costo 150 soles + Patatapum\n" +
                                        "3: Guisantralladora - Costo 250 soles + Repetidora");
                                System.out.println("Si no tenés las plantas base necesarias o ya no querés comprar podes salir con E/e.");
                                String rta= read.nextLine();
                                switch (rta) {
                                    case "E":
                                    case "e": {
                                        System.out.println("Saliendo de la tienda de Crazy Dave.");
                                        flagDave = false;
                                        break;
                                    }
                                    case "1": {
                                        if (juego.getCantGirasoles() == 0) {
                                            System.out.println("Necesitas girasoles para colocar un birasol.");
                                        } else {
                                            //buscar donde hay girasoles y darle solo esas opciones
                                            System.out.println("Eligió Birasol");
                                            //busca donde hay girasoles, pide posicion y verifica que este correcta
                                            int[] pos =posicionesDave('G', juego);
                                            Birasol b = new Birasol(pos[0],pos[1]);
                                            //recibe la planta y juego, vacía el tablero, setea soles, planta, resta girasoles
                                            plantarDave(juego, b);
                                            jardin.mostrarJardin(juego);
                                        }
                                        break;
                                    }
                                    case "2": {
                                        if (juego.getCantPatatapum() == 0) {
                                            System.out.println("Necesita al menos un patatapum para colocar una gasoseta.");
                                        } 
                                        else {
                                            //buscar donde hay girasoles y darle solo esas opciones
                                            System.out.println("Eligió gasoseta");
                                            int[] pos =posicionesDave('P', juego);
                                            Gasoseta g = new Gasoseta(pos[0],pos[1]);
                                            //recibe la planta y juego, vacía el tablero, setea soles, planta, resta patatapum
                                            plantarDave(juego, g);
                                            jardin.mostrarJardin(juego);
                                            }
                                        }
                                        break;
                                    case "3": {
                                        if (juego.getCantRepetidora() == 0) {
                                            System.out.println("Necesitas una repetidora para colocar una guisantralladora.");
                                        }
                                        else {
                                           //buscar donde hay girasoles y darle solo esas opciones
                                            System.out.println("Eligió Guisantralladora");
                                            int[] pos =posicionesDave('R', juego);
                                            Planta g = new Lanzaguisantes(pos[0],pos[1]);
                                            g.setDanio(100);
                                            g.setId('U');
                                            g.setCosto(250);
                                            //recibe la planta y juego, vacía el tablero, setea soles, planta, resta repetetidora
                                            plantarDave(juego, g);
                                            jardin.mostrarJardin(juego);
                                        }
                                        break;
                                    }
                                    default: System.out.println("No eligió una opción correcta.");
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
                if (fila > 0 && fila <= 5) {
                    System.out.printf("Colocará a %s en la fila %d.\n",tipoPlanta, fila);
                    coor[0] = fila;
                    coorFlag = false;
                } else {
                    System.out.println("Fila inválida, ingrese de nuevo.");
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
                if (columna > 0 && columna < 10) {
                    System.out.printf("Colocará a %s en la columna %d.\n",tipoPlanta, columna);
                    coor[1] = columna;
                    coorFlag = false;
                } else {
                    if (columna == 10)
                        System.out.println("La columna 10 está reservada para los zombies.");
                    else
                        System.out.println("Columna inválida, ingrese de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número del 1 al 9.");
            }
        }
        return coor;
    }
    private static int[][] buscarPlantas(char c, Juego juego) {
        int n;
        Jardin jardin = juego.jardin;
        switch (c) {
            case 'G': {
                n = juego.getCantGirasoles();
                break;
            }
            case 'R': {
                n = juego.getCantRepetidora();
                break;}
            case 'P': {
                n = juego.getCantPatatapum();
                break;}
            default: {
                System.out.println("Error.");
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
                if (jardin.jardinP[i][j] != null && jardin.jardinP[i][j].getId() == c) {
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
    private static boolean isCasillaVacia(Jardin t, int[] pos){
        //si los tableros de zombies y plantas están vacíos retorna true
        return t.jardinP[pos[0] - 1][pos[1] - 1] == null && t.jardinZ[pos[0] - 1][pos[1] - 1] == null;
    } 
    
    //imprime las opciones
    private static int[] posicionesDave(char id, Juego juego){
        int[][] pos = buscarPlantas(id, juego);
        int cantPlantas;
        switch (id){
            case 'G': {
                cantPlantas = juego.getCantGirasoles();
                break;
            }
            case 'R': {
                cantPlantas = juego.getCantRepetidora();
                break;}
            case 'P': {
                cantPlantas = juego.getCantPatatapum();
                break;}
            default: {
                System.out.println("Error.");
                return null;
            }
        }
        if (pos != null) {
            System.out.println("Posiciones disponibles: ");
            for (int i = 0; i < cantPlantas; i++) {
                System.out.printf("%d. ", i + 1);
                for (int j = 0; j < 2; j++) {
                    System.out.printf("%d ", pos[i][j] + 1);
                }
                System.out.println();
            }
        } else {
            System.out.println("No hay posiciones disponibles.");
        }

        //retorna la posicion elegida
        return isPosCorrectaDave(cantPlantas, pos);
    }
    //corrobora que se elija bien la posicion y retorna la posicion
    private static int[] isPosCorrectaDave(int cantPlanta, int[][] pos){
        boolean posCorrecta=false;
        Scanner read = new Scanner(System.in);
        int[] p = new int[2];
        String opcion;
        do{
            System.out.println("Elija la posición que desee de las anteriores");
            try {
                opcion = read.nextLine();
                if (Integer.parseInt(opcion) > cantPlanta || Integer.parseInt(opcion) <= 0) {
                    System.out.println("No es una opción correcta"); //estaría bueno que vuelva a imprimir las opciones
                } else {
                    posCorrecta = true;

                    p[0]=pos[Integer.parseInt(opcion)-1][0];
                    p[1]=pos[Integer.parseInt(opcion)-1][1];
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número");
            }
        } while(!posCorrecta); //posicion final elegida
        return p;
    }

    /// no debería estar en el tablero o juego?
    private static void plantarDave(Juego juego, Planta p){
        Jardin t = juego.jardin;
        t.jardinP[p.x][p.y]=null;
        juego.setSoles(juego.getSoles() - p.getCosto());
        t.plantar(p);
        System.out.printf("ID de la planta: %s. \n", p.id);
        switch (p.id){
            case 'B': {
                juego.cantGirasoles--;
                break;
            }
            case 'U': {
                juego.cantRepetidora--;
                break;
            }
            case 'O': {
                juego.cantPatatapum--;
                break;
            }
            default: System.out.println("Error.");
        }
    }
}
                                            