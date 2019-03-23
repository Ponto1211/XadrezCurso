package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Pe�a;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pe�as.Rei;
import xadrez.pe�as.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;
	private int turno;
	private Cor vez;
	private boolean check;

	private List<Pe�a> capturadas = new ArrayList<>();
	private List<Pe�a> noTabuleiro = new ArrayList<>();;

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		vez = Cor.BRANCO;
		PosicaoInicial();
	}

	public int getTurno() {
		return turno;
	}

	public Cor getVez() {
		return vez;
	}

	public boolean getCheck() {
		return check;
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

	public boolean[][] movimentosPossiveis(PosicaoXadrez pOrigem) {
		Posicao p = pOrigem.toPosicao();
		validarOrigem(p);
		return tabuleiro.pe�a(p).movimentosPossiveis();

	}

	public Pe�aXadrez movendoPe�a(PosicaoXadrez origem, PosicaoXadrez destino) {
		Posicao ori = origem.toPosicao();
		Posicao des = destino.toPosicao();
		validarOrigem(ori);
		validarDestino(ori, des);
		Pe�a pe�aCapturada = mover(ori, des);
		if (testarCheck(vez)) {
			desmover(ori, des, pe�aCapturada);
			throw new XadrezException("Voc� n�o pode se colocar em cheque");
		}
		check = (testarCheck(oponente(vez)));
		atualizarTurno();
		return (Pe�aXadrez) pe�aCapturada;
	}

	private Pe�a mover(Posicao origem, Posicao destino) {
		Pe�a p = tabuleiro.retirarPe�a(origem);
		Pe�a capturada = tabuleiro.retirarPe�a(destino);
		tabuleiro.colocarPe�a(p, destino);
		if (capturada != null) {
			noTabuleiro.remove(capturada);
			capturadas.add(capturada);
		}
		return capturada;
	}

	private void desmover(Posicao origem, Posicao destino, Pe�a capturada) {
		Pe�a p = tabuleiro.retirarPe�a(destino);
		tabuleiro.colocarPe�a(p, origem);
		if (capturada != null) {
			tabuleiro.colocarPe�a(capturada, destino);
			capturadas.remove(capturada);
			noTabuleiro.add(capturada);
		}
	}

	public void validarOrigem(Posicao pos) {
		if (!tabuleiro.temPe�a(pos)) {
			throw new XadrezException("N�o h� pe�a nessa posi��o.");
		}
		if (vez != ((Pe�aXadrez) tabuleiro.pe�a(pos)).getCor()) {
			throw new XadrezException("Essa pe�a n�o � sua.");
		}
		if (!tabuleiro.pe�a(pos).haMovimento()) {
			throw new XadrezException("Sem movimentos possiveis para esta pe�a.");
		}
	}

	public void validarDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.pe�a(origem).movimentoPossivel(destino)) {
			throw new XadrezException("Este movimento n�o � possivel");
		}

	}

	private void atualizarTurno() {
		turno++;
		vez = (vez.equals(Cor.BRANCO)) ? Cor.PRETO : Cor.BRANCO;
	}

	private Cor oponente(Cor c) {
		return (c == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private Pe�aXadrez rei(Cor c) {
		List<Pe�a> lista = noTabuleiro.stream().filter(x -> ((Pe�aXadrez) x).getCor().equals(c))
				.collect(Collectors.toList());
		for (Pe�a p : lista) {
			if (p instanceof Rei) {
				return (Pe�aXadrez) p;
			}
		}
		throw new IllegalStateException("N�o ha rei " + c + " no tabuleiro.");
	}

	private boolean testarCheck(Cor c) {
		Posicao pRei = rei(c).getPe�aXadrez().toPosicao();
		List<Pe�a> pe�asOponente = noTabuleiro.stream().filter(x -> ((Pe�aXadrez) x).getCor().equals(oponente(c)))
				.collect(Collectors.toList());
		for (Pe�a p : pe�asOponente) {
			boolean[][] possiveisCheck = p.movimentosPossiveis();
			if (possiveisCheck[pRei.getLinha()][pRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private void colocarNovaPe�a(char c, int l, Pe�aXadrez pe�a) {
		tabuleiro.colocarPe�a(pe�a, new PosicaoXadrez(c, l).toPosicao());
		noTabuleiro.add(pe�a);
	}

	private void PosicaoInicial() {
		colocarNovaPe�a('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPe�a('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		colocarNovaPe�a('e', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}
