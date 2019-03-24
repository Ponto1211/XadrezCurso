package xadrez.peças;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;

public class Rei extends PeçaXadrez {

	private PartidaXadrez partida;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partida) {
		super(tabuleiro, cor);
		this.partida = partida;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean testarRoque(Posicao pos) {
		PeçaXadrez p = (PeçaXadrez) getTabuleiro().peça(pos);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContagemMovimento() == 0;
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

		if (getContagemMovimento() == 0 && !partida.getCheck()) {
			Posicao pT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if (testarRoque(pT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if (!getTabuleiro().temPeça(p1) && !getTabuleiro().temPeça(p2)) {
					mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
			Posicao pT2 = new Posicao(posicao.getLinha(), posicao.getColuna() -4);
			if (testarRoque(pT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if (!getTabuleiro().temPeça(p1) && !getTabuleiro().temPeça(p2) && !getTabuleiro().temPeça(p3)) {
					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}

		}

		return mat;
	}
}
