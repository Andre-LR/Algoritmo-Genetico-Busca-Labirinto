import java.io.IOException;

import IA.AlgoritmoGenetico;
import UTILS.*;

public class Main {   
    public static void main(String[] args) throws IOException {       
        Labirinto labirinto = new Labirinto("Labirinto");

        labirinto.imprimeLabirinto();
        labirinto.imprimePosicaoObjetivos();     
        System.out.println("Qtd de objetivos: " + Labirinto.qtdObjetivos);  

        int tamPopulacao = 5;
        double taxaMutacao = 0.5;
        double taxaCrossover = 0.2;
        AlgoritmoGenetico ag = new AlgoritmoGenetico(labirinto, tamPopulacao, taxaMutacao, taxaCrossover);
        ag.run();

    }
    
}
