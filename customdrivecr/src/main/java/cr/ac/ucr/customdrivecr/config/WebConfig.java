package cr.ac.ucr.customdrivecr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AdminInterceptor adminInterceptor;
    private final ClienteInterceptor clienteInterceptor;

    public WebConfig(AdminInterceptor adminInterceptor, ClienteInterceptor clienteInterceptor) {
        this.adminInterceptor = adminInterceptor;
        this.clienteInterceptor = clienteInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**");

        registry.addInterceptor(clienteInterceptor)
                .addPathPatterns(
                        "/carrito/**",
                        "/pedidos/**",
                        "/mis-pedidos"
                );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}