package personagem;

import arma.CajadoArcano;
import arma.AdagaSombria;

public class Mago extends Personagem {

    public Mago(String nome) {
        // Atributos Base: Força 5, Destreza 7, Inteligência 18, Vida 70, Mana 150
        super(nome, 70, 150, 5, 7, 18);
        
        // Equipamento inicial
        adicionarArmaInventario(new CajadoArcano());
        adicionarArmaInventario(new AdagaSombria());
        equiparArma(inventarioArmas.get(0));
    }

    // Habilidade Passiva: "Regeneração de Mana" +10 mana por turno
    @Override
    protected void regenerarManaPassivo() {
        this.mana += 10;
        if (this.mana > this.manaMax) {
            this.mana = this.manaMax;
        }
        System.out.println(this.nome + " (Regeneração) recupera 10 de mana.");
    }
}
