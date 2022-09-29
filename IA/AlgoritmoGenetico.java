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
        //System.out.println(populacao.toString());
        
        // imprime o melhor indivíduo
        //System.out.println("\n\nMelhor Individuo da geraçao: " + populacao.getMelhorIndividuo().toString());

        // Printa geração
        System.out.println(populacao.printGeracao());
        
        // enquanto não atingir o critério de parada 
        // =========================== PRECISA TESTAR E ATUALIZAR ==================================
        // =========================== PRECISA TESTAR E ATUALIZAR ==================================
        // =========================== PRECISA TESTAR E ATUALIZAR ==================================
        while(populacao.getQtdMaxGeracoes() > populacao.getGeracaoAtual() && populacao.getMelhorIndividuo().objetivosEncontrados < Labirinto.qtdObjetivos){
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
           // System.out.println(populacao.toString());

            // imprime o melhor indivíduo
            //System.out.println("\n\nMelhor Individuo da geraçao: " + populacao.getMelhorIndividuo().toString());

            // Printa geração
            System.out.println(populacao.printGeracao());
        }


    }
}