package fa.training.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import fa.training.filter.CustomRestTemplateCustomizer;

@Configuration
@EnableAutoConfiguration
@ComponentScan("fa.training")
public class SpringConfig {

    @Bean
    @Qualifier("customRestTemplateCustomizer")
    CustomRestTemplateCustomizer customRestTemplateCustomizer() {
        return new CustomRestTemplateCustomizer();
    }

    @Bean
    @DependsOn(value = {"customRestTemplateCustomizer"})
    RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder(customRestTemplateCustomizer());
    }

}

