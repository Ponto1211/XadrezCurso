package xadrez;

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

	private void colocarNovaPeça(char c, int l, PeçaXadrez peça) {
		tabuleiro.colocarPeça(peça, new PosicaoXadrez(c, l).toPosicao());
	}

	private void PosicaoInicial() {
		colocarNovaPeça('a',1,new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('h',1,new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('a',8,new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeça('h',8,new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeça('e',1,new Rei(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('e',8,new Rei(tabuleiro, Cor.PRETO));
	}
}
