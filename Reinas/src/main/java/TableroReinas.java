import java.util.Random;

public class TableroReinas {
    private int[] posiciones;
    private int n;

    public TableroReinas(int n) {
        this.n = n;
        this.posiciones = new int[n];
    }

    public void resolver() {
        if (resolverReinas(0)) {
            imprimirSolucion();
        } else {
            System.out.println("No hay solución para " + n + " reinas.");
        }
    }

    private boolean resolverReinas(int fila) {
        if (fila == n) {
            return true;
        }

        // columnas aleatorias
        Integer[] columnas = new Integer[n];
        for (int i = 0; i < n; i++) {
            columnas[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int j = random.nextInt(n);
            int temp = columnas[i];
            columnas[i] = columnas[j];
            columnas[j] = temp;
        }

        // en cada partida será aleatorio
        for (int i = 0; i < n; i++) {
            int col = columnas[i];

            if (esSegura(fila, col)) {
                posiciones[fila] = col;

                if (resolverReinas(fila + 1)) {
                    return true;
                }

                posiciones[fila] = -1;
            }
        }
        return false;
    }

    private boolean esSegura(int fila, int col) {
        for (int i = 0; i < fila; i++) {
            if (posiciones[i] == col || Math.abs(posiciones[i] - col) == Math.abs(i - fila)) {
                return false;
            }
        }
        return true;
    }

    private void imprimirSolucion() {
        System.out.println("Solución encontrada:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (posiciones[i] == j) {
                    System.out.print("R ");  // Reina
                } else {
                    System.out.print("▩ ");  // Casilla vacía
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 8;
        TableroReinas tableroReinas = new TableroReinas(n);

        // se repite cada segundo
        while (true) {
            tableroReinas.resolver();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
