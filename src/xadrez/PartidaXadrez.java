package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pe�as.Rei;
import xadrez.pe�as.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		PosicaoInicial();
	}

	public Pe�aXadrez[][] getPe�as() {
		Pe�aXadrez[][] mat = new Pe�aXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (Pe�aXadrez) tabuleiro.pe�a(i, j);
			}
		}
		return mat;
	}

	private void PosicaoInicial() {
		tabuleiro.colocarPe�a(new Torre(tabuleiro, Cor.BRANCO), new Posicao(7, 0));
		tabuleiro.colocarPe�a(new Torre(tabuleiro, Cor.BRANCO), new Posicao(7, 7));
		tabuleiro.colocarPe�a(new Torre(tabuleiro, Cor.PRETO), new Posicao(0, 0));
		tabuleiro.colocarPe�a(new Torre(tabuleiro, Cor.PRETO), new Posicao(0, 7));
		tabuleiro.colocarPe�a(new Rei(tabuleiro, Cor.BRANCO), new Posicao(7, 4));
		tabuleiro.colocarPe�a(new Rei(tabuleiro, Cor.PRETO), new Posicao(0, 4));
	}
}
