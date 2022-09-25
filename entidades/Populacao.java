import java.util.Random;

public class Populacao {
    public int tamPopulacao;
    public Maze maze;
    public int geracaoAtual = 0;
    public int qtdMaxGeracoes = 100;
    public Individuo[] individuos;
    public Individuo melhorIndividuo;
    public double aptidaoPopulacao;
    public static int maxMovimentos = 300;


    public Populacao(int tamPopulacao, Maze maze) {
        this.tamPopulacao = tamPopulacao;
        this.maze = maze;
        individuos = new Individuo[tamPopulacao];

        // Cria a população inicial
        for (int i = 0; i < tamPopulacao; i++) {
            individuos[i] = new Individuo(this.maze);
        }
        geracaoAtual++;
    }

    public Individuo getMelhorIndividuo() {
        double aptidaoMaxima = Double.MAX_VALUE;
        int aptidaoMaximaIndex = 0;
        for (int i = 0; i < individuos.length; i++) {
            if (individuos[i].getAptidao() <= aptidaoMaxima) {
                aptidaoMaxima = individuos[i].getAptidao();
                aptidaoMaximaIndex = i;
            }
        }
        return individuos[aptidaoMaximaIndex];
    }

    public int getMelhorIndividuoIndex() {
        for(int i=0; i<individuos.length; i++) {
            if(individuos[i].getAptidao() == getMelhorIndividuo().getAptidao()) {
                return i;
            }
        }
        return 0;
    }

    // getters and setters
    public int getTamanhoPopulacao() {
        return tamPopulacao;
    }

    public Individuo[] getIndividuos() {
        return individuos;
    }

    public void setIndividuos(Individuo[] individuos) {
        this.individuos = individuos;
    }

    public void addIndividuo(Individuo individuo) {
        for (int i = 0; i < individuos.length; i++) {
            if (individuos[i] == null) {
                individuos[i] = individuo;
                break;
            }
        }
    }

    public void setPopulacaoAptidao(double populacaoAptidao) {
        this.aptidaoPopulacao = populacaoAptidao;
    }

    public int getGeracaoAtual() {
        return geracaoAtual;
    }

    public Individuo getIndividuoByIndex(int index) {
        return individuos[index];
    }

    public void setIndividuoByIndex(Individuo individuo, int index) {
        individuos[index] = individuo;
    }

    public int getQtdMaxGeracoes() {
        return qtdMaxGeracoes;
    }

    public Maze getMaze() {
        return maze;
    }

    // Embaralhar a população
    public void embaralha() {
        Random rnd = new Random();
        for (int i = tamPopulacao - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Individuo a = individuos[index];
            individuos[index] = individuos[i];
            individuos[i] = a;
        }
    }

    // Remove o individuo da populacao
    public void removeIndividuo(Individuo individuo) {
        for (int i = 0; i < individuos.length; i++) {
            if (individuos[i] == individuo) {
                individuos[i] = null;
                break;
            }
        }
    }
}
