package IA;

import java.util.Random;

import MODEL.*;
import UTILS.*;

public class AlgoritmoGenetico {
    public Labirinto labirinto;
    public Populacao populacao;
    public int popSize;
    public double taxaMutacao;
    public double taxaCrossover;    //(0.1 à 1.0) -> % de indivíduos que serão selecionados para crossover

    public AlgoritmoGenetico(Labirinto labirinto, int popSize, double taxaMutacao, double taxaCrossover) {
        this.labirinto = labirinto;
        this.popSize = popSize;
        this.taxaMutacao = taxaMutacao;
        this.taxaCrossover = taxaCrossover;
    }

    public void run(){
        // gera a população inicial
        populacao = new Populacao(popSize);

        // avalia a população inicial
        for (int i = 0; i < populacao.getIndividuos().length; i++) {
            populacao.getIndividuo(i).calculaAptidao();
        }
        
        // Vizualiza geracao inicial
        System.out.println(populacao.toString());
        
        // enquanto não atingir o critério de parada 
        // =========================== PRECISA TESTAR E ATUALIZAR ==================================
        // =========================== PRECISA TESTAR E ATUALIZAR ==================================
        // =========================== PRECISA TESTAR E ATUALIZAR ==================================
        while(populacao.getQtdMaxGeracoes() > populacao.getGeracaoAtual() && populacao.getMelhorAptidao() > 0){
            // seleciona os indivíduos para crossover
            Populacao popIntermediaria = populacao;

            Individuo[] individuosSelecionados = new Individuo[popIntermediaria.individuos.length];
            for(int i = 0; i < popIntermediaria.individuos.length; i++){
                individuosSelecionados[i] = Selecao.torneio(populacao);
            }

            // realiza o crossover e gera a população intermediária
            for(int i = 0; i < popIntermediaria.individuos.length; i+=2){
                popIntermediaria.individuos[i] = Crossover.crossover(taxaCrossover,individuosSelecionados[i], individuosSelecionados[i+1]);
                popIntermediaria.individuos[i+1] = Crossover.crossover(taxaCrossover,individuosSelecionados[i+1], individuosSelecionados[i]);
            }

            // Elitismo
            popIntermediaria.individuos[0] = Selecao.elitismo(populacao); 

            // realiza a mutação de individuo aleatório (não pode ser o elistista)
            Random random = new Random();
            int index = random.nextInt(popIntermediaria.individuos.length-1) + 1;

            popIntermediaria.individuos[index] = Mutacao.mutacao(taxaMutacao, popIntermediaria.individuos[index]);

            // substitui a população antiga pela nova
            populacao = popIntermediaria;
            this.populacao.proximaGeracao();

            // avalia a nova população
            for (int i = 0; i < populacao.getIndividuos().length; i++) {
                populacao.getIndividuo(i).calculaAptidao();
            }

            // Vizualiza nova geracao
            System.out.println(populacao.toString());

          // populacao.avaliar(labirinto);
        }

        // imprime o melhor indivíduo e o caminho percorrido por ele
        System.out.println("Melhor Individuo: " + populacao.getMelhorIndividuo().toString());

    }

    /**
     * Aptidão da população
     * 
     * Calcula a aptidão de cada indivíduo da população, e então obtém a Aptidão total 
     * daquela população. Verificar a aptidão total de cada população irá servir para 
     * verificar se os indivíduos estão evoluindo.
     * 
     * @param populacao que será avaliada
     * @param maze labirinto que será usado para testar a população
     */
    public void calculaAptidaoPopulacao(Populacao populacao, Labirinto labirinto) {
        double populacaoAptidao = 0;

        for (Individuo individuo : populacao.getIndividuos()) {
           // populacaoAptidao += this.calculaAptidao(individuo, maze);
        }

        populacao.setPopulacaoAptidao(populacaoAptidao);
    }

}