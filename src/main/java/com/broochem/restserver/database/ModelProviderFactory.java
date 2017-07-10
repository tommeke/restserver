package com.broochem.restserver.database;

import java.util.Properties;

public class ModelProviderFactory {
	
	private Properties props = null;
	
	// singleton
	private static ModelProviderFactory modelProviderFactory = null;

	private static IModelProvider modelProvider = null;
	
	public static ModelProviderFactory getInstance( Properties props) {
		if (  modelProviderFactory == null) {
			modelProviderFactory = new ModelProviderFactory( props);
		}
		
		return modelProviderFactory;
	}
	
	public static ModelProviderFactory getInstance() {
		return modelProviderFactory;
	}
	
	private ModelProviderFactory( Properties props) {
		this.props = props;
	}
	
	public IModelProvider createModelProvider() {
		if ( modelProvider == null) {
			modelProvider = new FileModelProvider();
			modelProvider.init( props);
		}
		return modelProvider;
	}

}
