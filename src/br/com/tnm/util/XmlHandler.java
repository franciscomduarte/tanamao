package br.com.tnm.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import br.com.tnm.bean.Feed;
import android.util.Log;


public class XmlHandler extends DefaultHandler {
	private Feed feedStr = new Feed();
	private List<Feed> rssList = new ArrayList<Feed>();

	private int articlesAdded = 0;

	private static final int ARTIGO_LIMITE = 25;

	StringBuffer chars = new StringBuffer();

	public void startElement(String uri, String localName, String qName, Attributes atts) {
		chars = new StringBuffer();

		 if (qName.equalsIgnoreCase("media:content"))
				
		 {
		 	 if(!atts.getValue("url").toString().equalsIgnoreCase("null")){
		 	 feedStr.setImgLink(atts.getValue("url").toString());
		 	 }
		 	 else{
		 		 feedStr.setImgLink(""); 
		 	 }
		 }

	}
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (localName.equalsIgnoreCase("title") && feedStr.getTitulo()==null 
				&& !chars.toString().equalsIgnoreCase("PCI - Concursos")
				&& !chars.toString().contains("download")
				&& !chars.toString().contains("images")
				)
		{
			feedStr.setTitulo(chars.toString());
		}
		else if (localName.equalsIgnoreCase("description") && feedStr.getDescricao()==null && !chars.toString().contains("Últimas Notícias"))
		{

			feedStr.setDescricao(chars.toString());
		}
		else if (localName.equalsIgnoreCase("pubDate") && feedStr.getDataPublicacao()==null)
		{

			feedStr.setDataPublicacao(chars.toString());
		}
		else if (localName.equalsIgnoreCase("encoded") && feedStr.getEncodedContent()==null)
		{

			feedStr.setEncodedContent(chars.toString());
		}
		else if (qName.equalsIgnoreCase("media:content"))

		{

		}
		else if (localName.equalsIgnoreCase("link")  && feedStr.getUrl()==null && !chars.toString().equals("http://www.pciconcursos.com.br/"))
		{
			feedStr.setUrl(chars.toString());

		}
		
		else if (localName.equalsIgnoreCase("category") && feedStr.getCategoria()==null)
		{
			
			feedStr.setCategoria(chars.toString());
		}
		
//		if (localName.equalsIgnoreCase("item")  && localName.equalsIgnoreCase("")) {
		if (feedStr.getTitulo() != null && 
			 feedStr.getDescricao() != null && 
			 feedStr.getDataPublicacao() != null && 
			 //feedStr.getImgLink() != null &&
			 feedStr.getCategoria()!=null){
			rssList.add(feedStr);

			feedStr = new Feed();
			articlesAdded++;
			if (articlesAdded >= ARTIGO_LIMITE)
			{
				throw new SAXException();
			}
		}
//		}
	}

	public void characters(char ch[], int start, int length) {
		chars.append(new String(ch, start, length));
	}


	public List<Feed> getLatestArticles(String feedUrl) {
		URL url = null;
		try {

			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			url = new URL(feedUrl);
			xr.setContentHandler(this);
			xr.parse(new InputSource(url.openStream()));
		} catch (IOException e) {
		} catch (SAXException e) {

		} catch (ParserConfigurationException e) {

		}

		return rssList;
	}

}