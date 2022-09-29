package IA;

import MODEL.Individuo;

public class Crossover {
    /** 
    * Crossover da população usando ponto fixo
    * 
    * Corta os indivíduos em dois pontos, e troca os genes para gerar o novo indivíduo
    * 
    * Exemplo:
    * Individuo_1 : AAAAAAAAAA
    * Individuo_2 : BBBBBBBBBB
    * Filho_Gerado: AAAABBBBBB
    *
    * @return atualizar a população atual
    */
    public static Individuo crossover(double taxaCrossover, Individuo pai, Individuo mae) {           
        Individuo filho = new Individuo();     

        // Filtra para que apenas parcela da população (conforme taxaCrossover) realize crossover
        // Caso não bata o indice, o individuo no Index é mantido na nova população
        if (taxaCrossover > Math.random()) {

            // Pega o ponto de corte para o crossover
            int pontoCorte = (int) (Math.random() * (pai.getGenes().size() + 1));

            // Loop sobre os genes
            for (int geneIndex = 0; geneIndex < pai.getGenes().size(); geneIndex++) {
                // Usa metade dos genes do indivíduo PAI e metade dos genes do indivíduo MÃE
                if (geneIndex < pontoCorte) {
                    filho.setGene(geneIndex, pai.getGene(geneIndex));
                } else {
                    filho.setGene(geneIndex, mae.getGene(geneIndex));
                }
            }
        }else{
            // Caso não bata o índice, mantém os genes da mãe
            filho = mae;
        }
        return filho;
    }
}
