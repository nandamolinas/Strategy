package arma;

import personagem.Personagem;
import batalha.AtaqueResultado;
import java.util.Collections;

public class ArcoElfico implements IArma {
    
    public String getNome() { return "Arco Élfico"; }
    public int getDanoBase() { return 12; }
    public int getCustoMana() { return 15; }

    public boolean podeEquipar(Personagem personagem) { 
        return personagem.getDestreza() >= 8; 
    }

    public AtaqueResultado atacar(Personagem atacante, Personagem alvo) {
        String msg = atacante.getNome() + " usa Chuva de Flechas!";
        return new AtaqueResultado(getDanoBase(), Collections.emptyList(), msg, true);
    }
}