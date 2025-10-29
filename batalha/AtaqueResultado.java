package batalha;

import java.util.List;
import java.util.Collections;
import efeitos.EfeitoStatus;

public class AtaqueResultado {
    private final int dano;
    private final List<EfeitoStatus> efeitos;
    private final String mensagem;
    private final boolean isAtaqueEmArea;

    // Construtor para ataque normal
    public AtaqueResultado(int dano, List<EfeitoStatus> efeitos, String mensagem) {
        this(dano, efeitos, mensagem, false);
    }

    // Construtor completo
    public AtaqueResultado(int dano, List<EfeitoStatus> efeitos, String mensagem, boolean isAtaqueEmArea) {
        this.dano = dano;
        this.efeitos = (efeitos != null) ? efeitos : Collections.emptyList();
        this.mensagem = mensagem;
        this.isAtaqueEmArea = isAtaqueEmArea;
    }

    public int getDano() { return dano; }
    public List<EfeitoStatus> getEfeitos() { return efeitos; }
    public String getMensagem() { return mensagem; }
    public boolean isAtaqueEmArea() { return isAtaqueEmArea; }
}