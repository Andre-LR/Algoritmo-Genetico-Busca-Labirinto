import javax.swing.*;
import java.awt.*;

public class MazeDisplay extends JPanel {
    private char[][] maze;

    public MazeDisplay(Maze maze, String title){
        this.maze = maze.getMaze();
        JFrame f = new JFrame();
        f.getContentPane().add(this);
        setBackground(Color.WHITE);
        f.setTitle(title);
        f.setSize(400,400); // Tamanho janela display
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setMaze(char [][] otherMaze){
        for (int i = 0; i < otherMaze.length; i++){
            for (int j = 0; j < otherMaze[0].length; j++){
                maze[i][j] = otherMaze[i][j];
            }
        }
    }

    
    /**
     * Display do Labirinto
     * 
     * Legendas das cores:
     * BRANCO   = 0 = Livre
     * PRETO    = 1 = Parede
     * VERMELHO = E = Posição Inicial
     * AZUL     = C = Objetivos a serem buscados
     * VERDE    = Caminho percorrido
     */
    public void paintComponent(Graphics g){ 
        int frame_width = 600;
        int frame_height = 500;
        g.setColor(Color.white);  // paint the white background:
        g.fillRect(0, 0, frame_width, frame_height);
        g.setColor(Color.red);

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Color color;
                switch (maze[row][col]) {
                    case '1' : color = Color.BLACK; break;
                    case 'E' : color = Color.RED; break;
                    case 'C' : color = Color.BLUE; break;
                    case '5' : color = Color.GREEN; break;
                    default : color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);
            }
        }

    }


}
