import java.util.ArrayList;
import java.util.Random;

public class AlgoritmoGenetico {
    public int tamPopulacao;
    public double taxaMutacao;
    public double taxaCrossover;
    public int countElitismo;
    public int tamanhoTorneio;

    public AlgoritmoGenetico(int tamPopulacao, double taxaMutacao, double taxaCrossover, int countElitismo, int tamanhoTorneio) {
        this.tamPopulacao = tamPopulacao;
        this.taxaMutacao = taxaMutacao;
        this.taxaCrossover = taxaCrossover;
        this.countElitismo = countElitismo;
        this.tamanhoTorneio = tamanhoTorneio;
    }

    // Inicializa a população
    public Populacao initPopulacao() {
        return new Populacao(this.tamPopulacao);
    }

    /**
     * Calcula a aptidão de um indivíduo em um dado labirinto
     *  ==================================FAZER======================================
     */
    public double calculaAptidao(Individuo individuo, Maze maze) {

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

    /** 
     * Seleciona os indivíduos para o crossover usando seleção por torneio
     * 
     * A seleção por torneio se da pela escolha aleatoria de N indivíduos, 
     * e então selecionamos para o crossover o indivíduo com a melhor aptidão.
     * 
     * @param population
     * @return O indivíduo selecionado para o crossover
     */
    public Individuo selecionaIndividuoCrossOver(Populacao populacao) {
        // Cria torneio
        Populacao torneio = new Populacao(this.tamanhoTorneio);

        // Adiciona indivíduos aleatórios ao torneio
        populacao.embaralha();
        for (int i = 0; i < this.tamanhoTorneio; i++) {
            Individuo individuoEscolhido = populacao.getIndividuoByIndex(i);
            torneio.addIndividuo(individuoEscolhido);
        }

        return torneio.getIndividuoMelhorAptidao();
    }

    /** 
     * Aplica mutação na população
     * @param population
     * @return População mutada
     */
    public Populacao mutacaoPopulacao(Populacao populacao) {
        // Inicializa nova população
        Populacao novaPopulacao = new Populacao(this.tamPopulacao);

        // Loop sobre a população atual por aptidão
        for (int populationIndex = 0; populationIndex < populacao.getTamPopulacao(); populationIndex++) {
            Individuo individual = populacao.getIndividuoMelhorAptidao();

            // =================VERIFICAR SE ESTÁ CORRETO==================
            // loop sobre os genes do indivíduo
            for (int geneIndex = 0; geneIndex < individual.genes.size(); geneIndex++) {
                // Não faz mutação se o indivíduo for o melhor(ELITISMO) da população
                if (populationIndex >= this.countElitismo) {
                    // Verifica se o gene deve ser mutado
                    if (this.taxaMutacao > Math.random()) {
                        // Pega o novo gene
                        int novoGene = randInt(1,4);
                        // Muta o gene
                        individual.setGene(geneIndex, novoGene);
                    }
                }
            }

            // Adiciona o indivíduo mutado na nova população
            novaPopulacao.setIndividuoByIndex(individual, populationIndex);
        }

        // Retorna a nova população mutada
        return novaPopulacao;
    }

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
     * @param population que sofrerá o crossover
     * @return Populacao gerada
     */
    public Populacao crossoverPopulation(Populacao populacao) {
        // Cria nova população
        Populacao novaPopulacao = new Populacao(tamPopulacao);

        // Loop sobre a população atual por aptidão
        for (int populationIndex = 0; populationIndex < tamPopulacao; populationIndex++) {
            Individuo individuo_01 = populacao.getIndividuoMelhorAptidao();

            // Aplicamos crossover no indivíduo????
            if (this.taxaCrossover > Math.random() && populationIndex >= this.countElitismo) {
                // Ininicializa o filho
                Individuo filhoGerado = new Individuo();

                // Busca secundo indivíduo para o crossover
                Individuo individuo_02 = this.selecionaIndividuoCrossOver(populacao);

                // Pega o ponto de corte para o crossover
                int pontoCorte = (int) (Math.random() * (individuo_01.genes.size() + 1));

                // Loop sobre os genes
                for (int geneIndex = 0; geneIndex < individuo_01.genes.size(); geneIndex++) {
                    // Usa metade dos genes do indivíduo 01 e metade dos genes do indivíduo 02
                    if (geneIndex < pontoCorte) {
                        filhoGerado.setGene(geneIndex, individuo_01.getGene(geneIndex));
                    } else {
                        filhoGerado.setGene(geneIndex, individuo_02.getGene(geneIndex));
                    }
                }

                // Adiciona o filho Gerado na população
                novaPopulacao.setIndividuoByIndex(filhoGerado, populationIndex);
            } else {
                // Adiciona o Individuo na nova população sem aplicar crossover
                novaPopulacao.setIndividuoByIndex(individuo_01, populationIndex);
            }
        }

        return novaPopulacao;
    }

    private static int randInt(int min, int max){
        Random rand = new Random();
        int randomNum = rand.nextInt((max-min)+1)+min;
        return randomNum;
    }

}
