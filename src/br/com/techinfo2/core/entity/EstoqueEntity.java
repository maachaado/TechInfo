package br.com.techinfo2.core.entity;

public class EstoqueEntity {

	private Long codigo;
	private String placaMae;
	private String processador;
	private String fonte;
	private String memoria;
	private String placaVideo;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getPlacaMae() {
		return placaMae;
	}
	public void setPlacaMae(String placaMae) {
		this.placaMae = placaMae;
	}
	public String getProcessador() {
		return processador;
	}
	public void setProcessador(String processador) {
		this.processador = processador;
	}
	public String getFonte() {
		return fonte;
	}
	public void setFonte(String fonte) {
		this.fonte = fonte;
	}
	public String getMemoria() {
		return memoria;
	}
	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}
	public String getPlacaVideo() {
		return placaVideo;
	}
	public void setPlacaVideo(String placaVideo) {
		this.placaVideo = placaVideo;
	}

}
