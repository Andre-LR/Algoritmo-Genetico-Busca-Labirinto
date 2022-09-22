public class Populacao {
    public int popSize;
    public Individuo[] individuos;
    public int melhorAptidao = 0;


    public Populacao(int popSize) {
        this.popSize = popSize;
        individuos = new Individuo[popSize];

        // Cria a população inicial
        for (int i = 0; i < popSize; i++) {
            individuos[i] = new Individuo();
        }
    }

    public Individuo getFittest() {
        int aptidaoMaxima = Integer.MIN_VALUE;
        int aptidaoMaximaIndex = 0;
        for (int i = 0; i < individuos.length; i++) {
            if (aptidaoMaxima <= individuos[i].getAptidao()) {
                aptidaoMaxima = individuos[i].getAptidao();
                aptidaoMaximaIndex = i;
            }
        }
        melhorAptidao = individuos[aptidaoMaximaIndex].getAptidao();

        return individuos[aptidaoMaximaIndex];
    }

    public Individuo getSecondFittest() {
        int aptidaoMaxima = Integer.MIN_VALUE;
        int aptidaoMaximaIndex = 0;
        int aptidaoMaxima2 = Integer.MIN_VALUE;
        int aptidaoMaximaIndex2 = 0;
        for (int i = 0; i < individuos.length; i++) {
            if (aptidaoMaxima <= individuos[i].getAptidao()) {
                aptidaoMaxima2 = aptidaoMaxima;
                aptidaoMaximaIndex2 = aptidaoMaximaIndex;
                aptidaoMaxima = individuos[i].getAptidao();
                aptidaoMaximaIndex = i;
            } else if (aptidaoMaxima2 <= individuos[i].getAptidao()) {
                aptidaoMaxima2 = individuos[i].getAptidao();
                aptidaoMaximaIndex2 = i;
            }
        }
        return individuos[aptidaoMaximaIndex2];
    }

    public int getLeastFittestIndex() {
        int aptidaoMinima = Integer.MAX_VALUE;
        int aptidaoMinimaIndex = 0;
        for (int i = 0; i < individuos.length; i++) {
            if (aptidaoMinima >= individuos[i].getAptidao()) {
                aptidaoMinima = individuos[i].getAptidao();
                aptidaoMinimaIndex = i;
            }
        }
        return aptidaoMinimaIndex;
    }

    public int getFittestIndex() {
        int aptidaoMaxima = Integer.MIN_VALUE;
        int aptidaoMaximaIndex = 0;
        for (int i = 0; i < individuos.length; i++) {
            if (aptidaoMaxima <= individuos[i].getAptidao()) {
                aptidaoMaxima = individuos[i].getAptidao();
                aptidaoMaximaIndex = i;
            }
        }
        return aptidaoMaximaIndex;
    }

    // getters and setters
    public int getPopSize() {
        return popSize;
    }

    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }

    public Individuo[] getIndividuos() {
        return individuos;
    }

    public void setIndividuos(Individuo[] individuos) {
        this.individuos = individuos;
    }

    public int getMelhorAptidao() {
        return melhorAptidao;
    }

    public void setMelhorAptidao(int melhorAptidao) {
        this.melhorAptidao = melhorAptidao;
    }
}
