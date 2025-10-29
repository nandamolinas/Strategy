import personagem.*;
import batalha.Batalha;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 1. Criar Personagens
        Personagem heroi = new Mago("Gandalf, o Mago");
        // Personagem heroi = new Guerreiro("Aragorn, o Guerreiro");
        // Personagem heroi = new Arqueiro("Legolas, o Arqueiro");

        // 2. Criar Inimigos
        Personagem orc1 = new Guerreiro("Orc Grunt");
        Personagem orc2 = new Guerreiro("Orc Chefe");
        
        List<Personagem> inimigos = new ArrayList<>();
        inimigos.add(orc1);
        inimigos.add(orc2);

        // 3. Iniciar Batalha
        Batalha batalha = new Batalha(heroi, inimigos);
        batalha.iniciarBatalha();
    }
}