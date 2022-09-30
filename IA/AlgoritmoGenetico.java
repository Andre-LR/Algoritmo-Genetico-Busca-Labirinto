package IA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

import MODEL.*;
import UTILS.*;

public class AlgoritmoGenetico {
    public Labirinto labirinto;
    public Populacao populacao;
    public int popSize;
    public double taxaMutacao;
    public double taxaCrossover;    //(0.1 à 1.0) -> % de individuos que serão selecionados para crossover

    public AlgoritmoGenetico(Labirinto labirinto, int popSize, double taxaMutacao, double taxaCrossover) {
        this.labirinto = labirinto;
        this.popSize = popSize;
        this.taxaMutacao = taxaMutacao;
        this.taxaCrossover = taxaCrossover;
    }

    public void run() throws FileNotFoundException{
        PrintStream out = new PrintStream(new File("log.txt")); System.setOut(out); System.out.println("Log de execução do algoritmo genético");
        // gera a populacao inicial
        System.out.println("========================== Gerando populacao inicial ==========================");
        populacao = new Populacao(popSize);

        // avalia a populacao inicial
        for (int i = 0; i < populacao.getIndividuos().length; i++) {
            populacao.getIndividuo(i).calculaAptidao();
        }
        
        // Printa geração
        System.out.println(populacao.printGeracao());
        
        while(populacao.getQtdMaxGeracoes() > populacao.getGeracaoAtual() && populacao.getMelhorIndividuo().objetivosEncontrados < Labirinto.qtdObjetivos){
            // seleciona os individuos para crossover
            System.out.println("========================== Selecionando individuos para crossover ==========================");
            Populacao popIntermediaria = populacao;

            System.out.println("========================== Realizando selecao por torneio ==========================");
            Individuo[] individuosSelecionados = new Individuo[popIntermediaria.individuos.length];
            for(int i = 0; i < popIntermediaria.individuos.length; i++){
                individuosSelecionados[i] = Selecao.torneio(populacao);
            }

            // realiza o crossover e gera a populacao intermediária
            System.out.println("========================== Realizando crossover ==========================");
            for(int i = 0; i < popIntermediaria.individuos.length; i+=2){
                popIntermediaria.individuos[i] = Crossover.crossover(taxaCrossover,individuosSelecionados[i], individuosSelecionados[i+1]);
                popIntermediaria.individuos[i+1] = Crossover.crossover(taxaCrossover,individuosSelecionados[i+1], individuosSelecionados[i]);
            }

            // Elitismo
            System.out.println("========================== Realizando selecao por elitismo ==========================");
            popIntermediaria.individuos[0] = Selecao.elitismo(populacao); 

            // realiza a mutacao de individuo aleatório (não pode ser o elistista)
            System.out.println("========================== Realizando mutacao ==========================");
            Random random = new Random();
            int index = random.nextInt(popIntermediaria.individuos.length-1) + 1;

            popIntermediaria.individuos[index] = Mutacao.mutacao(taxaMutacao, popIntermediaria.individuos[index]);

            // substitui a populacao antiga pela nova
            System.out.println("========================== Substituindo populacao ==========================");
            populacao = popIntermediaria;
            this.populacao.proximaGeracao();

            // avalia a nova populacao
            System.out.println("========================== Avaliando populacao ==========================");
            for (int i = 0; i < populacao.getIndividuos().length; i++) {
                populacao.getIndividuo(i).calculaAptidao();
            }

            System.out.println(populacao.printGeracao());
        }


    }
}