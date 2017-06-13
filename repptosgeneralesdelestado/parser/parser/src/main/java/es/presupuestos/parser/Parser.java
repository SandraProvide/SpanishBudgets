package es.presupuestos.parser;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import es.presupuestos.parser.entities.RowFile;
import es.presupuestos.parser.utils.PaserConstants;

import static es.presupuestos.parser.utils.PaserConstants.*;

public class Parser<T> {

	//Logger Constants
	private Logger logger = Logger.getLogger("Parser");
	private String CLASS_NAME = "Parser";
	
	public Parser(){
		super();
	}
	
	/**
	 * Convert filePath data to RowFile Object
	 * @param file The data location
	 * @return The RowFile
	 */
	public List<RowFile> parserToObject(File file){
		logger.entering(CLASS_NAME, "parserObject",file);
		List<RowFile> listRf = new ArrayList<RowFile>();
		FileReader fr;
		int anno = 0;
		String type = "";
		String fileName = file.getName();
		String [] values = fileName.split(SEPARATOR);
		try { 
			try{
				String annoValue = values[POS_ANNO];
				anno = Integer.valueOf(annoValue) + 2000;
			}catch(NumberFormatException nfe){
				logger.log(Level.SEVERE, "Ocurrio un problema al parsear el año");
			}
			type = values[POS_TYPE];
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			StringBuilder htmlCode = new StringBuilder();
			while (br.ready()) {
				htmlCode.append(br.readLine());
			}
			Document document = Jsoup.parse(htmlCode.toString());
			Elements tables = document.select(ELEMENT_TABLE);
			Element table = null;
			for (Element t : tables) {
				// We need search for the table with the Header like:
				// Organica|ExplicaciÃ³n|...
				if (t.select(TABLE_HEAD) != null && !t.select(TABLE_HEAD).isEmpty()) {
					Element head = t.select(TABLE_HEAD).get(0);
					Elements rows = head.select(TABLE_ROW);
					Element rowFirst = rows.first();
					Elements columns = rowFirst.getElementsByTag(TABLE_COLUMN);
					if (columns != null && columns.get(0).text().equalsIgnoreCase(ORGANICA_TEXT)) {
						table = t;
						break;
					} else {
						continue;
					}
				} else {
					continue;
				}
			}
			if(table != null){
			Element tableBody = table.getElementsByTag(TABLE_BODY).first();
			Elements rows = tableBody.select(TABLE_ROW);
			String parentId = "";
			String parentDesc = "";
			for(int i = 0 ; i < rows.size() ; i++){
				RowFile rf = new RowFile();
				Element row = rows.get(i);
				Elements columns = row.select(TABLE_COLUMN);
				rf.setAnno(anno);
				rf.setType(type);
				String id = columns.get(ORGANICA).text();
				if(id!= null && id.split("\\.").length > 1){
					//is a child
					rf.setPadre_ID(parentId);
					rf.setPadre_DESC(parentDesc);
					rf.setHijo_DESC(columns.get(DESCRIPCION).text());
					rf.setHijo_ID(columns.get(ORGANICA).text());
					rf.setMillonesEuros(columns.last().text());
					listRf.add(rf);
				}else{
					//Is a parent
					parentDesc = columns.get(DESCRIPCION).text();
					parentId = columns.get(ORGANICA).text();
				}
			}
			}else{
				System.out.println("La tabla recuperaba es nula.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found, please review the params.");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("An error has ocurred with the file.");
			e.printStackTrace();
			return null;
		}
		return listRf;
	}
}
