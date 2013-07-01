package com.booteak.basf.advmat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booteak.basf.advmat.common.BASFProcess;
import com.booteak.basf.advmat.common.ProductCompact;
import com.google.common.collect.ImmutableMap;

public class ProductSearchServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1630153357925686789L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(req.getParameterMap());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String processIdStr = req.getParameter("processId");
		int processId = Integer.valueOf(processIdStr);
		BASFProcess bp = BASFProcess.getIdToProcessMap().get(processId);
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter pw = resp.getWriter();
        
        List<ProductCompact> pcs = ElasticsearchClient.getInstance().
        		getCompactProductInfo(bp, req.getParameterMap(), 0, 100);
        Map<String, Object> scopes = ImmutableMap.<String, Object>builder()
        		.put("compactproducts", pcs)
        		.put("processId", processId)
        		.put("process", bp.getName()).build();
        CompiledMustacheTemplates.getInstance().getCompactProductsMustache().execute(pw, scopes).flush();
		
	}

}
