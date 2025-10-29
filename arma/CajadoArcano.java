package arma;

import personagem.Personagem;
import batalha.AtaqueResultado;
import efeitos.EfeitoStatus;
import efeitos.Queimadura;
import java.util.List;
import java.util.ArrayList;

public class CajadoArcano implements IArma {
    private static final int INTELIGENCIA_MINIMA = 12;
    
    public String getNome() { 
        return "Cajado Arcano"; 
    }

    public int getDanoBase() { 
        return 8; 
    }

    public int getCustoMana() { 
        return 25; 
    }

    public boolean podeEquipar(Personagem personagem) { 
        return personagem.getInteligencia() >= INTELIGENCIA_MINIMA; 
    }

    public AtaqueResultado atacar(Personagem atacante, Personagem alvo) {
        List<EfeitoStatus> efeitos = new ArrayList<>();
        efeitos.add(new Queimadura());
        String msg = atacante.getNome() + " lança uma Bola de Fogo em " + alvo.getNome() + "!";
        
        return new AtaqueResultado(getDanoBase(), efeitos, msg);
    }
}