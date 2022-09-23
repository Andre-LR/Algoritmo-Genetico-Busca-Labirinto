import java.util.Random;

public class Populacao {
    public int tamPopulacao;
    public int qtdMaxGeracoes = 500;
    public int geracaoAtual = 0;
    public Individuo[] individuos;
    public double melhorAptidao = 0;
    public int melhorAptidaoIndex;
    public double aptidaoPopulacao = 0;


    public Populacao(int tamPopulacao) {
        this.tamPopulacao = tamPopulacao;
        individuos = new Individuo[tamPopulacao];

        // Cria a população inicial
        for (int i = 0; i < tamPopulacao; i++) {
            individuos[i] = new Individuo();
        }
    }

    public Individuo getIndividuoMelhorAptidao() {
        double aptidaoMaxima = Double.MAX_VALUE;
        int aptidaoMaximaIndex = 0;
        for (int i = 0; i < individuos.length; i++) {
            if (aptidaoMaxima >= individuos[i].getAptidao()) {
                aptidaoMaxima = individuos[i].getAptidao();
                aptidaoMaximaIndex = i;
            }
        }

        melhorAptidao = individuos[aptidaoMaximaIndex].getAptidao();
        melhorAptidaoIndex = aptidaoMaximaIndex;
        return individuos[aptidaoMaximaIndex];
    }

    public Individuo getIndividuoSegundoMelhorAptidao() {
        double aptidaoMaxima = Double.MAX_VALUE;
        int aptidaoMaximaIndex = 0;
        double aptidaoMaxima2 = Double.MAX_VALUE;
        int aptidaoMaximaIndex2 = 0;
        for (int i = 0; i < individuos.length; i++) {
            if (aptidaoMaxima >= individuos[i].getAptidao()) {
                aptidaoMaxima2 = aptidaoMaxima;
                aptidaoMaximaIndex2 = aptidaoMaximaIndex;
                aptidaoMaxima = individuos[i].getAptidao();
                aptidaoMaximaIndex = i;
            } else if (aptidaoMaxima2 >= individuos[i].getAptidao()) {
                aptidaoMaxima2 = individuos[i].getAptidao();
                aptidaoMaximaIndex2 = i;
            }
        }
        return individuos[aptidaoMaximaIndex2];
    }

    public int getIndexMelhorAptidao() {
        Individuo i = getIndividuoMelhorAptidao();
        return melhorAptidaoIndex;
    }

    // getters and setters
    public int getTamPopulacao() {
        return tamPopulacao;
    }

    public void setTamPopulacao(int tamPopulacao) {
        this.tamPopulacao = tamPopulacao;
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

    public double getMelhorAptidao() {
        return melhorAptidao;
    }

    public void setMelhorAptidao(int melhorAptidao) {
        this.melhorAptidao = melhorAptidao;
    }

    public void setPopulacaoAptidao(double populacaoAptidao) {
        this.aptidaoPopulacao = populacaoAptidao;
    }

    public int getQtdMaxGeracoes() {
        return qtdMaxGeracoes;
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
}
