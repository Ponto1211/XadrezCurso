package tabuleiro;

public abstract class Peça {

	protected Posicao posicao;
	private Tabuleiro tabuleiro;

	public Peça(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public abstract boolean[][] movimentosPossiveis();

	public boolean movimentoPossivel(Posicao pos) {
		return movimentosPossiveis()[pos.getLinha()][pos.getColuna()];
	}

	public boolean haMovimento() {
		boolean[][] mat = movimentosPossiveis();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
