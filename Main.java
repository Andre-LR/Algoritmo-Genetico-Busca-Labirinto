import java.io.IOException;
import IA.AlgoritmoGenetico;
import UTILS.*;

public class Main {   
    public static void main(String[] args) throws IOException {       
        Labirinto labirinto = new Labirinto("labirinto.txt");

        labirinto.imprimeLabirinto();
        labirinto.imprimePosicaoObjetivos();     
        System.out.println("Qtd de objetivos: " + Labirinto.qtdObjetivos);

        int tamPopulacao = 1500; // Deve ser PAR
        double taxaMutacao = 0.3;
        double taxaCrossover = 0.7;
        AlgoritmoGenetico ag = new AlgoritmoGenetico(labirinto, tamPopulacao, taxaMutacao, taxaCrossover);
        ag.run();


    }
    
}
