package tabuleiro;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private Pe�a[][] pe�as;

	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new TabuleiroException("Deve haver pelo menos 1 linha e 1 coluna no tabuleiro.");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pe�as = new Pe�a[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public Pe�a pe�a(int linha, int coluna) {
		if (!posicaoExiste(linha, coluna)) {
			throw new TabuleiroException("Posi��o fora do tabuleiro.");
		}
		return pe�as[linha][coluna];
	}

	public Pe�a pe�a(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posi��o fora do tabuleiro.");
		}
		return pe�as[posicao.getLinha()][posicao.getColuna()];
	}

	public void colocarPe�a(Pe�a p, Posicao pos) {
		if (temPe�a(pos)) {
			throw new TabuleiroException("J� h� uma pe�a na posi��o " + pos + ".");
		}
		pe�as[pos.getLinha()][pos.getColuna()] = p;
		p.posicao = pos;
	}

	public Pe�a retirarPe�a(Posicao pos) {
		if (!posicaoExiste(pos)) {
			throw new TabuleiroException("Posi��o fora do tabuleiro.");
		}
		if (pe�a(pos) == null) {
			return null;
		}
		Pe�a aux = pe�a(pos);
		aux.posicao = null;
		pe�as[pos.getLinha()][pos.getColuna()] = null;
		return aux;
	}

	private boolean posicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}

	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}

	public boolean temPe�a(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posi��o fora do tabuleiro.");
		}
		return pe�a(posicao) != null;
	}

}
