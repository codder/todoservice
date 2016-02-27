package dev.codder.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Created by Daniil on 2/25/2016.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "dev.codder")
public class TODOSpringConfiguration{
    @Bean
    public UrlBasedViewResolver urlBasedViewResolver()
    {
        UrlBasedViewResolver res = new InternalResourceViewResolver();
        res.setViewClass(JstlView.class);
        res.setPrefix("/WEB-INF/pages/");
        res.setSuffix(".jsp");

        return res;
    }

}
