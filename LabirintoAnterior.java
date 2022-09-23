public class LabirintoAnterior {
    public char[][] matrizLabirinto;
    public int[] posicaoInicial = {0,0};
    public int[] posicao_objetivo = new int[2];

    //Create the maze Matrix with rows and columns as inputs
    public LabirintoAnterior(int rows, int columns) {
        matrizLabirinto = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrizLabirinto[i][j] = '_';
            }
        }

        // Posiciona o objetivo ao final da matriz
        posicao_objetivo[0] = rows-1;
        posicao_objetivo[1] = columns-1;

        matrizLabirinto[posicaoInicial[0]][posicaoInicial[1]] = 'I';
        matrizLabirinto[posicao_objetivo[0]][posicao_objetivo[1]] = 'O';
    }

    // Create a NxN maze
    public LabirintoAnterior(int n){
        matrizLabirinto = new char[n][n];
        
        for (int i = 0; i < matrizLabirinto.length; i++) {
            for (int j = 0; j < matrizLabirinto[0].length; j++) {
                matrizLabirinto[i][j] = '_';
            }
        }

        // Posiciona o objetivo ao final da matriz
        posicao_objetivo[0] = matrizLabirinto.length-1;  
        posicao_objetivo[1] = matrizLabirinto[0].length-1;

        matrizLabirinto[posicaoInicial[0]][posicaoInicial[1]] = 'I';
        matrizLabirinto[posicao_objetivo[0]][posicao_objetivo[1]] = 'O';
    }

    // Create a maze with starting position and objetive position
    public LabirintoAnterior(int rows, int columns, int objetivoX, int objetivoY, int posicaoInicialX, int posicaoInicialY){
        matrizLabirinto = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrizLabirinto[i][j] = '_';
            }
        }

        // Posiciona o objetivo na matriz
        posicao_objetivo[0] = objetivoX;
        posicao_objetivo[1] = objetivoY;

        // Posiciona a posição inicial
        posicaoInicial[0] = posicaoInicialX;
        posicaoInicial[1] = posicaoInicialY;

        matrizLabirinto[posicaoInicial[0]][posicaoInicial[1]] = 'I';
        matrizLabirinto[posicao_objetivo[0]][posicao_objetivo[1]] = 'O';
    }

    /**
     * Adiciona barreiras no labirinto
     *      - Verifica se a posição é válida
     *      - Verifica se não é a posição inicial nem a posição objetivo
     */
    public void addBarreira(int [][] posicao){
        for (int i = 0; i < posicao.length; i++) {
            
            if(posicao[i][0] > matrizLabirinto.length || posicao[i][1] > matrizLabirinto[0].length){
                continue;
            }
            if(posicao[i][0] < 0 || posicao[i][1] < 0){
                continue;
            }

            
            if(posicao[i][0] == posicaoInicial[0] && posicao[i][1] == posicaoInicial[1]){
                continue;
            }
            
            if(posicao[i][0] == posicao_objetivo[0] && posicao[i][1] == posicao_objetivo[1]){
                continue;
            }

            matrizLabirinto[posicao[i][0]][posicao[i][1]] = '#';
        }
    }

    // Printa o labirinto
    public void printLabirinto(){
        for (int i = 0; i < matrizLabirinto.length; i++) {
            for (int j = 0; j < matrizLabirinto[0].length; j++) {
                System.out.print(matrizLabirinto[i][j] + " ");
            }
            System.out.println();
        }
    }
    

}
