package tabuleiro;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private Peça[][] peças;

	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new TabuleiroException("Deve haver pelo menos 1 linha e 1 coluna no tabuleiro.");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		peças = new Peça[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public Peça peça(int linha, int coluna) {
		if (!posicaoExiste(linha, coluna)) {
			throw new TabuleiroException("Posição fora do tabuleiro.");
		}
		return peças[linha][coluna];
	}

	public Peça peça(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posição fora do tabuleiro.");
		}
		return peças[posicao.getLinha()][posicao.getColuna()];
	}

	public void colocarPeça(Peça p, Posicao pos) {
		if (temPeça(pos)) {
			throw new TabuleiroException("Já há uma peça na posição " + pos + ".");
		}
		peças[pos.getLinha()][pos.getColuna()] = p;
		p.posicao = pos;
	}

	public Peça retirarPeça(Posicao pos) {
		if (!posicaoExiste(pos)) {
			throw new TabuleiroException("Posição fora do tabuleiro.");
		}
		if (peça(pos) == null) {
			return null;
		}
		Peça aux = peça(pos);
		aux.posicao = null;
		peças[pos.getLinha()][pos.getColuna()] = null;
		return aux;
	}

	private boolean posicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}

	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}

	public boolean temPeça(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posição fora do tabuleiro.");
		}
		return peça(posicao) != null;
	}

}
