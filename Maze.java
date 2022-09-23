import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Maze {
    public int[] entrada = {0,0};
    //Array com as coordenadas dos objetivos
    public int[][] objetivos = new int[2][2];

    int rows = 0;
    int columns = 1;
    char[][] matrizLabirinto = new char[rows][columns];

    public Maze() throws FileNotFoundException {
        FileReader file = new FileReader("labirinto.txt");
        Scanner scanner = new Scanner(file);
                        
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            columns = linha.length();
            rows++;
        }
        file = new FileReader("labirinto.txt");
        scanner = new Scanner(file);
        matrizLabirinto = new char[rows][columns];
        int i = 0;
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            for (int j = 0; j < columns; j++) {
                matrizLabirinto[i][j] = linha.charAt(j);
                if (linha.charAt(j) == 'I') {
                    entrada[0] = i;
                    entrada[1] = j;
                }
            }
            i++;
        }
    }
    
    //imprime posicao dos objetivos
    public void printObjetivos() {
        System.out.println("Objetivos:");
        for (int i = 0; i < objetivos.length; i++) {
            System.out.println("Objetivo " + (i+1) + ": " + objetivos[i][0] + ", " + objetivos[i][1]);
        }
    }
    
    public void printLabirinto(){
        for (int i = 0; i < matrizLabirinto.length; i++) {
            for (int j = 0; j < matrizLabirinto[0].length; j++) {
                System.out.print(matrizLabirinto[i][j] + " ");
            }
            System.out.println();
        }
    }


}
