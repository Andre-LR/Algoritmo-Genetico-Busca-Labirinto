import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Inicialização do Labirinto
 * 
 * Legendas:
 * 0 = Livre
 * 1 = Parede
 * E = Posição Inicial
 * C = Objetivos a serem buscados
 */
public class Maze {
    public char[][] matrizLabirinto;
    public int linhas;
    public int colunas;
    public int[] posicaoInicial = {0,0};
    public ArrayList<int[]> posicaoObjetivos = new ArrayList<int[]>(); // Lista com coordenada dos objetivos na Matriz

    // Labirinto por leitura do arquivo "labirinto.txt"
    // CORRIGIR MÉTODO
    public Maze(String arquivotxt) throws FileNotFoundException {
        FileReader file = new FileReader(arquivotxt);
        Scanner scanner = new Scanner(file);

        linhas = 10;
        colunas = 10;
        
        matrizLabirinto = new char[linhas-1][colunas-1];
        int i = 0;
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            for (int j = 0; j < linha.length(); j++) {
                matrizLabirinto[i][j] = linha.charAt(j);
                if (linha.charAt(j) == 'I') {
                    posicaoInicial[0] = i;
                    posicaoInicial[1] = j;
                }
                if (linha.charAt(j) == 'C') {
                    int[] posicaoObjetivo = {i, j};
                    posicaoObjetivos.add(posicaoObjetivo);
                }
            }
            i++;
        }

        scanner.close();
    };

    // Labirinto pronto
    public Maze(){
        matrizLabirinto = new char[][]{
            {'E', '1', '1', '1', '1', '1', '1', '1', '0', '1'},
            {'0', '0', '0', '0', 'C', '0', '0', '0', '0', '1'},
            {'1', '1', '1', '0', '1', '1', '1', '1', '1', '0'},
            {'0', '0', '1', '0', '1', '0', '0', '0', '0', '0'},
            {'C', '0', '1', '0', '1', '1', '0', '1', '1', '1'},
            {'0', '0', '0', '0', '1', 'C', '0', '0', '0', '0'},
            {'0', '1', '1', '1', '1', '0', '0', '1', '0', 'C'},
            {'0', '1', '0', '0', '0', '0', '0', '0', '1', '1'},
            {'0', '1', '1', '1', '1', '1', '1', '0', '0', '1'},
            {'C', '0', '0', '0', '0', '0', '0', '0', '0', '1'}
        };

        // Busca posição objetivos e posição inicial
        for (int i = 0; i < matrizLabirinto.length; i++) {
            for (int j = 0; j < matrizLabirinto[i].length; j++) {
                if (matrizLabirinto[i][j] == 'E') {
                    posicaoInicial[0] = i;
                    posicaoInicial[1] = j;
                }
                if (matrizLabirinto[i][j] == 'C') {
                    int[] posicaoObjetivo = {i, j};
                    posicaoObjetivos.add(posicaoObjetivo);
                }
            }
        }
    }
    
    public void printPosicaoObjetivos() {
        System.out.println("\nPosicao dos objetivos:");
        String txt = "Coordenadas: [";
        for (int i = 0; i < posicaoObjetivos.size(); i++) {
            txt += "(" + posicaoObjetivos.get(i)[0] + ", " + posicaoObjetivos.get(i)[1] + "), ";
        }
        txt += "]";

        System.out.println(txt);
    }
    
    public void printLabirinto(){
        for (int i = 0; i < matrizLabirinto.length; i++) {
            for (int j = 0; j < matrizLabirinto[0].length; j++) {
                System.out.print(matrizLabirinto[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char[][] getMaze(){
        return matrizLabirinto;
    }


}
