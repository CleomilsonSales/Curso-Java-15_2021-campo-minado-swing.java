package br.com.cleomilsonsales.cm.visao;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.com.cleomilsonsales.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class PainelRodape extends JPanel{
	private static String texto;
	private static String textoMinas;
	
	JLabel label = new JLabel();
	JLabel labelMinas = new JLabel();
	
	public PainelRodape(Tabuleiro tabuleiro) {
		texto = "Desenvolvido por Cleomilson Sales";
		textoMinas = "Existem "+tabuleiro.getMinas()+" minas";
		
		setLayout(new GridLayout(1,2));

		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setText(texto);
		add(label);
		
		labelMinas.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMinas.setText(textoMinas);
		add(labelMinas);

	}

}
