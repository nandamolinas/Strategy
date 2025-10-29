package batalha;

import efeitos.EfeitoStatus; // <--- ADICIONE ESTA LINHA
import java.util.List;
import java.util.Scanner;
import personagem.Personagem;
import arma.IArma;

public class Batalha {
    private Personagem jogador;
    private List<Personagem> inimigos;
    private Scanner scanner;
    private int turno = 1;

    public Batalha(Personagem jogador, List<Personagem> inimigos) {
        this.jogador = jogador;
        this.inimigos = inimigos;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarBatalha() {
        System.out.println("A BATALHA COMEÇA!");

        while (jogador.isVivo() && !todosInimigosMortos()) {
            System.out.println("\n--- TURNO " + turno + " ---");
            
            // Turno do Jogador
            executarTurnoJogador();
            if (!jogador.isVivo() || todosInimigosMortos()) break;

            // Turno dos Inimigos
            executarTurnoInimigos();
            if (!jogador.isVivo()) break;
            
            turno++;
        }

        // Fim da batalha
        if (jogador.isVivo()) {
            System.out.println("\n--- VITÓRIA ---");
            System.out.println(jogador.getNome() + " venceu a batalha!");
        } else {
            System.out.println("\n--- DERROTA ---");
            System.out.println(jogador.getNome() + " foi derrotado.");
        }
    }

    private void executarTurnoJogador() {
        System.out.println("É o turno de " + jogador.getNome() + ".");
        jogador.processarInicioTurno();
        mostrarStatusBatalha();

        if (jogador.isAtordoado()) {
            System.out.println(jogador.getNome() + " está atordoado e não pode agir!");
            return;
        }

        System.out.println("Escolha sua ação:");
        System.out.println("1. Atacar");
        System.out.println("2. Trocar de Arma");
        
        int escolha = scanner.nextInt();
        
        if(escolha == 1) {
            // Escolher alvo
            Personagem alvo = escolherAlvo();
            if(alvo == null) return; // Não há alvos

            // Atacar
            AtaqueResultado resultado = jogador.atacar(alvo);
            System.out.println(resultado.getMensagem());

            // Aplicar dano e efeitos
            if (resultado.isAtaqueEmArea()) {
                System.out.println("O ataque atinge TODOS os inimigos!");
                for (Personagem inimigo : inimigos) {
                    if(inimigo.isVivo()) {
                        processarAtaque(inimigo, resultado);
                    }
                }
            } else {
                processarAtaque(alvo, resultado);
            }
            
        } else if (escolha == 2) {
            // Trocar Arma
            System.out.println("Escolha a arma para equipar:");
            List<IArma> inventario = jogador.getInventarioArmas();
            for(int i=0; i < inventario.size(); i++) {
                System.out.println((i+1) + ". " + inventario.get(i).getNome());
            }
            int indiceArma = scanner.nextInt() - 1;
            jogador.trocarArma(indiceArma);
        }
    }

    private void executarTurnoInimigos() {
        for (Personagem inimigo : inimigos) {
            if (inimigo.isVivo() && jogador.isVivo()) {
                System.out.println("É o turno de " + inimigo.getNome() + ".");
                inimigo.processarInicioTurno();

                if (inimigo.isAtordoado()) {
                    System.out.println(inimigo.getNome() + " está atordoado e não pode agir!");
                    continue;
                }

                // IA Simples: apenas ataca o jogador
                AtaqueResultado resultado = inimigo.atacar(jogador);
                System.out.println(resultado.getMensagem());
                processarAtaque(jogador, resultado);
            }
        }
    }
    
    private void processarAtaque(Personagem alvo, AtaqueResultado resultado) {
        alvo.receberDano(resultado.getDano(), true); // true = passivas podem ativar
        for (EfeitoStatus efeito : resultado.getEfeitos()) {
            alvo.aplicarEfeito(efeito);
        }
    }

    private Personagem escolherAlvo() {
        // Remove mortos da lista de alvos
        inimigos.removeIf(p -> !p.isVivo());
        if(inimigos.isEmpty()) return null;

        System.out.println("Escolha o alvo:");
        for (int i = 0; i < inimigos.size(); i++) {
            System.out.println((i + 1) + ". " + inimigos.get(i).getStatus());
        }

        int escolhaAlvo = scanner.nextInt() - 1;
        if (escolhaAlvo >= 0 && escolhaAlvo < inimigos.size()) {
            return inimigos.get(escolhaAlvo);
        }
        return inimigos.get(0); // Pega o primeiro se a escolha for inválida
    }

    private boolean todosInimigosMortos() {
        for (Personagem inimigo : inimigos) {
            if (inimigo.isVivo()) return false;
        }
        return true;
    }
    
    private void mostrarStatusBatalha() {
        System.out.println("Jogador: " + jogador.getStatus());
        System.out.println("Inimigos:");
        for(Personagem p : inimigos) {
            if(p.isVivo()) System.out.println("- " + p.getStatus());
        }
    }
}