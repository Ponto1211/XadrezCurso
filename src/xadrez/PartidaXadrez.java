package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.peças.Rei;
import xadrez.peças.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		PosicaoInicial();
	}

	public PeçaXadrez[][] getPeças() {
		PeçaXadrez[][] mat = new PeçaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PeçaXadrez) tabuleiro.peça(i, j);
			}
		}
		return mat;
	}

	private void PosicaoInicial() {
		tabuleiro.colocarPeça(new Torre(tabuleiro, Cor.BRANCO), new Posicao(7, 0));
		tabuleiro.colocarPeça(new Torre(tabuleiro, Cor.BRANCO), new Posicao(7, 7));
		tabuleiro.colocarPeça(new Torre(tabuleiro, Cor.PRETO), new Posicao(0, 0));
		tabuleiro.colocarPeça(new Torre(tabuleiro, Cor.PRETO), new Posicao(0, 7));
		tabuleiro.colocarPeça(new Rei(tabuleiro, Cor.BRANCO), new Posicao(7, 4));
		tabuleiro.colocarPeça(new Rei(tabuleiro, Cor.PRETO), new Posicao(0, 4));
	}
}
