package br.com.cleomilsonsales.cm.visao;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.com.cleomilsonsales.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class PainelRodape extends JPanel{	
	private final JLabel label; 
	private final JLabel labelMinas;
	
	public PainelRodape(Tabuleiro tabuleiro) {
		label = new JLabel("Desenvolvido por Cleomilson Sales");
		labelMinas = new JLabel("Existem "+tabuleiro.getMinas()+" minas");
		
		setLayout(new GridLayout(1,2));

		label.setHorizontalAlignment(SwingConstants.LEFT);
		add(label);
		
		labelMinas.setHorizontalAlignment(SwingConstants.RIGHT);
		add(labelMinas);

	}

}
