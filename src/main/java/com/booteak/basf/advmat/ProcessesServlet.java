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
import com.google.common.collect.ImmutableMap;

public class ProcessesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6563188491167062554L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter pw = resp.getWriter();
        List<BASFProcess> procs = ElasticsearchClient.getInstance().getProcesses();
        Map<String, List<BASFProcess>> scopes = ImmutableMap.<String, List<BASFProcess>>builder().put("processes", procs).build();
        CompiledMustacheTemplates.getInstance().getProcessesMustache().execute(pw, scopes).flush();
	}

}
