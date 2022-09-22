import java.util.Random;
import java.util.ArrayList;

public class Individuo {
    public enum movimentos {
        CIMA, BAIXO, ESQUERDA, DIREITA
    }

    public int aptidao = 0;
    public ArrayList<movimentos> genes = new ArrayList<movimentos>();

    public Individuo() {
        Random random = new Random();
        for (int i = 0; i < 70; i++) {
            int valor = random.nextInt(4);
            switch (valor) {
                case 0:
                    genes.add(movimentos.CIMA);
                    break;
                case 1:
                    genes.add(movimentos.BAIXO);
                    break;
                case 2:
                    genes.add(movimentos.ESQUERDA);
                    break;
                case 3:
                    genes.add(movimentos.DIREITA);
                    break;
            }
        }
    }

    // getters and setters
    public int getAptidao() {
        return aptidao;
    }

    public void setAptidao(int fitness) {
        this.aptidao = fitness;
    }

    public ArrayList<movimentos> getGenes() {
        return genes;
    }

    public void setGenes(ArrayList<movimentos> genes) {
        this.genes = genes;
    }




}