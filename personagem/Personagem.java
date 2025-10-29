package personagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

import arma.IArma;
import batalha.AtaqueResultado;
import efeitos.EfeitoStatus;

public abstract class Personagem {
    // Atributos Base
    protected String nome;
    protected int hp;
    protected int hpMax;
    protected int mana;
    protected int manaMax;
    protected int forca;
    protected int destreza;
    protected int inteligencia;

    // Estratégia de Arma
    protected IArma armaEquipada;
    protected List<IArma> inventarioArmas = new ArrayList<>();

    // Efeitos
    protected List<EfeitoStatus> efeitosAtivos = new ArrayList<>();
    protected boolean atordoado = false;
    protected boolean desprevenido = true; // Começa desprevenido
    protected Random rng = new Random();

    public Personagem(String nome, int hp, int mana, int forca, int destreza, int inteligencia) {
        this.nome = nome;
        this.hpMax = hp;
        this.hp = hp;
        this.manaMax = mana;
        this.mana = mana;
        this.forca = forca;
        this.destreza = destreza;
        this.inteligencia = inteligencia;
    }

    // --- Lógica de Combate (Strategy) ---

    public void equiparArma(IArma arma) {
        if (arma.podeEquipar(this)) {
            this.armaEquipada = arma;
            System.out.println(this.nome + " equipou " + arma.getNome() + ".");
        } else {
            System.out.println(this.nome + " não tem os atributos para equipar " + arma.getNome() + ".");
        }
    }
    
    public void adicionarArmaInventario(IArma arma) {
        if(arma.podeEquipar(this)) {
            this.inventarioArmas.add(arma);
        }
    }
    
    public void trocarArma(int indice) {
        if(indice >= 0 && indice < inventarioArmas.size()) {
            equiparArma(inventarioArmas.get(indice));
        }
    }

    public AtaqueResultado atacar(Personagem alvo) {
        if (this.armaEquipada == null) {
            return new AtaqueResultado(0, null, this.nome + " está desarmado!");
        }
        if (this.mana < this.armaEquipada.getCustoMana()) {
            return new AtaqueResultado(0, null, this.nome + " não tem mana suficiente!");
        }

        this.gastarMana(this.armaEquipada.getCustoMana());
        
        // Delega o ataque para a Strategy (Arma)
        AtaqueResultado resultado = this.armaEquipada.atacar(this, alvo);
        
        // Adiciona um crítico simples (Bônus)
        if(rng.nextInt(100) < 10) { // 10% de chance de crítico
            int danoCritico = resultado.getDano() * 2;
            String msgCritico = resultado.getMensagem() + " (ACERTO CRÍTICO!)";
            return new AtaqueResultado(danoCritico, resultado.getEfeitos(), msgCritico, resultado.isAtaqueEmArea());
        }

        return resultado;
    }

    // --- Lógica de Turno e Efeitos ---

    public void receberDano(int dano, boolean podeSerPassiva) {
        int danoFinal = dano;
        
        if (podeSerPassiva) {
            // Tenta Esquivar (Passiva Arqueiro)
            if (this.tentarEsquivar()) {
                System.out.println(this.nome + " se esquivou do ataque!");
                return;
            }
            // Reduz Dano (Passiva Guerreiro)
            danoFinal = this.reduzirDanoRecebido(dano);
        }

        this.hp -= danoFinal;
        if (this.hp < 0) this.hp = 0;
        System.out.println(this.nome + " recebeu " + danoFinal + " de dano. HP restante: " + this.hp);
    }
    
    public void aplicarEfeito(EfeitoStatus efeito) {
        // Evita duplicatas, reinicia a duração
        for(EfeitoStatus e : efeitosAtivos) {
            if(e.getNome().equals(efeito.getNome())) {
                e.setDuracao(efeito.getDuracao());
                System.out.println(this.nome + " teve o efeito " + e.getNome() + " reiniciado.");
                return;
            }
        }
        efeito.aplicarEfeito(this);
        this.efeitosAtivos.add(efeito);
    }
    
    public void processarInicioTurno() {
        this.desprevenido = false; // Não está mais desprevenido após o primeiro turno
        
        // Passivas de regeneração (Mago)
        this.regenerarManaPassivo();
        
        // Processa efeitos
        Iterator<EfeitoStatus> it = efeitosAtivos.iterator();
        while(it.hasNext()) {
            EfeitoStatus efeito = it.next();
            efeito.processarTurno(this);
            if(efeito.isFinalizado()) {
                it.remove();
            }
        }
    }
    
    // --- Métodos de Passiva (para subclasses) ---
    protected boolean tentarEsquivar() { return false; } // Arqueiro
    protected int reduzirDanoRecebido(int dano) { return dano; } // Guerreiro
    protected void regenerarManaPassivo() { } // Mago

    // --- Getters e Setters ---
    public String getNome() { return nome; }
    public int getHp() { return hp; }
    public int getMana() { return mana; }
    public int getForca() { return forca; }
    public int getDestreza() { return destreza; }
    public int getInteligencia() { return inteligencia; }
    public boolean isVivo() { return this.hp > 0; }
    public boolean isAtordoado() { return atordoado; }
    public void setAtordoado(boolean atordoado) { 
        this.atordoado = atordoado; 
        if(atordoado) this.desprevenido = true; // Fica desprevenido se atordoado
    }
    public boolean isDesprevenido() { return desprevenido; }
    public void gastarMana(int custo) { this.mana -= custo; }
    public IArma getArmaEquipada() { return armaEquipada; }
    public List<IArma> getInventarioArmas() { return inventarioArmas; }
    public String getStatus() {
        return String.format("[%s | HP: %d/%d | Mana: %d/%d]", nome, hp, hpMax, mana, manaMax);
    }
}