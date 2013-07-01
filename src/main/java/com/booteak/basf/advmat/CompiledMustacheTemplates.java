package com.booteak.basf.advmat;

import java.io.IOException;
import java.io.PrintWriter;

import com.booteak.basf.advmat.common.BASFProcess;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class CompiledMustacheTemplates {

	private MustacheFactory mf;
	private Mustache processesMustache;
	private Mustache productFiltersMustache;
	private Mustache compactProductsMustache;
	private Mustache productMustache;

	private static final CompiledMustacheTemplates INSTANCE = new CompiledMustacheTemplates();
	
	public CompiledMustacheTemplates() {
		mf = new DefaultMustacheFactory();		
		processesMustache = mf.compile("templates/processes.mustache");
		productFiltersMustache = mf.compile("templates/productfilters.mustache");
		compactProductsMustache = mf.compile("templates/compactproducts.mustache");
		productMustache = mf.compile("templates/product.mustache");
	}


	public static CompiledMustacheTemplates getInstance() {
		return INSTANCE;
	}
	
	
	public Mustache getProcessesMustache() {
		return processesMustache;
	}

	public Mustache getProductFiltersMustache() {
		return productFiltersMustache;
	}

	public Mustache getCompactProductsMustache() {
		return compactProductsMustache;
	}

	public Mustache getProductMustache() {
		return productMustache;
	}

	public static void main(String[] args) {
		CompiledMustacheTemplates cmt = getInstance();
		Mustache m = cmt.getProcessesMustache();
		try {
			m.execute(new PrintWriter(System.out), 
					BASFProcess.getNameToProcessMap().values()).flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
