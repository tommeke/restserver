package com.broochem.restserver;

import java.util.Properties;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.broochem.restserver.database.DBProps;
import com.broochem.restserver.database.ModelProviderFactory;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {

		// data source init, pretty much hard coded with factory right now, service inject this later
		Properties props = new Properties();
		props.setProperty( DBProps.DIR, "./data");
		ModelProviderFactory.getInstance(props).createModelProvider();
		
		// Jersey REST config
		ResourceConfig config = new ResourceConfig();
		config.packages("com.broochem.restserver");
		ServletHolder servlet = new ServletHolder(new ServletContainer(config));

		ServletContextHandler restContext = new ServletContextHandler();
		restContext.addServlet( servlet, "/*");
		
		// Jetty static content
		ResourceHandler staticResourceHandler = new ResourceHandler();
	    staticResourceHandler.setResourceBase("./src/main/resource/webapp/");
	    ContextHandler staticContextHandler = new ContextHandler("/");
	    staticContextHandler.setHandler( staticResourceHandler);
				
	    // add handlers
		Server server = new Server(8888);

		HandlerList handlers = new HandlerList();
		handlers.setHandlers( new Handler[] {
				restContext,
				staticContextHandler
		});
		
		server.setHandler( handlers);
		
		try {
			server.start();
			server.join();
		} finally {
			server.destroy();
		}

	}
}
