package pxt.scaffold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileClassCreator {

	private static final Logger LOGGER = Logger.getLogger(FileClassCreator.class.getName());

	private String basePath;
	private String basePackage;
	private String idType;
	private String model;

	public FileClassCreator(String basePath, String basePackage, String idType, String model) {
		super();
		this.basePath = basePath;
		this.basePackage = basePackage;
		this.idType = idType;
		this.model = model;
	}

	public void createFiles() {
		generateFileOnProject("", "domain",this.createDomainFileClass());
		generateFileOnProject("Controller", "controller",this.createControllerFileClass());
		generateFileOnProject("Repository", "repository",this.createRepositoryFileClass());
		generateFileOnProject("Service", "service",this.createServiceFileClass());
		
	}

	private void generateFileOnProject(String sufixClassName, String packageName,String content) {
		try {
			File f = new File(basePath + File.separator + "src" + File.separator + "main" + File.separator + "java"
					+ File.separator + basePackage.replace(".", File.separator) + File.separator + packageName + File.separator + model + sufixClassName
					+ ".java");

			if (f.createNewFile()) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(f));
				writer.write(content);

				writer.close();
			}

		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Falha ao gravar arquivo no disco", e);
		}

	}

	private String createDomainFileClass() {

		File domainTemplate = getFileFromResources("DomainTemplateClass");
		try {
			return replaceVariables(domainTemplate, "", "domain");
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Falha ao criar classe de domain", e);
			return "";
		}

	}

	private String createControllerFileClass() {

		File domainTemplate = getFileFromResources("ControllerTemplateClass");
		try {
			return replaceVariables(domainTemplate, "Controller", "controller");
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Falha ao criar classe de controller", e);
			return "";
		}

	}

	private String createServiceFileClass() {

		File domainTemplate = getFileFromResources("ServiceInterfaceTemplateClass");
		try {
			return replaceVariables(domainTemplate, "Service", "service");
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Falha ao criar interface de service", e);
			return "";
		}

	}

	private String createRepositoryFileClass() {
		File domainTemplate = getFileFromResources("RepositoryTemplateClass");
		try {
			return replaceVariables(domainTemplate, "Repository", "repository");
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Falha ao criar interface de repository", e);
			return "";
		}
	}

	private String replaceVariables(File domainTemplate, String sufixClassName, String packageFinalName)
			throws IOException {
		String contentTemplate = getContentFile(domainTemplate);
		String s = contentTemplate.replaceAll("<package>", basePackage + "." + packageFinalName);
		s = s.replaceAll("<class-name>", model + sufixClassName);
		s = s.replace("<base-package>", basePackage);
		s = s.replaceAll("<domain-name>", model);
		s = s.replaceAll("<id-type>", idType);
		s = s.replaceAll("<url-name>", model);
		s = s.replace("<interface-name>", model + "Service");
		return s;
	}

	// get file from classpath, resources folder
	private File getFileFromResources(String fileName) {

		ClassLoader classLoader = getClass().getClassLoader();

		URL resource = classLoader.getResource(fileName);
		if (resource == null) {
			throw new IllegalArgumentException("file is not found!");
		} else {
			return new File(resource.getFile());
		}

	}

	private String getContentFile(File file) throws IOException {

		StringBuilder fileContent = new StringBuilder();

		if (file == null)
			return "";

		try (FileReader reader = new FileReader(file); BufferedReader br = new BufferedReader(reader)) {

			String line;
			while ((line = br.readLine()) != null) {
				fileContent.append(line).append("\n");
				;
			}
			return fileContent.toString();
		}
	}
}
