package fa.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class EmailTemplateConfig {

	@Bean
	public ITemplateResolver thymeleafClassLoaderTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}
	
	public SpringTemplateEngine thymeleafTemplateEngine()
	{
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(thymeleafClassLoaderTemplateResolver());
		return templateEngine;
	}
}
