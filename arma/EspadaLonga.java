package arma;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import batalha.AtaqueResultado;
import personagem.Personagem;
import efeitos.EfeitoStatus;
import efeitos.Sangramento;

public class EspadaLonga implements IArma {
    private static final int CHANCE_SANGRAMENTO = 30;
    private static final int FORCA_MINIMA = 10;
    private final Random rng = new Random();

    public String getNome() { 
        return "Espada Longa"; 
    }

    public int getDanoBase() { 
        return 15; 
    }

    public int getCustoMana() { 
        return 0; 
    }
    
    public boolean podeEquipar(Personagem personagem) { 
        return personagem.getForca() >= FORCA_MINIMA; 
    }

    public AtaqueResultado atacar(Personagem atacante, Personagem alvo) {
        List<EfeitoStatus> efeitos = new ArrayList<>();
        String msg;

        if (rng.nextInt(100) < CHANCE_SANGRAMENTO) {
            efeitos.add(new Sangramento());
            msg = atacante.getNome() + " usa Corte Profundo em " + alvo.getNome() + "! Causando Sangramento!";
        } else {
            msg = atacante.getNome() + " ataca " + alvo.getNome() + " com um corte simples.";
        }
        
        return new AtaqueResultado(getDanoBase(), efeitos, msg);
    }
}