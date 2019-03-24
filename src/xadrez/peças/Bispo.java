package xadrez.peças;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaXadrez;

public class Bispo extends PeçaXadrez {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);

		p.setPosicao(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setPosicao(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temPeçaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicao(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setPosicao(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temPeçaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicao(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setPosicao(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temPeçaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicao(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeça(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setPosicao(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temPeçaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

}
