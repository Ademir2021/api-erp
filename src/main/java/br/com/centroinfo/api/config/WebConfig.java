package br.com.centroinfo.api.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.images-path:./imgs/items}")
    private String imagesPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        Path path = Paths.get(imagesPath)
                .toAbsolutePath()
                .normalize();

        System.out.println("Pasta de imagens: " + path);

        registry
                .addResourceHandler("/imgs/items/**")
                .addResourceLocations(path.toUri().toString());
    }
}
