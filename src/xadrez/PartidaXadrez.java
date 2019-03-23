package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peça;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.peças.Rei;
import xadrez.peças.Torre;

public class PartidaXadrez {

	private Tabuleiro tabuleiro;
	private int turno;
	private Cor vez;
	private boolean check;

	private List<Peça> capturadas = new ArrayList<>();
	private List<Peça> noTabuleiro = new ArrayList<>();;

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

	public PeçaXadrez[][] getPeças() {
		PeçaXadrez[][] mat = new PeçaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PeçaXadrez) tabuleiro.peça(i, j);
			}
		}
		return mat;
	}

	public boolean[][] movimentosPossiveis(PosicaoXadrez pOrigem) {
		Posicao p = pOrigem.toPosicao();
		validarOrigem(p);
		return tabuleiro.peça(p).movimentosPossiveis();

	}

	public PeçaXadrez movendoPeça(PosicaoXadrez origem, PosicaoXadrez destino) {
		Posicao ori = origem.toPosicao();
		Posicao des = destino.toPosicao();
		validarOrigem(ori);
		validarDestino(ori, des);
		Peça peçaCapturada = mover(ori, des);
		if (testarCheck(vez)) {
			desmover(ori, des, peçaCapturada);
			throw new XadrezException("Você não pode se colocar em cheque");
		}
		check = (testarCheck(oponente(vez)));
		atualizarTurno();
		return (PeçaXadrez) peçaCapturada;
	}

	private Peça mover(Posicao origem, Posicao destino) {
		Peça p = tabuleiro.retirarPeça(origem);
		Peça capturada = tabuleiro.retirarPeça(destino);
		tabuleiro.colocarPeça(p, destino);
		if (capturada != null) {
			noTabuleiro.remove(capturada);
			capturadas.add(capturada);
		}
		return capturada;
	}

	private void desmover(Posicao origem, Posicao destino, Peça capturada) {
		Peça p = tabuleiro.retirarPeça(destino);
		tabuleiro.colocarPeça(p, origem);
		if (capturada != null) {
			tabuleiro.colocarPeça(capturada, destino);
			capturadas.remove(capturada);
			noTabuleiro.add(capturada);
		}
	}

	public void validarOrigem(Posicao pos) {
		if (!tabuleiro.temPeça(pos)) {
			throw new XadrezException("Não há peça nessa posição.");
		}
		if (vez != ((PeçaXadrez) tabuleiro.peça(pos)).getCor()) {
			throw new XadrezException("Essa peça não é sua.");
		}
		if (!tabuleiro.peça(pos).haMovimento()) {
			throw new XadrezException("Sem movimentos possiveis para esta peça.");
		}
	}

	public void validarDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peça(origem).movimentoPossivel(destino)) {
			throw new XadrezException("Este movimento não é possivel");
		}

	}

	private void atualizarTurno() {
		turno++;
		vez = (vez.equals(Cor.BRANCO)) ? Cor.PRETO : Cor.BRANCO;
	}

	private Cor oponente(Cor c) {
		return (c == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private PeçaXadrez rei(Cor c) {
		List<Peça> lista = noTabuleiro.stream().filter(x -> ((PeçaXadrez) x).getCor().equals(c))
				.collect(Collectors.toList());
		for (Peça p : lista) {
			if (p instanceof Rei) {
				return (PeçaXadrez) p;
			}
		}
		throw new IllegalStateException("Não ha rei " + c + " no tabuleiro.");
	}

	private boolean testarCheck(Cor c) {
		Posicao pRei = rei(c).getPeçaXadrez().toPosicao();
		List<Peça> peçasOponente = noTabuleiro.stream().filter(x -> ((PeçaXadrez) x).getCor().equals(oponente(c)))
				.collect(Collectors.toList());
		for (Peça p : peçasOponente) {
			boolean[][] possiveisCheck = p.movimentosPossiveis();
			if (possiveisCheck[pRei.getLinha()][pRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private void colocarNovaPeça(char c, int l, PeçaXadrez peça) {
		tabuleiro.colocarPeça(peça, new PosicaoXadrez(c, l).toPosicao());
		noTabuleiro.add(peça);
	}

	private void PosicaoInicial() {
		colocarNovaPeça('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeça('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeça('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('e', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}
