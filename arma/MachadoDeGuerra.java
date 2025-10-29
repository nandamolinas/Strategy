package arma;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import personagem.Personagem;
import batalha.AtaqueResultado;
import efeitos.EfeitoStatus;
import efeitos.Atordoamento;

public class MachadoDeGuerra implements IArma {
    private static final int CHANCE_ATORDOAR = 25;
    private static final int FORCA_MINIMA = 15;
    private final Random rng = new Random();

    public String getNome() { 
        return "Machado de Guerra"; 
    }

    public int getDanoBase() { 
        return 18; 
    }

    public int getCustoMana() { 
        return 5; 
    }

    public boolean podeEquipar(Personagem personagem) { 
        return personagem.getForca() >= FORCA_MINIMA; 
    }

    public AtaqueResultado atacar(Personagem atacante, Personagem alvo) {
        List<EfeitoStatus> efeitos = new ArrayList<>();
        String msg;

        if (rng.nextInt(100) < CHANCE_ATORDOAR) {
            efeitos.add(new Atordoamento());
            msg = atacante.getNome() + " usa Golpe Esmagador em " + alvo.getNome() + "! Alvo Atordoado!";
        } else {
            msg = atacante.getNome() + " ataca " + alvo.getNome() + " com um golpe pesado.";
        }
        
        return new AtaqueResultado(getDanoBase(), efeitos, msg);
    }
}