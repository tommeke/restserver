package com.broochem.restserver.database;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Interface defining the methods available for fetching models from the database / persistence layer 
 * 
 * @author tom
 *
 */

public interface IModelProvider {
	
	/**
	 * init properties for data connection and parameters go here.
	 * will get called by factory.
	 * 
	 * @param props
	 */
	
	public void init( Properties props);
	
	/**
	 * returns all models for this data source through their id and name. 
	 * 
	 * @return
	 */
	
	public List<Pair<Long,String>> getAllModels();
	
	/**
	 * get model based on id.
	 * 
	 * @param modelId
	 * @return model as JSON string
	 */
	
	public String getModel( long modelId);
	

}
