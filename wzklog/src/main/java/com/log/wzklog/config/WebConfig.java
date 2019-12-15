package com.log.wzklog.config;

        import com.log.wzklog.intercepors.LoginInterceptor;
        import org.springframework.boot.web.server.ErrorPage;
        import org.springframework.boot.web.server.ErrorPageRegistrar;
        import org.springframework.boot.web.server.ErrorPageRegistry;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.http.HttpStatus;
        import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
        import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer, ErrorPageRegistrar {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //,"/src/**","/docs/**","/production/js/**","/production/images/**","/production/css/maps/**"
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/phone","/forget",
                "/noteCheck","/createUser","/userLogin","/build/**","/vendors/**"
        ,"/front","/read/**","/img");

    }

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = new ErrorPage[2];
        errorPages[0] = new ErrorPage(HttpStatus.NOT_FOUND, "/400.html");
        errorPages[1] = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
        registry.addErrorPages(errorPages);

    }
}
