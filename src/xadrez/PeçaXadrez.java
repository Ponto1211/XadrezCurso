package xadrez;

import tabuleiro.Peça;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PeçaXadrez extends Peça {

	private Cor cor;

	public PeçaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

	protected boolean temPeçaOponente(Posicao pos) {
		PeçaXadrez p = (PeçaXadrez) getTabuleiro().peça(pos);
		return p != null && p.getCor() != cor;
	}

}
