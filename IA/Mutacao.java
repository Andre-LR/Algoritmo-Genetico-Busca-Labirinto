package IA;

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
        // Loop sobre os genes
        for (int geneIndex = 0; geneIndex < individuo.getGenes().size(); geneIndex++) {
            // Aplica mutação
            if (taxaMutacao > Math.random()) {
                // Pega o novo gene
                int novoGene = individuo.getGene(geneIndex);
                // Aplica mutação até que um novo gene seja gerado
                while (novoGene == individuo.getGene(geneIndex)) {
                    novoGene = (int) (Math.random() * individuo.getGenes().size() + 1);
                }
                // Define o novo gene
                individuo.setGene(geneIndex, novoGene);
            }
        }
        return individuo;
    }
}
