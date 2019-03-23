package app;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();

		while (true) {
			try {
				UI.limparTela();
				UI.printTabuleiro(partida.getPeças());
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicao(sc);

				boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);
				UI.limparTela();
				UI.printTabuleiro(partida.getPeças(), movimentosPossiveis);

				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.lerPosicao(sc);

				System.out.println();
				PeçaXadrez capturada = partida.movendoPeça(origem, destino);
			} catch (XadrezException xe) {
				System.out.println(xe.getMessage());
				sc.nextLine();
			} catch (InputMismatchException ie) {
				System.out.println(ie.getMessage());
				sc.nextLine();
			}
		}
	}

}
