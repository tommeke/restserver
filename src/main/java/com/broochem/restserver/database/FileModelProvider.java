package com.broochem.restserver.database;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.tuple.Pair;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Simple mock-up implementation fetching the models from local directory and files 
 * 
 * @author tom
 *
 */

public class FileModelProvider implements IModelProvider {

	private File mainDir = null;
	
	@Override
	public void init( Properties props) {
		String dir = props.getProperty( DBProps.DIR);
		mainDir = new File( dir);
		if (!mainDir.exists() || !mainDir.isDirectory())
			throw new RuntimeException( "Model directory does not exists: " + dir);
	}
	
	@Override
	public List<Pair<Long, String>> getAllModels() {
		
		List<Pair<Long,String>> modelList = new ArrayList<Pair<Long,String>>();
		
		for ( String modelName : mainDir.list( new FilenameFilter() {
			
														@Override
														public boolean accept(File dir, String name) {
															return name.toUpperCase().endsWith( ".XML");
														}
													})) {
			modelList.add( Pair.of( (long) modelName.hashCode(), modelName));
		}
		
		return modelList;
	}

	@Override
	public String getModel(long modelId) {
		
		File modelFile = null;
		
		for ( File tempFile : mainDir.listFiles( new FilenameFilter() {
			
														@Override
														public boolean accept(File dir, String name) {
															return name.toUpperCase().endsWith( ".XML");
														}
													})) {
			if ( (long) tempFile.getName().hashCode() == modelId) {
				modelFile = tempFile;
				break;
			}
		}
		
		if (  modelFile == null)
			throw new RuntimeException( "model not found: " + modelId);
		
		try {
			XmlMapper xmlMapper = new XmlMapper();
			JsonNode node = xmlMapper.readTree(modelFile);
	
			ObjectMapper jsonMapper = new ObjectMapper();
			return jsonMapper.writeValueAsString(node);
		} catch ( Exception ex) {
			throw new RuntimeException( ex);
		}
	}

}
