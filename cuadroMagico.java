import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class cuadroMagico {
    public static BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));
    public static String entrada;
    public static int[] sumas;
    public static int indice = 0; // Variable para mantener el índice en sumas

    public static boolean validar(int r, int c){
        return r == c;
    }

    public static int[][] llenarDatos(int[][] m) throws IOException {
        System.out.println("Ingrese los elementos del cuadro magico:");
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print("Elemento [" + i + "][" + j + "]: ");
                entrada = bufer.readLine();
                m[i][j] = Integer.parseInt(entrada);
            }
        }
        return m;
    }

    public static boolean comprobarRenglones(int[][] cuadro) {
        int s;
        for (int i = 0; i < cuadro.length; i++) {
            s = sumaRenglon(i, cuadro);
            sumas[indice] = s;
            System.out.println("Suma de fila " + i + ": " + s);
            if (i > 0 && s != sumas[0]) return false; // Si alguna fila no coincide, retorna falso
            indice++;
        }
        return true;
    }

    public static boolean comprobarColumnas(int[][] cuadro) {
        int s;
        for (int j = 0; j < cuadro[0].length; j++) {
            s = 0;
            for (int i = 0; i < cuadro.length; i++) {
                s += cuadro[i][j];
            }
            sumas[indice] = s;
            System.out.println("Suma de columna " + j + ": " + s);

            if (s != sumas[0]) return false; // Si alguna columna no coincide, retorna falso
            indice++;
        }
        return true;
    }

    public static boolean comprobarDiagonales(int[][] cuadro) {
        int s1 = 0, s2 = 0;
        for (int i = 0; i < cuadro.length; i++) {
            s1 += cuadro[i][i];
            s2 += cuadro[i][cuadro.length - i - 1];
        }
        
        sumas[indice] = s1;
        System.out.println("Suma de diagonal principal: " + s1);
        if (s1 != sumas[0]) return false; // Si la primera diagonal no coincide, retorna falso
        indice++;

        sumas[indice] = s2;
        System.out.println("Suma de diagonal secundaria: " + s2);
        return s2 == sumas[0];
    }

    public static boolean esMagico(int[][] cuadro) {
        indice = 0;
        if (comprobarRenglones(cuadro)) {
            if (comprobarColumnas(cuadro)) {
                if (comprobarDiagonales(cuadro)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int sumaRenglon(int r, int[][] m) {
        int suma = 0;
        for (int j = 0; j < m[r].length; j++) {
            suma += m[r][j];
        }
        return suma;
    }

    public static void main(String[] args) throws IOException {
        int renglones, columnas;
        System.out.print("Ingrese el numero de renglones: ");
        entrada = bufer.readLine();
        renglones = Integer.parseInt(entrada);

        System.out.print("Ingrese el numero de columnas: ");
        entrada = bufer.readLine();
        columnas = Integer.parseInt(entrada);

        if (!validar(renglones, columnas)) {
            System.out.println("No cumple con las dimensiones de un cuadro mágico.");
            System.exit(1);
        } else {
            int[][] cuadro = new int[renglones][columnas];
            cuadro = llenarDatos(cuadro);
            sumas = new int[renglones + columnas + 2];

            if (esMagico(cuadro)) {
                System.out.println("El cuadro es mágico.");
            } else {
                System.out.println("El cuadro no es mágico.");
            }
        }
    } 
}
