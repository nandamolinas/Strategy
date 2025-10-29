package personagem;

import arma.ArcoElfico;
import arma.AdagaSombria;

public class Arqueiro extends Personagem {

    public Arqueiro(String nome) {
        // Atributos Base: Força 8, Destreza 15, Inteligência 7, Vida 90, Mana 80
        super(nome, 90, 80, 8, 15, 7);
        
        // Equipamento inicial
        adicionarArmaInventario(new ArcoElfico());
        adicionarArmaInventario(new AdagaSombria());
        equiparArma(inventarioArmas.get(0));
    }

    // Habilidade Passiva: "Esquiva" - 25% de chance de evitar um ataque
    @Override
    protected boolean tentarEsquivar() {
        return rng.nextInt(100) < 25; // 25% de chance
    }
}