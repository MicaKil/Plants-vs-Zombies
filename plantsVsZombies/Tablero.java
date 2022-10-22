package plantsVsZombies;

public class Tablero {
    // no se si sería privado o protegido
    protected char[][] tablero = new char[5][10];
    public Tablero() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                this.tablero[i][j] = ' ';
            }
        }
    }
    public void mostrarTablero() {
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        System.out.println("   ____________________");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%d | ", i + 1);
            for (int j = 0; j < 10; j++) {
                System.out.print(this.tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
}
