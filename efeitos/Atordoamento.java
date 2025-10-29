package efeitos;

import personagem.Personagem;

public class Atordoamento extends EfeitoStatusBase {

    public Atordoamento() {
        this(1); // Duração de 1 turno
    }

    public Atordoamento(int duracao) {
        super("Atordoamento", duracao);
    }
    
    @Override
    public void aplicarEfeito(Personagem alvo) {
        System.out.println(alvo.getNome() + " está Atordoado e perde o próximo turno!");
        alvo.setAtordoado(true);
    }

    @Override
    public void processarTurno(Personagem alvo) {
        super.processarTurno(alvo);
        if (isFinalizado()) {
            alvo.setAtordoado(false);
            System.out.println(alvo.getNome() + " não está mais atordoado.");
        }
    }
}