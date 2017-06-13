package es.presupuestos.parser.utils;

public class PaserConstants {
	//Put here one file path in your system, if you are using WINDOW$ you need write scape character to directory separator:
	//Example: "C:\\User\\Documents\\yourFIle.html"
	//public  final static String DEMO_INPUT_FILE= "/home/dabyz/Descargas/Ingresos_N_15_E_R_6_1_104_1_0.HTM";
	//public  final static String DEMO_OUTPUT_FILE= "/home/dabyz/Descargas/Ingresos_N_15_E_R_6_1_104_1_0.json";
	public  final static String DEMO_INPUT_FILE= "C:\\repptosgeneralesdelestado\\origen\\N_15_E_R_6_1_104_1_0.HTM";
	public  final static String DEMO_OUTPUT_FILE= "C:\\repptosgeneralesdelestado\\destino\\N_15_E_R_6_1_104_1_0.json";
	
	//Tags to Parse
	public final static String ELEMENT_TABLE="table";
	public final static String TABLE_ROW = "tr";
	public final static String TABLE_COLUMN = "td";
	public final static String TABLE_HEAD = "thead";
	public final static String TABLE_BODY = "tbody";
	public final static String TABLE_DIV = "div";
	
	//To read files
	public static String SEPARATOR = "_";
	public static String ANNO_COMPLETE = "20";
	public static int POS_TYPE = 0;
	public static int POS_ANNO = 1;  //FIXME: Posicion año
 	
	//Columns Headers index
	public final static int ORGANICA = 0;
	public final static int DESCRIPCION = 1;
	public final static int TOTAL=10;
	
	//Columns headers value
	public final static String ORGANICA_TEXT = "Orgánica";
}
