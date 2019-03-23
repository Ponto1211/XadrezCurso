package app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.Pe�aXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();
		List<Pe�aXadrez> capturadas = new ArrayList<>();

		while (true) {
			try {
				UI.limparTela();
				UI.printPartida(partida, capturadas);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicao(sc);

				boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);
				UI.limparTela();
				UI.printTabuleiro(partida.getPe�as(), movimentosPossiveis);

				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.lerPosicao(sc);

				System.out.println();
				Pe�aXadrez capturada = partida.movendoPe�a(origem, destino);
				if (capturada != null) {
					capturadas.add(capturada);
				}
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
