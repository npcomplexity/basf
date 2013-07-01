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
import com.booteak.basf.advmat.common.ProductFilterOptions;
import com.google.common.collect.ImmutableMap;

public class ProductFiltersServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -127450100323275685L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String processIdStr = req.getParameter("processId");
		int processId = Integer.valueOf(processIdStr);
		BASFProcess bp = BASFProcess.getIdToProcessMap().get(processId);
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter pw = resp.getWriter();
        
        List<ProductFilterOptions> pfos = ElasticsearchClient.getInstance().getProductFilterOptions(bp.getId());
        Map<String, Object> scopes = ImmutableMap.<String, Object>builder()
        		.put("prodfilters", pfos)
        		.put("processId", processId)
        		.put("process", bp.getName()).build();
        CompiledMustacheTemplates.getInstance().getProductFiltersMustache().execute(pw, scopes).flush();
	}

}
