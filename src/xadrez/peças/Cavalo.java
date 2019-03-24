package xadrez.pe�as;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Pe�aXadrez;

public class Cavalo extends Pe�aXadrez {

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "C";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);

		p.setPosicao(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && (!getTabuleiro().temPe�a(p) || temPe�aOponente(p))) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicao(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExiste(p) && (!getTabuleiro().temPe�a(p) || temPe�aOponente(p))) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicao(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getTabuleiro().posicaoExiste(p) && (!getTabuleiro().temPe�a(p) || temPe�aOponente(p))) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicao(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && (!getTabuleiro().temPe�a(p) || temPe�aOponente(p))) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicao(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && (!getTabuleiro().temPe�a(p) || temPe�aOponente(p))) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicao(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExiste(p) && (!getTabuleiro().temPe�a(p) || temPe�aOponente(p))) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicao(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if (getTabuleiro().posicaoExiste(p) && (!getTabuleiro().temPe�a(p) || temPe�aOponente(p))) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setPosicao(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && (!getTabuleiro().temPe�a(p) || temPe�aOponente(p))) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}

}
