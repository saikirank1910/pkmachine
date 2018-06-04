package com.prokarma.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServerConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ContextConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		 return null;
	}

	@Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
