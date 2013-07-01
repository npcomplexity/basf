package com.booteak.basf.advmat;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class BASFApp {

	public BASFApp() {
	}
	
	public static void main(String[] args) {
		try {
			Server embeddedHttpServer = new Server(Integer.valueOf(System.getenv("PORT")));
			ServletHandler handler = new ServletHandler();
			embeddedHttpServer.setHandler(handler);
			handler.addServletWithMapping(ProcessesServlet.class, "/processes");
			handler.addServletWithMapping(ProductFiltersServlet.class, "/processes/productfilters");
			handler.addServletWithMapping(ProductSearchServlet.class, "/processes/search");
			handler.addServletWithMapping(ProductServlet.class, "/processes/product");
	        embeddedHttpServer.start();
	        embeddedHttpServer.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}