package br.com.senac.moduloTI.Configuration;

import br.com.senac.moduloTI.Controller.ChamadoController;
import br.com.senac.moduloTI.Controller.ControllerActiver;
import br.com.senac.moduloTI.Controller.OrdemServicoController;
import br.com.senac.moduloTI.Entity.SessionAtribute;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.mail.MailSender;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

/**
 *
 * @author Darlan Silva
 */
@SpringBootConfiguration
@ComponentScan(basePackageClasses = {ChamadoController.class, OrdemServicoController.class, SessionAtribute.class, ControllerActiver.class})
@EnableCaching
public class AppWebConfiguration implements WebMvcConfigurer {

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    } 

    @Bean
    public CacheManager cacheManager() {
        CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5,
                TimeUnit.MINUTES);
        GuavaCacheManager manager = new GuavaCacheManager();
        manager.setCacheBuilder(builder);
        return manager;
    }

    @Bean
    public MailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setUsername("Tech.ModeBR@gmail.com");
        mailSender.setPassword("t5e3c3h6m7o9d3e3");
        mailSender.setPort(587);

        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.smtp.auth", "true");
        mailProperties.setProperty("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(mailProperties);

        return mailSender;
    }

}
