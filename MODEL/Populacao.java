package MODEL;

public class Populacao {
    public int tamPopulacao;
    public int qtdMaxGeracoes = 300;
    public int geracaoAtual = 0;
    public Individuo[] individuos;

    public Populacao(int tamPopulacao) {
        this.tamPopulacao = tamPopulacao;
        individuos = new Individuo[tamPopulacao];

        // Cria a população inicial
        for (int i = 0; i < tamPopulacao; i++) {
            individuos[i] = new Individuo();
        }
    }

    public Individuo getMelhorIndividuo() {
        double aptidaoMaxima = Double.MIN_VALUE;
        int aptidaoMaximaIndex = 0;
        for (int i = 0; i < individuos.length; i++) {
            if (aptidaoMaxima <= individuos[i].getAptidao()) {
                aptidaoMaxima = individuos[i].getAptidao();
                aptidaoMaximaIndex = i;
            }
        }
        return individuos[aptidaoMaximaIndex];
    }

    public double getMelhorAptidao(){
        return getMelhorIndividuo().getAptidao();
    }

    public Individuo[] getIndividuos() {
        return individuos;
    }

    public Individuo getIndividuo(int index) {
        return individuos[index];
    }

    public int getQtdMaxGeracoes() {
        return qtdMaxGeracoes;
    }

    public int getGeracaoAtual() {
        return geracaoAtual;
    }

    public void proximaGeracao() {
        this.geracaoAtual++;
    }


    public String toString() {
        String s = "\n\n\n\n============================================================= Geracao " + geracaoAtual + " =============================================================";
        for (int i = 0; i < individuos.length; i++) {
            s += "\n\nIndividuo: " + i + " --> " + individuos[i].toString();
        }
        
        return s;
    }

    public String printGeracao() {
        String s = "\n\n\n\n============================================================= Geracao " + geracaoAtual + " =============================================================";
        s += "\nMelhor aptidao da geracao: " + getMelhorAptidao();
        s += "\nObjetivos alcancados: " + getMelhorIndividuo().objetivosEncontrados;
        s += "\nMelhor Individuo: " + getMelhorIndividuo().toString();
        return s;
    }
}
