package br.com.cleomilsonsales.cm.visao;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.cleomilsonsales.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {
	
	public PainelTabuleiro(Tabuleiro tabuleiro) {
		setLayout(new GridLayout(
				tabuleiro.getLinhas(),tabuleiro.getColunas()));
		
		
		tabuleiro.paraCadaCampo(c -> add(new BotaoCampo(c)));
		
		tabuleiro.registrarObservadores(e -> {
			
			SwingUtilities.invokeLater(() -> { //esse recurso faz com que a mensagem so apareça depois que a interface for toda atualizada
				if(e.isGanhou()) {
					JOptionPane.showMessageDialog(this, "Ganhou :)");
				}else {
					JOptionPane.showMessageDialog(this, "Perdeu :(");
				}
				
				tabuleiro.reiniciar();
			});
		});
	}
}
