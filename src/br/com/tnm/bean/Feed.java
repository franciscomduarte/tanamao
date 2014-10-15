package br.com.tnm.bean;

import java.net.URL;

public class Feed {
	
	private long feedId;
	private String titulo;
	private String descricao;
	private String imgLink;
	private String dataPublicacao;
	private String categoria;
	private String url;	
	private String encodedContent;
	
	
	private long artigoId;
	public long getArtigoId() {
		return artigoId;
	}
	public void setArtigoId(long artigoId) {
		this.artigoId = artigoId;
	}
	public long getFeedId() {
		return feedId;
	}
	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;

		if (descricao.contains("<img ")){
		String img = descricao.substring(descricao.indexOf("<img "));
		String cleanUp = img.substring(0, img.indexOf(">")+1);
		img = img.substring(img.indexOf("src=") + 5);
		int indexOf = img.indexOf("'");
		if (indexOf==-1){
		indexOf = img.indexOf("\"");
		}
		img = img.substring(0, indexOf);

		//setImgLink(img);

		this.descricao = this.descricao.replace(cleanUp, "");
		}
	}
	public String getImgLink() {
		return imgLink;
	}
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
	public String getDataPublicacao() {
		return dataPublicacao;
	}
	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEncodedContent() {
		return encodedContent;
	}
	public void setEncodedContent(String encodedContent) {
		this.encodedContent = encodedContent;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	


}
