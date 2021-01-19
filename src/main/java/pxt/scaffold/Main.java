package pxt.scaffold;

import java.util.logging.Level;
import java.util.logging.Logger;

import pxt.scaffold.exceptions.ParameterException;

public class Main {
	
	private static String basePath;
	private static String basePackage;
	private static String idType;
	private static String model;
	
	private static final Logger LOGGER = Logger.getLogger( Main.class.getName() );

	public static void main(String args[]) {
		try {
			readParameters(args);
			FileClassCreator fileClassCreator = new FileClassCreator(basePath, basePackage,idType,model);
			fileClassCreator.createFiles();
		} catch (ParameterException e) {
			LOGGER.log(Level.WARNING,e.getMessage());
		}
		
	}

	private static void readParameters(String[] args) throws ParameterException {
		if(args.length == 8) {
			for(int i = 0 ; i<args.length ;i++) {
				if(args[i].equals("basePath")) {
					basePath = args[i+1];
					i++;
				}else if(args[i].equals("basePackage")) {
					basePackage = args[i+1];
					i++;
				}else if(args[i].equals("idType")) {
					idType = args[i+1];
					i++;
				}else if(args[i].equals("model")) {
					model = args[i+1];
					i++;
				}else {
					throw new ParameterException("Parâmetro inválido: " +  args[i]);
				}
			}
		}else {
			throw new ParameterException("Informe os parametros basePath, basePackage,idType e model");
		}
	}
}
