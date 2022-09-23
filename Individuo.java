import java.util.Random;
import java.util.ArrayList;

public class Individuo {

    public double aptidao = 0;
    public ArrayList<Integer> genes = new ArrayList<Integer>();
    public int[] movimentosPossiveis = {1, 2, 3, 4, 5, 6, 7, 8, 9};


    /** Initializes random individual.
     *
     * The chromosome is made of 1s, 2s, 3s, 4s, 6s, 7s, 8s and 9s, presenting the directions of robot
     * 8 - move up
     * 4 - move left
     * 6 - move right
     * 2 - move down
     * 3 - move down right
     * 1 - move down left
     * 7 - move up left
     * 9 - move up right
     */
    public Individuo() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int valor = movimentosPossiveis[random.nextInt(movimentosPossiveis.length)];

            if (valor == 5) {
                continue;
            }
            genes.add(valor);
        }
    }
    

    // getters and setters
    public double getAptidao() {
        return aptidao;
    }

    public void setAptidao(double fitness) {
        this.aptidao = fitness;
    }

    public ArrayList<Integer> getGenes() {
        return genes;
    }

    // Para mutação -> Coloca novo gene na posição index
    public void setGene(int index, int gene) {
        genes.set(index, gene);
    }

    public int getGene(int index) {
        return genes.get(index);
    }

    public String toString() {
        String geneString = "Genes: [";
        for (int i = 0; i < 100; i++) {
            geneString +=  getGenes().get(i) + ", ";
        }

        geneString = geneString + "]";
        return geneString;
    }

     

}