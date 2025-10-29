package personagem;

import arma.EspadaLonga;
import arma.MachadoDeGuerra;

public class Guerreiro extends Personagem {

    public Guerreiro(String nome) {
        // Atributos Base: Força 15, Destreza 8, Inteligência 5, Vida 120, Mana 50
        super(nome, 120, 50, 15, 8, 5);
        
        // Equipamento inicial
        adicionarArmaInventario(new EspadaLonga());
        adicionarArmaInventario(new MachadoDeGuerra());
        equiparArma(inventarioArmas.get(0));
    }

    // Habilidade Passiva: "Pele Dura" - Reduz dano recebido em 20%
    @Override
    protected int reduzirDanoRecebido(int dano) {
        int danoReduzido = (int) (dano * 0.80);
        System.out.println(this.nome + " (Pele Dura) reduz o dano para " + danoReduzido + ".");
        return danoReduzido;
    }
}