package MODEL;

import java.util.Random;

import UTILS.Labirinto;

import java.util.ArrayList;

public class Individuo {
    public int[] posicaoAtual;
    public int objetivosEncontrados = 0;
    public double aptidao;
    public ArrayList<Integer> genes = new ArrayList<Integer>();
    public int[] movimentosPossiveis = {1, 2, 3, 4, 6, 7, 8, 9};
    public ArrayList<int[]> posicoesVisitadas = new ArrayList<int[]>();


    /** Initializes random individual.
     *
     * 
     * 8 - move up
     * 4 - move left
     * 6 - move right
     * 2 - move down
     * 3 - move down right
     * 1 - move down left
     * 7 - move up left
     * 9 - move up right
     */
    public Individuo() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int valor = movimentosPossiveis[random.nextInt(movimentosPossiveis.length)];

            if (valor == 5) {
                continue;
            }
            genes.add(valor);
        }

        posicaoAtual = Labirinto.posicaoInicial;
    }
    
    // getters and setters
    public double getAptidao() {
        return aptidao;
    }

    public void setAptidao(double fitness) {
        this.aptidao = fitness;
    }

    public ArrayList<Integer> getGenes() {
        return genes;
    }

    // Para mutação -> Coloca novo gene na posição index
    public void setGene(int index, int gene) {
        genes.set(index, gene);
    }

    public int getGene(int index) {
        return genes.get(index);
    }

    public String toString() {
        String geneString = "Genes: [";
        for (int i = 0; i < 100; i++) {
            geneString +=  getGenes().get(i) + ", ";
        }

        geneString = geneString + "]";

        geneString += "\nAptidão: " + getAptidao();
        geneString += "\nQuantidade de objetivos encontrados: " + objetivosEncontrados;
        return geneString;
    }





    // ===================================== CÁLCULO DE APTIDÃO =====================================
    // Cálcula aptidão do indivíduo
    public void calculaAptidao(){
        double fitness = 100;
        // Se encontrou todos os objetivos, quebra
        for (int gene : genes) {
            if(objetivosEncontrados == Labirinto.qtdObjetivos){
                fitness -= 100;
                break;
            }



            // Move para esquerda
            if(gene == 4){
                // Se for movimento inválido, quebra
                if(posicaoAtual[0] < 0 || posicaoAtual[1]-1 < 0 || posicaoAtual[0] > Labirinto.linhas-1 || posicaoAtual[1]-1 > Labirinto.colunas-1){
                    break;
                }else{
                    if(Labirinto.labirinto[posicaoAtual[0]][posicaoAtual[1]-1] == '0' || Labirinto.labirinto[posicaoAtual[0]][posicaoAtual[1]-1] == 'C'){

                        // atualiza posição atual
                        posicaoAtual = new int[]{posicaoAtual[0], posicaoAtual[1]-1};
                        
                        // se posicao atual ja foi visitada
                        if(posicoesVisitadas.contains(posicaoAtual)){
                            fitness += 50;
                        }else{
                            fitness += 10;
                            // se a posicao atual for um objetivo
                            if(Labirinto.labirinto[posicaoAtual[0]][posicaoAtual[1]-1] == 'C'){
                                objetivosEncontrados++;
                                fitness -= 20;
                            }
                            // adiciona visitados
                            posicoesVisitadas.add(posicaoAtual);
                        }
                    }else{
                        break;
                    }
                }
            }

            // Move para direita
            if(gene == 6){
                // Se for movimento inválido, quebra
                if(posicaoAtual[0] < 0 || posicaoAtual[1]+1 < 0 || posicaoAtual[0] > Labirinto.linhas-1 || posicaoAtual[1]+1 > Labirinto.colunas-1){
                    break;
                }else{
                    if(Labirinto.labirinto[posicaoAtual[0]][posicaoAtual[1]+1] == '0' || Labirinto.labirinto[posicaoAtual[0]][posicaoAtual[1]+1] == 'C'){

                        // atualiza posição atual
                        posicaoAtual = new int[]{posicaoAtual[0], posicaoAtual[1]+1};
                        
                        // se posicao atual ja foi visitada
                        if(posicoesVisitadas.contains(posicaoAtual)){
                            fitness += 50;
                        }else{
                            fitness += 10;
                            // se a posicao atual for um objetivo
                            if(Labirinto.labirinto[posicaoAtual[0]][posicaoAtual[1]+1] == 'C'){
                                objetivosEncontrados++;
                                fitness -= 20;
                            }
                            // adiciona visitados
                            posicoesVisitadas.add(posicaoAtual);
                        }
                    }else{
                        break;
                    }
                }
            }

            // Move para cima
            if(gene == 8){
                // Se for movimento inválido, quebra
                if(posicaoAtual[0]-1 < 0 || posicaoAtual[1] < 0 || posicaoAtual[0]-1 > Labirinto.linhas-1 || posicaoAtual[1] > Labirinto.colunas-1){
                    break;
                }else{
                    if(Labirinto.labirinto[posicaoAtual[0]-1][posicaoAtual[1]] == '0' || Labirinto.labirinto[posicaoAtual[0]-1][posicaoAtual[1]] == 'C'){

                        // atualiza posição atual
                        posicaoAtual = new int[]{posicaoAtual[0]-1, posicaoAtual[1]};
                        
                        // se posicao atual ja foi visitada
                        if(posicoesVisitadas.contains(posicaoAtual)){
                            fitness += 50;
                        }else{
                            fitness += 10;
                            // se a posicao atual for um objetivo
                            if(Labirinto.labirinto[posicaoAtual[0]-1][posicaoAtual[1]] == 'C'){
                                objetivosEncontrados++;
                                fitness -= 20;
                            }
                            // adiciona visitados
                            posicoesVisitadas.add(posicaoAtual);
                        }
                    }else{
                        break;
                    }
                }
            }

            // Move para baixo
            if(gene == 2){
                // Se for movimento inválido, quebra
                if(posicaoAtual[0]+1 < 0 || posicaoAtual[1] < 0 || posicaoAtual[0]+1 > Labirinto.linhas-1 || posicaoAtual[1] > Labirinto.colunas-1){
                    break;
                }else{
                    if(Labirinto.labirinto[posicaoAtual[0]+1][posicaoAtual[1]] == '0' || Labirinto.labirinto[posicaoAtual[0]+1][posicaoAtual[1]] == 'C'){

                        // atualiza posição atual
                        posicaoAtual = new int[]{posicaoAtual[0]+1, posicaoAtual[1]};
                        
                        // se posicao atual ja foi visitada
                        if(posicoesVisitadas.contains(posicaoAtual)){
                            fitness += 50;
                        }else{
                            fitness += 10;
                            // se a posicao atual for um objetivo
                            if(Labirinto.labirinto[posicaoAtual[0]+1][posicaoAtual[1]] == 'C'){
                                objetivosEncontrados++;
                                fitness -= 20;
                            }
                            // adiciona visitados
                            posicoesVisitadas.add(posicaoAtual);
                        }
                    }else{
                        break;
                    }
                }
            }

            // Move para diagonal superior esquerda
            if(gene == 7){
                // Se for movimento inválido, quebra
                if(posicaoAtual[0]-1 < 0 || posicaoAtual[1]-1 < 0 || posicaoAtual[0]-1 > Labirinto.linhas-1 || posicaoAtual[1]-1 > Labirinto.colunas-1){
                    break;
                }else{
                    if(Labirinto.labirinto[posicaoAtual[0]-1][posicaoAtual[1]-1] == '0' || Labirinto.labirinto[posicaoAtual[0]-1][posicaoAtual[1]-1] == 'C'){

                        // atualiza posição atual
                        posicaoAtual = new int[]{posicaoAtual[0]-1, posicaoAtual[1]-1};
                        
                        // se posicao atual ja foi visitada
                        if(posicoesVisitadas.contains(posicaoAtual)){
                            fitness += 50;
                        }else{
                            fitness += 10;
                            // se a posicao atual for um objetivo
                            if(Labirinto.labirinto[posicaoAtual[0]-1][posicaoAtual[1]-1] == 'C'){
                                objetivosEncontrados++;
                                fitness -= 20;
                            }
                            // adiciona visitados
                            posicoesVisitadas.add(posicaoAtual);
                        }
                    }else{
                        break;
                    }
                }
            }

            // Move para diagonal superior direita
            if(gene == 9){
                // Se for movimento inválido, quebra
                if(posicaoAtual[0]-1 < 0 || posicaoAtual[1]+1 < 0 || posicaoAtual[0]-1 > Labirinto.linhas-1 || posicaoAtual[1]+1 > Labirinto.colunas-1){
                    break;
                }else{
                    if(Labirinto.labirinto[posicaoAtual[0]-1][posicaoAtual[1]+1] == '0' || Labirinto.labirinto[posicaoAtual[0]-1][posicaoAtual[1]+1] == 'C'){

                        // atualiza posição atual
                        posicaoAtual = new int[]{posicaoAtual[0]-1, posicaoAtual[1]+1};
                        
                        // se posicao atual ja foi visitada
                        if(posicoesVisitadas.contains(posicaoAtual)){
                            fitness += 50;
                        }else{
                            fitness += 10;
                            // se a posicao atual for um objetivo
                            if(Labirinto.labirinto[posicaoAtual[0]-1][posicaoAtual[1]+1] == 'C'){
                                objetivosEncontrados++;
                                fitness -= 20;
                            }
                            // adiciona visitados
                            posicoesVisitadas.add(posicaoAtual);
                        }
                    }else{
                        break;
                    }
                }
            }

            // Move para diagonal inferior esquerda
            if(gene == 1){
                // Se for movimento inválido, quebra
                if(posicaoAtual[0]+1 < 0 || posicaoAtual[1]-1 < 0 || posicaoAtual[0]+1 > Labirinto.linhas-1 || posicaoAtual[1]-1 > Labirinto.colunas-1){
                    break;
                }else{
                    if(Labirinto.labirinto[posicaoAtual[0]+1][posicaoAtual[1]-1] == '0' || Labirinto.labirinto[posicaoAtual[0]+1][posicaoAtual[1]-1] == 'C'){

                        // atualiza posição atual
                        posicaoAtual = new int[]{posicaoAtual[0]+1, posicaoAtual[1]-1};
                        
                        // se posicao atual ja foi visitada
                        if(posicoesVisitadas.contains(posicaoAtual)){
                            fitness += 50;
                        }else{
                            fitness += 10;
                            // se a posicao atual for um objetivo
                            if(Labirinto.labirinto[posicaoAtual[0]+1][posicaoAtual[1]-1] == 'C'){
                                objetivosEncontrados++;
                                fitness -= 20;
                            }
                            // adiciona visitados
                            posicoesVisitadas.add(posicaoAtual);
                        }
                    }else{
                        break;
                    }
                }
            }

            // Move para diagonal inferior direita
            if(gene == 3){
                // Se for movimento inválido, quebra
                if(posicaoAtual[0]+1 < 0 || posicaoAtual[1]+1 < 0 || posicaoAtual[0]+1 > Labirinto.linhas-1 || posicaoAtual[1]+1 > Labirinto.colunas-1){
                    break;
                }else{
                    if(Labirinto.labirinto[posicaoAtual[0]+1][posicaoAtual[1]+1] == '0' || Labirinto.labirinto[posicaoAtual[0]+1][posicaoAtual[1]+1] == 'C'){

                        // atualiza posição atual
                        posicaoAtual = new int[]{posicaoAtual[0]+1, posicaoAtual[1]+1};
                        
                        // se posicao atual ja foi visitada
                        if(posicoesVisitadas.contains(posicaoAtual)){
                            fitness += 50;
                        }else{
                            fitness += 10;
                            // se a posicao atual for um objetivo
                            if(Labirinto.labirinto[posicaoAtual[0]+1][posicaoAtual[1]+1] == 'C'){
                                objetivosEncontrados++;
                                fitness -= 20;
                            }
                            // adiciona visitados
                            posicoesVisitadas.add(posicaoAtual);
                        }
                    }else{
                        break;
                    }
                }
            }
        }

        this.aptidao = fitness;
    }

    

}
