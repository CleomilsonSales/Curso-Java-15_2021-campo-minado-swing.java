package br.com.cleomilsonsales.cm.modelo;

@FunctionalInterface
public interface CampoObservador {
	public void eventoOcorreu(Campo ccampo, CampoEvento evento);
}
