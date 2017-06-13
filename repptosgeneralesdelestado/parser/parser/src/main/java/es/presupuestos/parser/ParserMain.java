package es.presupuestos.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import es.presupuestos.parser.entities.RowFile;
import es.presupuestos.parser.utils.JSONConvert;

import static es.presupuestos.parser.utils.PaserConstants.*;

/*
 * Parser to convert an Html Table to a Json format. 
 */
public class ParserMain {

	public static void main(String[] args) {
		// Reading the file
		String filePath = "";
		String fileOutput = "";
		Parser parser = new Parser();
		File file = null;
		try {
			filePath = args[0];
			fileOutput = args[1];

		} catch (IndexOutOfBoundsException e) {
			filePath = DEMO_INPUT_FILE;
			fileOutput = DEMO_OUTPUT_FILE;
		}
		file = new File(filePath);
		List<RowFile> resultList = new ArrayList<RowFile>();
		if(file.isDirectory()){
			for (File f : file.listFiles()){
				String fileName = f.getName();
				String [] fileParts= fileName.split("\\.");
				String extension = fileParts!= null && fileParts.length>0?fileParts[1]:"";
				if(extension.equalsIgnoreCase("htm")||extension.equalsIgnoreCase("html")){
					resultList.addAll(parser.parserToObject(f));
				}else{
					continue;
				}
			}
		}else{
			resultList= parser.parserToObject(file);
		}
		JSONConvert jsc = new JSONConvert(fileOutput);
		jsc.writeToFile(resultList);

	}
}
