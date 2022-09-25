import java.util.ArrayList;
import java.util.Random;

public class AlgoritmoGenetico {
    public Populacao populacao;
    public double taxaMutacao;
    public double taxaCrossover;    //(0.1 à 1.0) -> % de indivíduos que serão selecionados para crossover

    public AlgoritmoGenetico(Populacao populacao, double taxaMutacao, double taxaCrossover) {
        this.populacao = populacao;
        this.taxaMutacao = taxaMutacao;
        this.taxaCrossover = taxaCrossover;
    }

 //========================================== SELEÇÃO ==============================================

    /** 
     * Seleciona os indivíduos para o crossover usando seleção por torneio
     * 
     * A seleção por torneio se da pela escolha aleatoria de N indivíduos, 
     * e então selecionamos para o crossover o indivíduo com a melhor aptidão.
     * 
     * @param populacao
     * @return O indivíduo selecionado para o crossover
     */
    public Individuo selecionaIndividuoPorTorneio(Populacao populacao) {
        // Cria torneio
        Populacao torneio = new Populacao(this.populacao.getTamanhoPopulacao(), this.populacao.getMaze());

        // Adiciona indivíduos em ordem aleatórias ao torneio
        populacao.embaralha();
        for (int i = 0; i < torneio.getTamanhoPopulacao(); i++) {
            Individuo primeiroIndividuo = populacao.getIndividuoByIndex(i);
            torneio.addIndividuo(primeiroIndividuo);
        }

        // Escolhe primeiro individuo
        int indexAleatorio1 = new Random().nextInt(torneio.getTamanhoPopulacao());
        Individuo primeiroIndividuo = torneio.getIndividuoByIndex(indexAleatorio1);
        
        // Escolhe segundo individuo, cuidando para não pegar o mesmo Index
        Individuo segundoIndividuo = new Individuo(this.populacao.getMaze());
        while(true){
            int indexAleatorio2 = new Random().nextInt(torneio.getTamanhoPopulacao());
            if(indexAleatorio1 != indexAleatorio2){
                segundoIndividuo = torneio.getIndividuoByIndex(indexAleatorio2);
                break;
            }
        }

        if(primeiroIndividuo.getAptidao() > segundoIndividuo.getAptidao()){
            return primeiroIndividuo;
        }else{
            return segundoIndividuo;
        }
    } 

 //========================================== CROSSOVER ===========================================

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
    public void crossoverPopulacao() {
        System.out.println("\n=============== Início crossover da população ===============");
        System.out.println("\nCria População intermediária...");
        Populacao populacaoIntermediaria = new Populacao(this.populacao.getTamanhoPopulacao(), this.populacao.getMaze());

        // ELITISMO -> preserva o melhor indivíduo da população atual
        populacaoIntermediaria.addIndividuo(this.populacao.getMelhorIndividuo());
        System.out.println("\n=============== Elitismo ===============");
        System.out.println("Melhor indivíduo colocado na população intermediária...");

        // Realiza o crossover dos invivíduos
        System.out.println("\n=============== Seleção por Torneio ===============");
        System.out.println("Realizando crossover dos indivíduos selecionados por torneio...");

        // Inicia no index 1 pois o index 0 será ocupado pelo indivíduo elitista que não sofre crossover
        for(int i=1; i<populacaoIntermediaria.getTamanhoPopulacao(); i++){
            // Individuos Selecionados por torneio para o crossover
            Individuo pai = selecionaIndividuoPorTorneio(populacao);
            Individuo mae = selecionaIndividuoPorTorneio(populacao);

            // Filtra para que apenas parcela da população (conforme taxaCrossover) realize crossover
            // Caso não bata o indice, o individuo no Index é mantido na nova população
            if (this.taxaCrossover > Math.random()) {
                Individuo filho = new Individuo(this.populacao.getMaze());

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

                // Adiciona o filho Gerado na populacao intermediaria
                populacaoIntermediaria.addIndividuo(filho);

            } else {
                // Mantém o individuo do index da população atual na população intermediaria
                populacaoIntermediaria.addIndividuo(populacao.getIndividuoByIndex(i));
            }
            
        }

        // Atualiza a população atual
        this.populacao = populacaoIntermediaria;
        System.out.println("\n=============== Fim crossover da população ===============");
    }

 //========================================== MUTAÇÃO ============================================

    /** 
    * Mutação 01
    * Realiza uma mutação de X% nos genes de TODOS os indivíduos da população
    * Aplica mutação na população
    * @return População atual mutada
    */
    public void mutacaoPopulacao01() {
        // Inicializa nova população
        Populacao populacaoMutada = new Populacao(this.populacao.getTamanhoPopulacao(), this.populacao.getMaze());
        int[] movimentosPossiveis = {1, 2, 3, 4, 6, 7, 8, 9};

        // Inicia no index 1 pois o index 0 será ocupado pelo indivíduo elitista que não sofre mutação
        for (int i = 1; i < this.populacao.getTamanhoPopulacao(); i++) {
            // Individuo a ser mutado
            Individuo individuoMutado = this.populacao.getIndividuoByIndex(i);

            for (int geneIndex = 0; geneIndex < individuoMutado.getGenes().size(); geneIndex++) {
                // Verifica se o gene deve ser mutado conforme a taxa de mutação
                if (this.taxaMutacao > Math.random()) {
                    // Gera um gene aleatório entre os movimentos Possiveis = {1, 2, 3, 4, 6, 7, 8, 9};
                    int geneMutado = movimentosPossiveis[(int) (Math.random() * movimentosPossiveis.length)];

                    // Muta o gene
                    individuoMutado.setGene(geneIndex, geneMutado);
                }
            }
            // Adiciona o indivíduo mutado na nova população
            populacaoMutada.addIndividuo(individuoMutado);
        }
        // Atualiza a população atual
        this.populacao = populacaoMutada;
    }
    
    /** 
    * Mutação 02
    * Realiza a mutação de X% dos individuos da população
    * Aplica mutação na população
    * @return População atual mutada
    */
    public void mutacaoPopulacao02(){
        Populacao populacaoMutada = new Populacao(this.populacao.getTamanhoPopulacao(), this.populacao.getMaze());
        int[] movimentosPossiveis = {1, 2, 3, 4, 6, 7, 8, 9};

        // Inicia no index 1 pois o index 0 será ocupado pelo indivíduo elitista que não sofre mutação
        for (int i = 1; i < this.populacao.getTamanhoPopulacao(); i++) {
            // Individuo a ser mutado
            Individuo individuoMutado = this.populacao.getIndividuoByIndex(i);

            // Verifica se o individuo deve ser mutado conforme a taxa de mutação
            if (this.taxaMutacao > Math.random()) {
                for (int geneIndex = 0; geneIndex < individuoMutado.getGenes().size(); geneIndex++) {
                    if (this.taxaMutacao > Math.random()) {

                        // Gera um gene aleatório entre os movimentos Possiveis = {1, 2, 3, 4, 6, 7, 8, 9};
                        int geneMutado = movimentosPossiveis[(int) (Math.random() * movimentosPossiveis.length)];

                        // Muta o gene
                        individuoMutado.setGene(geneIndex, geneMutado);
                    }
                }
            }
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
        individuo.setAptidao(aptidao);

        return aptidao;
    }

    /**
     * Aptidão da população
     * 
     * Calcula a aptidão de cada indivíduo da população, e então obtém a Aptidão total 
     * daquela população. Verificar a aptidão total de cada população irá servir para 
     * veririficar se os indivíduos estão evoluindo.
     * 
     * @param populacao que será avaliada
     * @param maze labirinto que será usado para testar a população
     */
    public void calculaAptidaoPopulacao(Populacao populacao, Maze maze) {
        double populacaoAptidao = 0;

        for (Individuo individuo : populacao.getIndividuos()) {
            populacaoAptidao += this.calculaAptidao(individuo, maze);
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
