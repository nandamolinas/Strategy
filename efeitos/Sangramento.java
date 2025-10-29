package efeitos;

import personagem.Personagem;

public class Sangramento extends EfeitoStatusBase {
    private final int danoPorTurno;

    public Sangramento() {
        this(3, 5); // Duração de 3 turnos, 5 de dano
    }

    public Sangramento(int duracao, int danoPorTurno) {
        super("Sangramento", duracao);
        this.danoPorTurno = danoPorTurno;
    }

    @Override
    public void processarTurno(Personagem alvo) {
        if (!isFinalizado()) {
            System.out.println(alvo.getNome() + " sofre " + danoPorTurno + " de dano de sangramento.");
            alvo.receberDano(danoPorTurno, false); // false = não pode ser esquivado/reduzido
        }
        super.processarTurno(alvo);
    }
}