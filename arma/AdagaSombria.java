package arma;

import personagem.Personagem;
import batalha.AtaqueResultado;
import java.util.Collections;

public class AdagaSombria implements IArma {
    private static final int DESTREZA_MINIMA = 12;
    private static final int MULTIPLICADOR_DANO_FURTIVO = 3;
    
    public String getNome() { 
        return "Adaga Sombria"; 
    }

    public int getDanoBase() { 
        return 10; 
    }

    public int getCustoMana() { 
        return 10; 
    }

    public boolean podeEquipar(Personagem personagem) { 
        return personagem.getDestreza() >= DESTREZA_MINIMA; 
    }

    public AtaqueResultado atacar(Personagem atacante, Personagem alvo) {
        int danoFinal = getDanoBase();
        String msg;

        if (alvo.isDesprevenido()) {
            danoFinal *= MULTIPLICADOR_DANO_FURTIVO;
            msg = atacante.getNome() + " usa Ataque Furtivo nas costas de " + alvo.getNome() + " causando dano triplo!";
        } else {
            msg = atacante.getNome() + " ataca " + alvo.getNome() + " com a adaga.";
        }
        
        return new AtaqueResultado(danoFinal, Collections.emptyList(), msg);
    }
}