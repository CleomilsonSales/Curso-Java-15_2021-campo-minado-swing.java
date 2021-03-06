package br.com.cleomilsonsales.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {
	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<>();
	private List<CampoObservador> observadores = new ArrayList<>();
	
	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public void registrarObservador(CampoObservador observador) {
		observadores.add(observador);
	}
	
	private void notificarObservadores(CampoEvento evento) {
		observadores.stream()
			.forEach(o -> o.eventoOcorreu(this, evento));
	}
	
	boolean adicionarVizinhos(Campo vizinho) {
		//canditados a vizihos
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		// se for dois forem diferente sera uma analise de casa na diagonal
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		//diferen?a entre as coluna //pegando numero absoluto
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		//analise: para saber se ? vinho em casos de colunas e linha a somatoria dever? ser 1 e na diagona dever? ser 2 (isso ? a rela??o logica)
		int deltaGeral = deltaColuna + deltaLinha;
		
		if (deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return  true;
		}else if (deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else {
			return false;
		}
	}
	
	public void alternarMarcacao() {
		//para impedir abrir um campo marcado
		if (!aberto) {
			marcado = !marcado;
			if(marcado) {
				notificarObservadores(CampoEvento.MARCAR);
			}else {
				notificarObservadores(CampoEvento.DESMARCAR);
			}
		}
		
	}
	
	public boolean abrir() {
		//a??o para abrir campo
		if(!aberto && !marcado) {			
			if(minado) {
				//pra criar uma lista de tarefa no TASK
				//quando usa o "TODO" quer dizer que sera algo que precisa fazer, mas que deixar pra depois
				// quando usa "FIXME" quer dizer que ? erro que precisa ser corrigido depois
				
				notificarObservadores(CampoEvento.EXPLODIR);
				return true; // para sair do metodo	
			}
			
			setAberto(true);
			
			if (vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			
			return true;
		}else {
			return false;
		}
		
	}
	
	void minar() {
		minado =  true;
	}
	
	public boolean isMinado() {
		return minado;
	}
	
	public boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	//boa pratica: quando for algo boolean n?o usar o termo GET mas IS
	public boolean isMarcado() {
		return marcado;
	}
	
	void setAberto(boolean aberto) {
		this.aberto = aberto;
		
		if (aberto) {
			notificarObservadores(CampoEvento.ABRIR);
		}
	}
	
	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	public int minasNaVizinhanca() {
		return (int) vizinhos.stream().filter(v -> v.minado).count();
	}
	
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
		
		notificarObservadores(CampoEvento.REINICIAR);
	}
}
