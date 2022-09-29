package IA;

import java.util.Random;

import MODEL.Individuo;

public class Mutacao {
    /** 
    * Mutação 
    * Realiza a mutação dos genes de um individuo aleatório
    * Taxa de mutação consiste na probabilidade de um gene ser alterado. 
    * Aplica mutação na população
    * @return População atual mutada
    */
    
    public static Individuo mutacao(double taxaMutacao, Individuo individuo) {
        int[] movimentos = {1, 2, 3, 4, 6, 7, 8, 9};
        Random random = new Random();
        
        // Loop sobre os genes
        for (int geneIndex = 0; geneIndex < individuo.getGenes().size(); geneIndex++) {
            // Aplica mutação
            if (taxaMutacao > Math.random()) {
                //troca por um movimento aleatorio
                individuo.setGene(geneIndex, movimentos[random.nextInt(movimentos.length)]);
            }
        }
        return individuo;
    }
}
