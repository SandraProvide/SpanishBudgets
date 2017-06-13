package es.presupuestos.parser.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Write a POJO (Plain Old Java Object) in a file
 *
 */
public class JSONConvert {
	private String filePath;
	private ObjectMapper mapper;
	
	//Log Constants
	private String CLASS_NAME = JSONConvert.class.getName();
	private Logger logger = Logger.getLogger(CLASS_NAME);
	
	public JSONConvert(String filePath){
		super();
		logger.entering(CLASS_NAME, "JSONWriter",filePath);
		this.filePath = filePath;;
		this.mapper = new ObjectMapper();
		logger.exiting(CLASS_NAME, "JSONWriter");
	}
	
	/**
	 * Write objectToSeralize to filePath (indicate in contructor)
	 * @param objectToSerialize
	 * @return
	 */
	public boolean writeToFile(Object objectToSerialize){
		logger.entering(CLASS_NAME, "writeToFile",objectToSerialize);
		boolean result = false;
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.writeValue(new File(filePath), objectToSerialize);
			result = true;
		}catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.exiting(CLASS_NAME, "writeToFile",result);
		return result;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
