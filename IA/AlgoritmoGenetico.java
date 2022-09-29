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

        // enquanto não atingir o critério de parada
        while(true){
            // seleciona os indivíduos para crossover
            Populacao popIntermediaria = new Populacao(popSize);

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

            // avalia a nova população
          // populacao.avaliar(labirinto);
        }
    }

 
    //========================================== APTIDÃO ============================================

    /**
     * Calcula a aptidão de um indivíduo em um dado labirinto
     * 
     * O cálculo é realizado com base nos movimentos de um indivíduo no labirinto
     * informado.
     *  
    * Calcula a aptidão - Função heurística dos cromossomos
    * Heurística: Melhor pontuação
    * 
    *    Pontuação: 
    *     - Subtair 10 pontos para cada movimento
    *     - Subtrair 200 pontos para cada movimento que o indivíduo chega em uma posição bloqueada
    *     - Subtrair 500 pontos ao passar 3x no mesmo bloco
    *     - Somar 500 pontos ao pegar comida
    *     - Somar 1000 pontos ao pegar todas as comidas
    *
     * 
     *  ==================================FAZER======================================
     */
    public double calculaAptidao(Individuo individuo) {
        //calcula a aptidão do indivíduo
        




        




        // Armazena a aptidão
       // individuo.setAptidao(aptidao);

        return 0;
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



    /**
     * Verifica se a população possui uma condição de parada
     * Condições:
     *  - Se a população atingiu o número máximo de gerações
     *  - =================INCLUIR DEMAIS SE FOR O CASO==================
     *  - 
     */
    public boolean contemCondicaoParada(Populacao populacao) {
        return populacao.getGeracaoAtual() == populacao.getQtdMaxGeracoes();
    }

    private static int randInt(int min, int max){
        Random rand = new Random();
        int randomNum = rand.nextInt((max-min)+1)+min;
        return randomNum;
    }

}