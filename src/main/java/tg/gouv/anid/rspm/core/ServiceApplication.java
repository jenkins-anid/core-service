package tg.gouv.anid.rspm.core;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import tg.gouv.anid.rspm.core.config.CustomMessageSource;

import java.util.Locale;

@SpringBootApplication(scanBasePackages = "tg.gouv.anid")
@EntityScan(basePackages = "tg.gouv.anid")
@RefreshScope
@EnableJpaRepositories(basePackages = "tg.gouv.anid",repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@EnableDiscoveryClient
@EnableFeignClients("tg.gouv.anid.rspm.core.client.openfeign")
@Slf4j
public class ServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext app = new SpringApplicationBuilder(
				ServiceApplication.class)
				.build().run(args);
		Environment env = app.getEnvironment();
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		log.info("""
            
            ----------------------------------------------------------
            Application  '{}' is running!
            Access URLs:
            Local:        {}://localhost:{}
            Profile(s):   {}
            ----------------------------------------------------------
            """,
				env.getProperty("spring.application.name"),
				protocol,
				env.getProperty("server.port"),
				env.getActiveProfiles());
	}

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title("RSPM | Service principal")
						.description("Registre social des personnes et ménages")
						.version("1.0.0"));
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.FRANCE);
		return slr;
	}

	@Bean
	public MessageSource messageSource() {
		CustomMessageSource messageSource
				= new CustomMessageSource();
		messageSource.setBasename("classpath:i18n/rspm-core-service/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}
