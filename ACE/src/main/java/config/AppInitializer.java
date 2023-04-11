package config;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	private final Logger log = Logger.getLogger(this.getClass());

	@Override
	protected Class<?>[] getRootConfigClasses() {
		log.debug("AppInitializer Root Executed");
		return new Class[] { SpringConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return null;
	}

	@Override
	protected String[] getServletMappings() {
		log.debug("AppInitializer ServletMapping Executed");
		return new String[] { "/" };
	}
}