package br.com.cleomilsonsales.cm.visao;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import br.com.cleomilsonsales.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {
	
	public TelaPrincipal() {
		//definindo tamanho do jogo
		Tabuleiro tabuleiro = new Tabuleiro(12, 13, 13);
		
		setLayout(new BorderLayout());
		getContentPane().add(new PainelTabuleiro(tabuleiro), BorderLayout.CENTER);
		getContentPane().add(new PainelRodape(tabuleiro), BorderLayout.SOUTH);
		
		setTitle("Campo Minado");
		setSize(690, 438);
		setLocale(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TelaPrincipal();
	}
}
