package xadrez;

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

	private void colocarNovaPe�a(char c, int l, Pe�aXadrez pe�a) {
		tabuleiro.colocarPe�a(pe�a, new PosicaoXadrez(c, l).toPosicao());
	}

	private void PosicaoInicial() {
		colocarNovaPe�a('a',1,new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('h',1,new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('a',8,new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('h',8,new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('e',1,new Rei(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('e',8,new Rei(tabuleiro, Cor.PRETO));
	}
}
