package xadrez;

import tabuleiro.Pe�a;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class Pe�aXadrez extends Pe�a {

	private Cor cor;
	private int contagemMovimento;

	public Pe�aXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

	public int getContagemMovimento() {
		return contagemMovimento;
	}

	public void aumentarContagem() {
		contagemMovimento++;
	}

	public void reduzirContagem() {
		contagemMovimento--;
	}

	public PosicaoXadrez getPe�aXadrez() {
		return PosicaoXadrez.fromPosicao(posicao);
	}

	protected boolean temPe�aOponente(Posicao pos) {
		Pe�aXadrez p = (Pe�aXadrez) getTabuleiro().pe�a(pos);
		return p != null && p.getCor() != cor;
	}

}
