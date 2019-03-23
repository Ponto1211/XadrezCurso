package xadrez.peças;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaXadrez;

public class Rei extends PeçaXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				p.setPosicao(posicao.getLinha() - i, posicao.getColuna() - j);
				if (getTabuleiro().posicaoExiste(p) && (!getTabuleiro().temPeça(p) || temPeçaOponente(p))) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			}
		}

		return mat;
	}
}
