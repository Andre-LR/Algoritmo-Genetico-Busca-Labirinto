import java.util.Random;

public class Jogo {
    public Populacao populacao;
    public Individuo melhorIndividuo;
    public int geracaoCount = 0;

    public Jogo(int popSize) {
        populacao = new Populacao(popSize);
        melhorIndividuo = populacao.getFittest();
    }

    public static void main(String[] args) {
        int popSize = 10;
        
        // Cria população inicial
        Jogo jogo = new Jogo(popSize);
        System.out.println("População inicial contendo " + popSize + " indivíduos");

        // Calcular aptidão de cada indivíduo da população inicial e mostrar a melhor aptidão
        

    }

    // Create Display matrix NxN where 1 is a block and 0 is clear path
    public static int[][] createDisplay(int n){
        int[][] display = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                display[i][j] = 0;
            }
        }

        // create random blocks
        Random random = new Random();
        int qtdBlocos = (int) (n*n*0.25);
        for (int i = 0; i < qtdBlocos; i++) {
            int x = random.nextInt(n);
            int y = random.nextInt(n);
            display[x][y] = 1;
        }

        return display;
    }

     
     /**
      * Calcula a aptidão - Função heurística dos cromossomos
      * Heurística: Melhor pontuação
      * Pontuação: 
      *     - Subtair 10 pontos para cada movimento
      *     - Subtrair 200 pontos para cada movimento que o indivíduo chega em uma posição bloqueada
      *     - Subtrair 500 pontos ao passar 3x no mesmo bloco
      *     - Somar 500 pontos ao pegar comida
      *     - Somar 1000 pontos ao pegar todas as comidas
      */
     public static int calcularAptidao(Individuo individuo, int[][] display) {

     }


}