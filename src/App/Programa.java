package app;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.Pe�aXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();

		while (true) {
			UI.printTabuleiro(partida.getPe�as());
			System.out.println();
			System.out.print("Origem: ");
			PosicaoXadrez origem = UI.lerPosicao(sc);

			System.out.println();
			System.out.print("Destino: ");
			PosicaoXadrez destino = UI.lerPosicao(sc);

			System.out.println();
			Pe�aXadrez capturada = partida.movendoPe�a(origem, destino);
		}
	}

}
