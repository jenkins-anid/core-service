package tg.gouv.anid.rspm.core;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "tg.gouv.anid")
@EntityScan(basePackages = "tg.gouv.anid")
@RefreshScope
@EnableJpaRepositories(basePackages = "tg.gouv.anid",repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@EnableDiscoveryClient
@EnableFeignClients("tg.gouv.anid.rspm.core.client.openfeign")
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
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
}
