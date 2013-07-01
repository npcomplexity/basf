package com.booteak.basf.advmat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 826251889039049436L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String productIdStr = req.getParameter("productId");
		
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter pw = resp.getWriter();
        
        CompiledMustacheTemplates.getInstance().getProductMustache().execute(pw, 
        		ElasticsearchClient.getInstance().getProduct(productIdStr)).flush();
	}

}
