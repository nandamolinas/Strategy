package efeitos;

import personagem.Personagem;

public interface EfeitoStatus {
    String getNome();
    int getDuracao();
    void setDuracao(int duracao);
    void aplicarEfeito(Personagem alvo); // Efeito inicial (ex: "Está atordoado!")
    void processarTurno(Personagem alvo); // Efeito por turno (ex: dano de sangramento)
    boolean isFinalizado();
}