package efeitos;

import personagem.Personagem;

public class Queimadura extends EfeitoStatusBase {
    private final int danoPorTurno;

    public Queimadura() {
        this(2, 10); // Duração de 2 turnos, 10 de dano
    }

    public Queimadura(int duracao, int danoPorTurno) {
        super("Queimadura", duracao);
        this.danoPorTurno = danoPorTurno;
    }

    @Override
    public void processarTurno(Personagem alvo) {
        if (!isFinalizado()) {
            System.out.println(alvo.getNome() + " sofre " + danoPorTurno + " de dano de queimadura.");
            alvo.receberDano(danoPorTurno, false);
        }
        super.processarTurno(alvo);
    }
}