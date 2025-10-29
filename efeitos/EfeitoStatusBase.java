package efeitos;

import personagem.Personagem;

public abstract class EfeitoStatusBase implements EfeitoStatus {
    protected int duracao;
    protected String nome;

    public EfeitoStatusBase(String nome, int duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }

    @Override
    public String getNome() { return nome; }

    @Override
    public int getDuracao() { return duracao; }

    @Override
    public void setDuracao(int duracao) { this.duracao = duracao; }

    @Override
    public boolean isFinalizado() { return duracao <= 0; }

    @Override
    public void aplicarEfeito(Personagem alvo) {
        System.out.println(alvo.getNome() + " está sofrendo de " + this.nome + "!");
    }

    @Override
    public void processarTurno(Personagem alvo) {
        this.duracao--;
        if (isFinalizado()) {
            System.out.println(this.nome + " acabou para " + alvo.getNome() + ".");
        }
    }
}