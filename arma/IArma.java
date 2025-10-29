package arma;

import personagem.Personagem;
import batalha.AtaqueResultado;

public interface IArma {
    String getNome();
    int getDanoBase();
    int getCustoMana();
    boolean podeEquipar(Personagem personagem);
    AtaqueResultado atacar(Personagem atacante, Personagem alvo);
}