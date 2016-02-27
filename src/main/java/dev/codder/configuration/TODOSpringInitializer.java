package dev.codder.configuration;

import dev.codder.filters.CORSFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.Filter;

/**
 * Created by Daniil on 2/25/2016.
 */

public class TODOSpringInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ TODOSpringConfiguration.class };
    }


    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ WebConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        Filter[] singleton = { new CORSFilter()};
        return singleton;
    }



}
