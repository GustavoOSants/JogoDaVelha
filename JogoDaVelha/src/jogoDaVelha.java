import java.util.Scanner;
public class jogoDaVelha {
    public static void main(String[] args) {
        imprimeMenuPrincipal();
    }

    public static void imprimeMenuPrincipal() {
        Scanner input = new Scanner(System.in);
        int opcao;
        boolean certo = false;

        System.out.println("""
                                           
                >>     Centro Universitario Senac - Santo Amaro.    <<
                >>                                                  <<
                >> Projeto Integrador - Prof. Dr. Fernando Almeida. <<
                >> Projeto Semestral - 2024.                        <<
                >>                                                  <<
                >>                                                  <<
                >>     .:::::::: Desenvolvedores ::::::::.          <<
                >>                                                  <<
                >>               1 Maria Beatriz Monteiro Braga     <<
                >>               2 Gustavo Oliveira dos Santos      <<
                >>               3 Julia Souza Soares               <<
                >>               4 Rodrigo Pereira Gama             <<
                >>                                                  <<
                >>                                                  <<
                >>                   27/05/2024                     <<
                """);
        System.out.println(""
                + "\n\n\n ======================================================\n\n\n");


        System.out.println("|^_^| BEM VENDO AO JOGO DA VELHA |^_^|");
        System.out.println("Escolha uma das opcoes: ");

        do {
            System.out.println("\n[1] – Instruções"
                    + "\n[2] – Jogar"
                    + "\n[3] – Sair");

            opcao = input.nextInt();

            switch (opcao) {

                default:
                    System.out.println("-__- Opcao Invalida! -__-");
                    System.out.println("==^_^== Escolha outra Opcao. ==^_^==");
                    return;

                case 1:
                    System.out.println("Instrucoes do Game");
                    //Colocar as instruções aqui
                    break;
                case 2:
                    System.out.println("");
                    jogo();
                    certo = true;
                    break;
                case 3:
                    System.out.println("Voce Saiu do Jogo! ATE A PROXIMA!!!");
                    certo = true;
                    break;
            }
        } while (!certo);
    }

    public static void jogo() {
        Scanner estilo = new Scanner(System.in);
        int opcao;
        boolean certo = false;
        do {
            System.out.println("Escolha a opcao desejada: "
                    + "\n [1] - Jogar Sozinho"
                    + "\n [2] - Multijogador"
                    + "\n [3] - Voltar");
            opcao = estilo.nextInt();
            switch (opcao) {
                default:
                    System.out.println("-__- Opcao Invalida! -__-");
                    System.out.println("==^_^== Escolha outra Opcao. ==^_^==");
                case 1:
                    System.out.println("Nivel");
                    nivel();
                    certo = true;
                    break;
                case 2:
                    System.out.println("Iniciando partida!");
                    modoJogador();
                    certo = true;
                    break;
                case 3:
                    System.out.println("Menu Principal");
                    imprimeMenuPrincipal();
            }
        } while (!certo);
    }

    public static void nivel() {
        Scanner input = new Scanner(System.in);
        int opcao = 0;
        boolean certo = false;
        do{
        System.out.println("Informe a dificuldade: "
                + "\n [1] - Normal"
                + "\n [2] - Dificil"
                + "\n [3] - Voltar");
        opcao = input.nextInt();
        switch (opcao) {
            default:
                System.out.println("-__- Opcao Invalida! -__-\n\n");
                System.out.println("==^_^== Escolha outra Opcao. ==^_^==\n\n");
                certo=false;
            case 1:
                System.out.println("BOM JOGO");
                modoFacil();
                certo = true;
                break;
            case 2:
                System.out.println("Iniciando partida!");
                modoDificil();
                certo = true;
                break;
            case 3:
                System.out.println("Menu Principal");
                imprimeMenuPrincipal();
                certo = true;
                break;
        }
    }while (!certo) ;
    }


    public static void modoJogador() {
        char[][] tabuleiro = inicializarTabuleiro();
        boolean velha;
        int vencedor = 0, velhas = 0, pontuacaoJogador = 0, pontuacaoMaquina = 0, rodadas = 0;
        do {
            do {
                if(rodadas%2!=0){
                    System.out.println("Vez do Jogador 2!");
                    tabuleiro = jogadaJogador2(tabuleiro);
                    rodadas=0;
                }
                imprimirTabuleiro(tabuleiro);
                //verificando a vez do jogador
                System.out.println("Vez do Jogador 1!");
                tabuleiro = jogadaUsuario(tabuleiro);
                imprimirTabuleiro(tabuleiro);
                //atualizando tabuleiro
                System.out.println("Vez do Jogador 2!");
                tabuleiro = jogadaJogador2(tabuleiro);
                vencedor = verificaVencedor(tabuleiro);
                velha = verificaVelha(tabuleiro);
            } while (vencedor == 0 && !velha);
            if (vencedor == 1) {
                System.out.println("Parabéns o Jogador 1 Ganhou!");
                pontuacaoJogador++;
                imprimirPontuacao(pontuacaoJogador, pontuacaoMaquina, velhas);
                tabuleiro = zerarTabuleiro(tabuleiro);
                rodadas++;
            } else if (vencedor == -1) {
                System.out.println("Parabéns o Jogador 1 Ganhou!");
                pontuacaoMaquina++;
                imprimirPontuacao(pontuacaoJogador, pontuacaoMaquina, velhas);
                tabuleiro = zerarTabuleiro(tabuleiro);
                rodadas++;
            } else {
                System.out.println("O jogo acabou em velha!");
                velhas++;
                imprimirPontuacao(pontuacaoJogador, pontuacaoMaquina, velhas);
                tabuleiro = zerarTabuleiro(tabuleiro);
                rodadas++;
            }
        } while (pontuacaoJogador < 3 && pontuacaoMaquina < 3 && velhas < 3);
        if (velhas == 3) {
            System.out.println("O jogo acabou em velha");
        } else if (pontuacaoJogador == 3) {
            System.out.println("O Jogador 1 venceu!");
        } else {
            System.out.println("A Jogador 2 Ganhou!");
        }
    }

    public static void modoFacil() {
        char[][] tabuleiro = inicializarTabuleiro();
        boolean velha;

        int vencedor = 0, velhas = 0, pontuacaoJogador = 0, pontuacaoMaquina = 0, rodadas = 0;
        do {
            do {
                if(rodadas%2!=0){
                    tabuleiro = jogadaMaquinaFacil(tabuleiro);
                    rodadas=0;
                }
                imprimirTabuleiro(tabuleiro);
                //verificando a vez do jogador
                System.out.println("Sua vez!");
                tabuleiro = jogadaUsuario(tabuleiro);
                //atualizando tabuleiro
                System.out.println("Vez do computador!");
                tabuleiro = jogadaMaquinaFacil(tabuleiro);
                vencedor = verificaVencedor(tabuleiro);
                velha = verificaVelha(tabuleiro);
            } while (vencedor == 0 && !velha);
            if (vencedor == 1) {
                System.out.println("Parabéns você ganhou!");
                pontuacaoJogador++;
                imprimirPontuacao(pontuacaoJogador, pontuacaoMaquina, velhas);
                tabuleiro = zerarTabuleiro(tabuleiro);
                rodadas++;
            } else if (vencedor == -1) {
                System.out.println("A maquina Ganhou!");
                pontuacaoMaquina++;
                imprimirPontuacao(pontuacaoJogador, pontuacaoMaquina, velhas);
                tabuleiro = zerarTabuleiro(tabuleiro);
                rodadas++;
            } else {
                System.out.println("O jogo acabou em velha!");
                velhas++;
                imprimirPontuacao(pontuacaoJogador, pontuacaoMaquina, velhas);
                tabuleiro = zerarTabuleiro(tabuleiro);
                rodadas++;
            }
        } while (pontuacaoJogador < 3 && pontuacaoMaquina < 3 && velhas < 3);
        if (velhas == 3) {
            System.out.println("O jogo acabou em velha");
        } else if (pontuacaoJogador == 3) {
            System.out.println("Você é venceu!");
        } else {
            System.out.println("A Maquina Ganhou!");
        }
    }
    public static void modoDificil() {
        char[][] tabuleiro = inicializarTabuleiro();
        boolean velha;
        int vencedor, pontuacaoMaquina = 0, pontuacaoJogador = 0, velhas = 0, rodadas=0;
        imprimirTabuleiro(tabuleiro);
        do {
            do {
                if(rodadas%2!=0){
                    tabuleiro = jogadaMaquinaDificil(tabuleiro);
                    rodadas=0;
                }
                tabuleiro = jogadaUsuario(tabuleiro);
                imprimirTabuleiro(tabuleiro);
                tabuleiro = jogadaMaquinaDificil(tabuleiro);
                imprimirTabuleiro(tabuleiro);
                vencedor = verificaVencedor(tabuleiro);
                velha = verificaVelha(tabuleiro);
            } while (vencedor == 0 && !velha);
            if (vencedor == 1) {
                System.out.println("Parabéns você ganhou!");
                pontuacaoJogador++;
                imprimirPontuacao(pontuacaoJogador, pontuacaoMaquina, velhas);
                tabuleiro = zerarTabuleiro(tabuleiro);
                rodadas++;
            } else if (vencedor == -1) {
                System.out.println("A maquina Ganhou!");
                pontuacaoMaquina++;
                imprimirPontuacao(pontuacaoJogador, pontuacaoMaquina, velhas);
                tabuleiro = zerarTabuleiro(tabuleiro);
                rodadas++;
            } else {
                System.out.println("O jogo acabou em velha!");
                velhas++;
                imprimirPontuacao(pontuacaoJogador, pontuacaoMaquina, velhas);
                tabuleiro = zerarTabuleiro(tabuleiro);
                rodadas++;
            }
        }while(pontuacaoJogador<3 && pontuacaoMaquina<3 && velhas<3);
        if (velhas == 3) {
            System.out.println("O jogo acabou em velha");
        } else if (pontuacaoJogador == 3) {
            System.out.println("Você é venceu!");
        } else{
            System.out.println("A Maquina Ganhou!");
        }
    }

    public static boolean posicaoValida(int linha, int coluna, char[][] tabuleiro) {
        boolean ok;
        if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
            System.out.println("POSIÇÃO INVALIDA!");
            ok = false;
        } else if (tabuleiro[linha][coluna] == ' ') {
            ok = true;
        } else {
            System.out.println("POSIÇÃO INVALIDA!");
            ok = false;
        }
        return ok;
    }

    public static char[][] inicializarTabuleiro() {
        char[][] tabuleiro = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        return tabuleiro;
    }

    public static void imprimirTabuleiro(char[][] tabuleiro) {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  _____");
            }
        }
    }

    public static int lerCoordenadaLinha() {
        Scanner input = new Scanner(System.in);
        System.out.println("DIgite a coordenada que deseja colocar em referente a linha: (0,1,2) ");
        int linha = input.nextInt();
        return linha;
    }

    public static int lerCoordenadaColuna() {
        Scanner input = new Scanner(System.in);
        System.out.println("DIgite a coordenada que deseja colocar em referente a coluna: (0,1,2) ");
        int coluna = input.nextInt();
        return coluna;
    }

    public static char[][] jogadaMaquinaFacil(char[][] tabuleiro) {
        int jogador = 15;
        int linha, coluna;
        do {
            linha = (int) (Math.random() * 3);
            coluna = (int) (Math.random() * 3);
        } while (!(tabuleiro[linha][coluna] == ' '));
        tabuleiro = jogar(tabuleiro, linha, coluna, jogador);
        return tabuleiro;
    }

    public static char[][] jogadaUsuario(char[][] tabuleiro) {
        int linha, coluna;
        int jogador = 10;
        do {
            linha = lerCoordenadaLinha();
            coluna = lerCoordenadaColuna();
            //continuar solicitando se nao for valida
        } while (!posicaoValida(linha, coluna, tabuleiro));
        jogar(tabuleiro, linha, coluna, jogador);
        return tabuleiro;
    }

    public static char[][] jogadaMaquinaDificil(char[][] tabuleiro) {
        //tabuleiro de centro
        int jogador = 15;
        int linha = 4, coluna = 4;
        if (tabuleiro[1][1] == ' ') {
            linha = 1;
            coluna = 1;
            //tabuleiros de vitoria
        } else if (tabuleiro[0][0] == 'O' && tabuleiro[0][2] == 'O' && tabuleiro[0][1] == ' ') {
            linha = 0;
            coluna = 1;
        } else if (tabuleiro[0][1] == 'O' && tabuleiro[0][2] == 'O' && tabuleiro[0][0] == ' ') {
            linha = 0;
            coluna = 0;
        } else if (tabuleiro[0][0] == 'O' && tabuleiro[0][1] == 'O' && tabuleiro[0][2] == ' ') {
            linha = 0;
            coluna = 2;
        } else if (tabuleiro[1][1] == 'O' && tabuleiro[1][2] == 'O' && tabuleiro[1][0] == ' ') {
            linha = 1;
            coluna = 0;
        } else if (tabuleiro[1][0] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[1][2] == ' ') {
            linha = 1;
            coluna = 2;
        } else if (tabuleiro[2][0] == 'O' && tabuleiro[2][1] == 'O' && tabuleiro[2][2] == ' ') {
            linha = 2;
            coluna = 2;
        } else if (tabuleiro[2][1] == 'O' && tabuleiro[2][2] == 'O' && tabuleiro[2][0] == ' ') {
            linha = 2;
            coluna = 0;
        } else if (tabuleiro[2][2] == 'O' && tabuleiro[2][0] == 'O' && tabuleiro[2][1] == ' ') {
            linha = 2;
            coluna = 1;
        } else if (tabuleiro[0][2] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][0] == ' ') {
            linha = 2;
            coluna = 0;
        } else if (tabuleiro[2][0] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[0][2] == ' ') {
            linha=0;
            coluna = 2;
        } else if (tabuleiro[1][1] == 'O' && tabuleiro[2][2] == 'O' && tabuleiro[0][0] == ' ') {
            linha = 0;
            coluna = 0;
        } else if (tabuleiro[0][0] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][2] == ' ') {
            linha = 2;
            coluna = 2;
        } else if (tabuleiro[1][0] == 'O' && tabuleiro[2][0] == 'O' && tabuleiro[0][0] == ' ') {
            coluna = 0;
            linha = 0;
        } else if (tabuleiro[0][0] == 'O' && tabuleiro[2][0] == 'O' && tabuleiro[1][0] == ' ') {
            linha = 1;
            coluna = 0;
        } else if (tabuleiro[0][0] == 'O' && tabuleiro[1][0] == 'O' && tabuleiro[2][0] == ' ') {
            linha = 2;
            coluna = 0;
        } else if (tabuleiro[1][1] == 'O' && tabuleiro[2][1] == 'O' && tabuleiro[0][1] == ' ') {
            linha = 0;
            coluna = 1;
        } else if (tabuleiro[0][1] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][1] == ' ') {
            linha = 2;
            coluna = 1;
        } else if (tabuleiro[1][2] == 'O' && tabuleiro[2][2] == 'O' && tabuleiro[0][2] == ' ') {
            linha=0;
            coluna = 2;
        } else if (tabuleiro[0][2] == 'O' && tabuleiro[2][2] == 'O' && tabuleiro[1][2] == ' ') {
            linha=1;
            coluna = 2;
        } else if (tabuleiro[0][2] == 'O' && tabuleiro[1][2] == 'O' && tabuleiro[2][2] == ' ') {
            linha=2;
            coluna=2;
        }
        // tabuleiros de Bloqueio
        else if (tabuleiro[0][0] == 'X' && tabuleiro[0][2] == 'X' && tabuleiro[0][1] == ' ') {
            coluna = 1;
            linha = 0;
        } else if (tabuleiro[0][1] == 'X' && tabuleiro[0][2] == 'X' && tabuleiro[0][0] == ' ') {
            linha = 0;
            coluna = 0;
        } else if (tabuleiro[0][0] == 'X' && tabuleiro[0][1] == 'X' && tabuleiro[0][2] == ' ') {
            linha = 0;
            coluna = 2;
        } else if (tabuleiro[1][1] == 'X' && tabuleiro[1][2] == 'X' && tabuleiro[1][0] == ' ') {
            linha = 1;
            coluna = 0;
        } else if (tabuleiro[1][0] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[1][2] == ' ') {
            linha = 1;
            coluna = 2;
        } else if (tabuleiro[2][0] == 'X' && tabuleiro[2][1] == 'X' && tabuleiro[2][2] == ' ') {
            linha = 2;
            coluna = 2;
        } else if (tabuleiro[2][1] == 'X' && tabuleiro[2][2] == 'X' && tabuleiro[2][0] == ' ') {
            linha = 2;
            coluna = 0;
        } else if (tabuleiro[2][2] == 'X' && tabuleiro[2][0] == 'X' && tabuleiro[2][1] == ' ') {;
            linha = 2;
            coluna = 1;
        } else if (tabuleiro[0][2] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][0] == ' ') {
            linha = 2;
            coluna = 0;
        } else if (tabuleiro[2][0] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[0][2] == ' ') {
            linha = 0;
            coluna = 2;
        } else if (tabuleiro[1][1] == 'X' && tabuleiro[2][2] == 'X' && tabuleiro[0][0] == ' ') {
            linha = 0;
            coluna = 0;
        } else if (tabuleiro[0][0] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][2] == ' ') {
            linha = 2;
            coluna = 2;
        } else if (tabuleiro[1][0] == 'X' && tabuleiro[2][0] == 'X' && tabuleiro[0][0] == ' ') {
            linha = 0;
            coluna = 0;
        } else if (tabuleiro[0][0] == 'X' && tabuleiro[2][0] == 'X' && tabuleiro[1][0] == ' ') {
            linha = 1;
            coluna = 0;
        } else if (tabuleiro[0][0] == 'X' && tabuleiro[1][0] == 'X' && tabuleiro[2][0] == ' ') {
            linha = 2;
            coluna = 0;
        } else if (tabuleiro[1][1] == 'X' && tabuleiro[2][1] == 'X' && tabuleiro[0][1] == ' ') {
            linha = 0;
            coluna = 1;
        } else if (tabuleiro[0][1] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][1] == ' ') {
            linha = 2;
            coluna = 1;
        } else if (tabuleiro[1][2] == 'X' && tabuleiro[2][2] == 'X' && tabuleiro[0][2] == ' ') {
            linha = 0;
            coluna = 2;
        } else if (tabuleiro[0][2] == 'X' && tabuleiro[2][2] == 'X' && tabuleiro[1][2] == ' ') {
            linha = 1;
            coluna = 2;
        } else if (tabuleiro[0][2] == 'X' && tabuleiro[1][2] == 'X' && tabuleiro[2][2] == ' ') {
            linha = 2;
            coluna = 2;
        }
        //tabuleiros de canto
        else if (tabuleiro[0][0] == ' ') {
            linha = 0;
            coluna = 0;
        } else if (tabuleiro[0][2] == ' ') {
            linha = 0;
            coluna = 2;
        } else if (tabuleiro[2][0] == ' ') {
            linha = 2;
            coluna = 0;
        } else if (tabuleiro[2][2] == ' ') {
            linha = 2;
            coluna = 2;
        } //tabuleiros de borda
        else if (tabuleiro[2][1] == ' ') {
            linha = 2;
            coluna = 1;
        } else if (tabuleiro[0][1] == ' ') {
            linha = 0;
            coluna = 1;
        } else if (tabuleiro[1][0] == ' ') {
            linha = 1;
            coluna = 0;
        } else if (tabuleiro[1][2] == ' ') {
            linha = 1;
            coluna = 2;
        }
        tabuleiro = jogar(tabuleiro, linha, coluna, jogador);
        return tabuleiro;
    }

    public static int verificaVencedor(char[][] tabuleiro) {
        int vencedor = 0;
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == 'X' && tabuleiro[i][1] == 'X' && tabuleiro[i][2] == 'X') {
                vencedor = 1;
            } else if (tabuleiro[i][0] == 'O' && tabuleiro[i][1] == 'O' && tabuleiro[i][2] == 'O') {
                vencedor = -1;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (tabuleiro[0][j] == 'X' && tabuleiro[1][j] == 'X' && tabuleiro[2][j] == 'X') {
                vencedor = 1;
            } else if (tabuleiro[0][j] == 'O' && tabuleiro[1][j] == 'O' && tabuleiro[2][j] == 'O') {
                vencedor = -1;
            }
        }
        if (tabuleiro[0][0] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][2] == 'X') {
            vencedor = 1;
        } else if (tabuleiro[0][0] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][2] == 'O') {
            vencedor = -1;
        }
        if (tabuleiro[0][2] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][0] == 'X') {
            vencedor = 1;
        } else if (tabuleiro[0][2] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][0] == 'O') {
            vencedor = -1;
        }
        return vencedor;
    }

    public static boolean verificaVelha(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }


    public static char[][] jogar(char[][] tabuleiro, int linha, int coluna, int jogador) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == linha && j == coluna && jogador==10) {
                        tabuleiro[i][j] = 'X';
                    } else if (i == linha && j == coluna && jogador == 15) {
                        tabuleiro[i][j] = 'O';
                    }
                }
            }

        return tabuleiro;
    }
    public static void imprimirPontuacao(int pontuacaoJogador1, int pontuacaoJogador2, int velhas) {
        System.out.println("Imprimindo Pontuação: ");

        System.out.println("Jogador 1 ganhou: " + pontuacaoJogador1 + " veze(s)");
        System.out.println("Jogador 2 ganhou: " + pontuacaoJogador2 + " veze(s)");
        System.out.println("Velhas: " + velhas);
    }
    public static char[][] zerarTabuleiro(char[][] tabuleiro){
        for(int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                tabuleiro[linha][coluna] = ' ';
            }
        }
        return tabuleiro;
    }
    public static char[][] jogadaJogador2(char[][] tabuleiro) {
        int linha, coluna;
        int jogador = 15;
        do {
            linha = lerCoordenadaLinha();
            coluna = lerCoordenadaColuna();
            //continuar solicitando se nao for valida
        } while (!posicaoValida(linha, coluna, tabuleiro));
        jogar(tabuleiro, linha, coluna, jogador);
        return tabuleiro;
    }
}

