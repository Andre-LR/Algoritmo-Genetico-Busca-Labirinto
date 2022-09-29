package IA;

import java.util.Random;

import MODEL.Individuo;
import MODEL.Populacao;

public class Selecao {    

    /** 
    * Seleciona o melhor individuo e o mentém para a próxima geração
    *
    * @param populacao
    * @return Individuo com melhor aptidão
    */
    public static Individuo elitismo(Populacao populacao){
        Individuo melhorIndividuo = populacao.individuos[0];

        for(int i = 1; i < populacao.individuos.length; i++){
            if(populacao.individuos[i].getAptidao() > melhorIndividuo.getAptidao()){
                melhorIndividuo = populacao.individuos[i];
            }
        }
        return melhorIndividuo;
    }
    

    /** 
    * Seleciona os indivíduos para o crossover usando seleção por torneio
    * 
    * A seleção por torneio se da pela escolha aleatoria de N indivíduos, 
    * e então selecionamos para o crossover o indivíduo com a melhor aptidão.
    * 
    * @param populacao
    * @return O indivíduo selecionado para o crossover
    */
    public static Individuo torneio(Populacao populacao) {
        Random random = new Random();
        int individuo1, individuo2;

        individuo1 = random.nextInt(populacao.tamPopulacao);
        individuo2 = random.nextInt(populacao.tamPopulacao);

        if (populacao.individuos[individuo1].getAptidao() > populacao.individuos[individuo2].getAptidao()) {
            return populacao.individuos[individuo1];
        } else {
            return populacao.individuos[individuo2];
        }
    }
}
