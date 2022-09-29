package UTILS;

import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

import static java.lang.System.exit;

public class Labirinto extends JPanel{
    public static char[][] labirinto;
    public static int tamLabirinto;
    public static int linhas;
    public static int colunas;
    public static int[] posicaoInicial = {0,0};
    public static int qtdObjetivos;
    public ArrayList<int[]> posicaoObjetivos = new ArrayList<int[]>(); // Lista com coordenada dos objetivos na Matriz

    // Construtor Labirinto
    public Labirinto(String title) throws IOException {
        /**
            * Inicialização do Labirinto
            * 
            * Legendas:
            * 0 = Livre         * E = Posição Inicial
            * 1 = Parede        * C = Objetivos a serem buscados            
        */

//        labirinto = new char[][]{
//            {'E', '1', '1', '1', '1', '1', '1', '1', '0', '1'},
//            {'0', '0', '0', '0', 'C', '0', '0', '0', '0', '1'},
//            {'1', '1', '1', '0', '1', '1', '1', '1', '1', '0'},
//            {'0', '0', '1', '0', '1', '0', '0', '0', '0', '0'},
//            {'C', '0', '1', '0', '1', '1', '0', '1', '1', '1'},
//            {'0', '0', '0', '0', '1', 'C', '0', '0', '0', '0'},
//            {'0', '1', '1', '1', '1', '0', '0', '1', '0', 'C'},
//            {'0', '1', '0', '0', '0', '0', '0', '0', '1', '1'},
//            {'0', '1', '1', '1', '1', '1', '1', '0', '0', '1'},
//            {'C', '0', '0', '0', '0', '0', '0', '0', '0', '1'}
//        };

        // função para ter arquivo txt com labirinto

        labirinto = this.geraLabirinto();
        
        // Busca posição objetivos e posição inicial
        for (int i = 0; i < labirinto.length; i++) {
            for (int j = 0; j < labirinto[i].length; j++) {
                if (labirinto[i][j] == 'E') {
                    posicaoInicial[0] = i;
                    posicaoInicial[1] = j;
                }
                if (labirinto[i][j] == 'C') {
                    int[] posicaoObjetivo = {i, j};
                    posicaoObjetivos.add(posicaoObjetivo);
                    qtdObjetivos++;
                }
            }
        }  
        
        // Tamanho do Labirinto
        linhas = labirinto.length;
        colunas = labirinto[0].length;
        tamLabirinto = linhas;

        JFrame f = new JFrame();
        f.getContentPane().add(this);
        setBackground(Color.WHITE);
        f.setTitle(title);
        f.setSize(400,400); // Tamanho janela display
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // le arquivo e gera labirinto
    public char [][] geraLabirinto () {
        char[][] maze = new char[0][];
        try {
            BufferedReader infile = new BufferedReader(new FileReader("Algoritmo-Genetico-Busca-Labirinto/UTILS/labirinto.txt"));
            int rows = 10;
            int cols = 10;
            maze = new char[rows][cols];
            infile.readLine();
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    // remove blank spaces
                    char ch = (char) infile.read();
                    while (ch == ' ') {
                        ch = (char) infile.read();
                    } // end while
                    maze[r][c] = ch;
                }
                infile.readLine();
            }
            infile.close();

        }

        catch (FileNotFoundException e) {

            System.out.println("Input file not found.");

        } catch (IOException e) {

            System.out.println("Error reading from file.");

        }

        return maze;
    }

    // Paleta de Cores
    public void paintComponent(Graphics g){ 
        int frame_largura = 600;
        int frame_altura = 500;
        g.setColor(Color.white);
        g.fillRect(0, 0, frame_largura, frame_altura);
        g.setColor(Color.red);
        for (int linha = 0; linha < labirinto.length; linha++) {
            for (int coluna = 0; coluna < labirinto[0].length; coluna++) {
                Color color;
                switch (labirinto[linha][coluna]) {
                    case '1' : color = Color.BLACK; break;  // PRETO    = 1 = Parede
                    case 'E' : color = Color.RED; break;    // VERMELHO = E = Posição Inicial
                    case 'C' : color = Color.BLUE; break;   // AZUL     = C = Objetivos a serem buscados
                    case '5' : color = Color.GREEN; break;  // VERDE    = Caminho percorrido
                    default : color = Color.WHITE;          // BRANCO   = 0 = Livre
                }
                g.setColor(color);
                g.fillRect(30 * coluna, 30 * linha, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * coluna, 30 * linha, 30, 30);
            }
        }

        // Busca posição objetivos e posição inicial
        for (int i = 0; i < labirinto.length; i++) {
            for (int j = 0; j < labirinto[i].length; j++) {
                if (labirinto[i][j] == 'E') {
                    posicaoInicial[0] = i;
                    posicaoInicial[1] = j;
                }
                if (labirinto[i][j] == 'C') {
                    int[] posicaoObjetivo = {i, j};
                    posicaoObjetivos.add(posicaoObjetivo);
                }
            }
        }
    }
    
    public void imprimePosicaoObjetivos() {
        System.out.println("\nPosicao dos objetivos:");
        String txt = "Coordenadas: [";
        for (int i = 0; i < posicaoObjetivos.size(); i++) {
            txt += "(" + posicaoObjetivos.get(i)[0] + ", " + posicaoObjetivos.get(i)[1] + "), ";
        }
        txt += "]";

        System.out.println(txt);
    }
    
    public void imprimeLabirinto(){
        for (int i = 0; i < labirinto.length; i++) {
            for (int j = 0; j < labirinto[0].length; j++) {
                System.out.print(labirinto[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char[][] getMaze(){
        return labirinto;
    }
}