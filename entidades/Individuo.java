import java.util.Random;
import java.util.ArrayList;

/** 
 * Inicialia o indivíduo
 *
 * Os movimentos que cada indivíduo realizou, serão represenados por "genes", conforme legenda abaixo:
 * 8 - CIMA
 * 4 - ESQUERDA
 * 6 - DIREITA
 * 2 - BAIXO
 * 3 - BAIXO + DIREITA
 * 1 - BAIXO + ESQUERDA
 * 7 - CIMA + ESQUERDA
 * 9 - CIMA + DIREITA
 */

public class Individuo {
    public Maze maze;
    public int posicaoX;
    public int posicaoY;
    public int objetivosObtidos = 0;
    public int qtdMovimentosRealizados = 0;
    public double aptidao = 0;
    public ArrayList<Integer> genes = new ArrayList<Integer>(); // Movimentos realizados
    public int[] movimentosPossiveis = {1, 2, 3, 4, 6, 7, 8, 9};

    //CONSTRUTOR CORRETO
    public Individuo(Maze maze) {
        this.maze = maze;
        this.posicaoX = maze.posicaoInicial[0];
        this.posicaoY = maze.posicaoInicial[1];

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int valor = movimentosPossiveis[random.nextInt(movimentosPossiveis.length)];

            if (valor == 5) {
                continue;
            }
            genes.add(valor);
        }
    }

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
        String geneString = "\nGenes -> [";
        for (int i = 0; i < 100; i++) {
            geneString +=  getGenes().get(i) + ", ";
        }

        geneString = geneString + "]";
        return geneString;
    }






    // ========================================= EXECUÇÃO ==============================================
     

    
    // Coloca o individuo para percorrer o labirinto com base nas direções que ele irá tomar
    public void executarIndividuo() {
        boolean rodar = true;

        while(rodar){

            // Para a execução caso atinja o ultimo objetivo
            if (objetivosObtidos == this.maze.getQtdObjetivos()) {
                rodar = false;
                this.aptidao -= 1000 + (this.qtdMovimentosRealizados * 10);
                return;
            }
            

            // Para se atingir limite máximo de movimentos
            if (qtdMovimentosRealizados == Populacao.maxMovimentos) {
                rodar = false;
                this.aptidao += this.qtdMovimentosRealizados * 10;
                return;
            }

            // Realiza movimento. Para o individuo se ele ir em uma direção invalida
            if (this.movimento() == -1){
                this.aptidao += 200 + (this.qtdMovimentosRealizados * 10);
                return;
            }
        }
    }

    /**
     * Realiza movimento, e verifica se direção é invalida
     */
    public int movimento(){
        int rcode = 0;

        // pega a próxima direção
        int direcaoTomada = proximoMovimento();
        switch (direcaoTomada){
            case 1: // BAIXO + ESQUERDA
                posicaoX = posicaoX -1;
                if (maze.isBlock(posicaoX,posicaoY)){
                    rcode = -1;
                    this.aptidao += 200;
                    break;
                }
                if (posicaoX < 0 || posicaoY < 0 || posicaoX > maze.getMaxX() || posicaoY > maze.getMaxY()){
                    rcode = -1;
                    this.aptidao += 500;
                    break;
                }

                // Marca a posição atual como visitada
                maze.visitaPosicao(posicaoX, posicaoY);
                break;
            case 2: // BAIXO
                posicaoY = posicaoY - 1;
                if (maze.isBlock(posicaoX,posicaoY)){
                    rcode = -1;
                    this.aptidao += 200;
                    break;
                }
                if (posicaoX < 0 || posicaoY < 0 || posicaoX > maze.getMaxX() || posicaoY > maze.getMaxY()){
                    rcode = -1;
                    this.aptidao += 500;
                    break;
                }
                // Marca a posição atual como visitada
                maze.visitaPosicao(posicaoX, posicaoY);
                break;
            case 3: // BAIXO + DIREITA
                posicaoY = posicaoY + 1;
                if (maze.isBlock(posicaoX,posicaoY)){
                    rcode = -1;
                    this.aptidao += 200;
                    break;
                }
                if (posicaoX < 0 || posicaoY < 0 || posicaoX > maze.getMaxX() || posicaoY > maze.getMaxY()){
                    rcode = -1;
                    this.aptidao += 500;
                    break;
                }
               // Marca a posição atual como visitada
               maze.visitaPosicao(posicaoX, posicaoY);
               break;
            case 4: // ESQUERDA
                posicaoX = posicaoX + 1;
                if (maze.isBlock(posicaoX,posicaoY)){
                    rcode = -1;
                    this.aptidao += 200;
                    break;
                }
                if (posicaoX < 0 || posicaoY < 0 || posicaoX > maze.getMaxX() || posicaoY > maze.getMaxY()){
                    rcode = -1;
                    this.aptidao += 500;
                    break;
                }
                // Marca a posição atual como visitada
                maze.visitaPosicao(posicaoX, posicaoY);
                break;
        }

        return rcode;
    }

    // Pega proximo movimento
    public int proximoMovimento() {
        int proximoMovimento = 0;
        if(this.qtdMovimentosRealizados < genes.size()){
            proximoMovimento = getGene(this.qtdMovimentosRealizados);
            this.qtdMovimentosRealizados++;
        }
        return proximoMovimento;
    }
}