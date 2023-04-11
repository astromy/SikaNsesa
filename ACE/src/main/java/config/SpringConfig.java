package config;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
/*import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;*/
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.scheduling.annotation.EnableScheduling;
/*import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;*/
import org.springframework.ui.Model;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import models.Users;

@EnableWebMvc
@EnableScheduling
@Configuration
/* @EnableRedisHttpSession */
@ComponentScan(basePackages = { "acecontroller", "aceservice", "repository","dto" })
@Import({ SecurityConfig.class, JPAConfig.class })
public class SpringConfig extends WebMvcConfigurerAdapter {
	private final Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:js/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:css/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:fonts/");
		registry.addResourceHandler("/styles/**").addResourceLocations("classpath:styles/");
		registry.addResourceHandler("/scripts/**").addResourceLocations("classpath:scripts/");
		registry.addResourceHandler("/vendor/**").addResourceLocations("classpath:vendor/");
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:images/");
		registry.addResourceHandler("/reports/**").addResourceLocations("classpath:reports/");
		log.debug("Add Resource Handler Executed");
	}

	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver() {
		JasperReportsViewResolver reportResolver = new JasperReportsViewResolver();
		reportResolver.setPrefix("classpath:reports/");
		reportResolver.setSuffix(".jasper");
		reportResolver.setReportDataKey("datasource");
		reportResolver.setViewNames("rpt_*");
		reportResolver.setViewClass(JasperReportsMultiFormatView.class);
		reportResolver.setOrder(0);
		return reportResolver;
	}

	@Bean
	public TemplateResolver templateResolver() {
		TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setOrder(1);
		log.debug("Template Resolver Executed");
		return templateResolver;
	}

	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		SpringSecurityDialect dialect = new SpringSecurityDialect();
		log.debug("Spring Security Dialect Executed");
		return dialect;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		Set<IDialect> dialects = new HashSet<IDialect>();
		dialects.add(springSecurityDialect());
		templateEngine.setAdditionalDialects(dialects);
		templateEngine.setTemplateResolver(templateResolver());
		log.debug("Template Engine Executed");
		return templateEngine;
	}

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());

		// viewResolver.setViewNames(new String[]{"*.html","*.xhtml"});
		log.debug("View Resolver Executed");
		return viewResolver;
	}

	/*
	 * @Bean public JedisConnectionFactory connectionFactory() { return new
	 * JedisConnectionFactory(); }
	 */
	
	@Bean(name = "filterMultipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    resolver.setMaxUploadSize(-1);
	    resolver.setMaxInMemorySize(4096);
	    return resolver;
	}

	public void addViewControllers(ViewControllerRegistry registry, Model model) {
		log.debug("Entering Login Page");
		model.addAttribute("users", new Users());
		registry.addViewController("/login").setViewName("login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		OpenEntityManagerInViewInterceptor interceptor = new OpenEntityManagerInViewInterceptor();
		interceptor.setEntityManagerFactory(entityManagerFactory);
		registry.addWebRequestInterceptor(interceptor);
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		final ReloadableResourceBundleMessageSource rb = new ReloadableResourceBundleMessageSource();
		rb.setBasename("classpath:validation");
		rb.setFallbackToSystemLocale(false);
		return rb;
	}
	
	/*@Bean
	public FirebaseMessaging firebaseMessaging() throws IOException {
		GoogleCredentials googleCredentials = GoogleCredentials
				.fromStream(new ClassPathResource("kenten-service-firebase-adminsdk.json").getInputStream());
		FirebaseOptions firebaseOptions = FirebaseOptions.builder()
				.setCredentials(googleCredentials)
				.setDatabaseUrl("https://kenten-service.firebaseio.com")
				.build();
		FirebaseApp app = null;
	    if(FirebaseApp.getApps().isEmpty()) {
	        app = FirebaseApp.initializeApp(firebaseOptions);
	    }else {
	        app = FirebaseApp.initializeApp(firebaseOptions);
	    }
		// FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions,
		// "kenten-service");
	   log.error("Firebase Configration Done: " + FirebaseMessaging.getInstance(app));
		return FirebaseMessaging.getInstance(app);
	}*/
}
