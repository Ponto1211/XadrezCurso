package app;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;
import xadrez.PosicaoXadrez;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static PosicaoXadrez lerPosicao(Scanner sc) {
		try {
			String s = sc.nextLine();
			char c = s.charAt(0);
			int l = Integer.parseInt(s.substring(1));
			return new PosicaoXadrez(c, l);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Posição invalida");
		}
	}

	public static void printPartida(PartidaXadrez partida, List<PeçaXadrez> capturadas) {
		printTabuleiro(partida.getPeças());
		System.out.println();
		peçasCapturadas(capturadas);
		System.out.println();
		System.out.println("Turno: " + partida.getTurno());
		if (!partida.getCheckMate()) {
			System.out.println("Esperando jogador: " + partida.getVez());
			if (partida.getCheck()) {
				System.out.println("CHECK!");
			}
		} else {
			System.out.println("CHECKMATE!!!");
			System.out.println("Vencedor: " + partida.getVez());
		}
	}

	public static void printTabuleiro(PeçaXadrez[][] peças) {
		for (int i = 0; i < peças.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < peças.length; j++) {
				printPeça(peças[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	public static void printTabuleiro(PeçaXadrez[][] peças, boolean[][] possiveis) {
		for (int i = 0; i < peças.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < peças.length; j++) {
				printPeça(peças[i][j], possiveis[i][j]);

			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	public static void printPeça(PeçaXadrez peça, boolean fundo) {
		if (fundo) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peça == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (peça.getCor() == Cor.BRANCO) {
				System.out.print(ANSI_WHITE + peça + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + peça + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

	public static void peçasCapturadas(List<PeçaXadrez> capturadas) {
		List<PeçaXadrez> brancas = capturadas.stream().filter(x -> x.getCor().equals(Cor.BRANCO))
				.collect(Collectors.toList());
		List<PeçaXadrez> pretas = capturadas.stream().filter(x -> x.getCor().equals(Cor.PRETO))
				.collect(Collectors.toList());

		System.out.println("Peças capturadas:");
		System.out.print(ANSI_WHITE);
		System.out.print("Brancas: ");
		System.out.println(Arrays.toString(brancas.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print(ANSI_YELLOW);
		System.out.print("Pretas: ");
		System.out.println(Arrays.toString(pretas.toArray()));
		System.out.print(ANSI_RESET);

	}
}
