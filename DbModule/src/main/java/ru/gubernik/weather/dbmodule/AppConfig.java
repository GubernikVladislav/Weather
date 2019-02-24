package ru.gubernik.weather.dbmodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import ru.gubernik.weather.dbmodule.jms.JmsReceiver;
import ru.gubernik.weather.dbserviceapi.service.GetWeatherService;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.persistence.EntityManagerFactory;

/**
 * Файл конфигурации Spring
 */
@Configuration
@ComponentScan(basePackages = {"ru.gubernik.weather.dbmodule"})
@EnableJms
@EnableTransactionManagement
public class AppConfig {

    @Autowired
    private GetWeatherService getWeatherService;

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:jboss/exported/jms/queue/weather")
    private Queue queue;

    @Bean(name = "/WeatherService")
    public HessianServiceExporter hessianServiceExporter(){
        HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
        hessianServiceExporter.setService(getWeatherService);
        hessianServiceExporter.setServiceInterface(GetWeatherService.class);
        return hessianServiceExporter;
    }

    @Bean
    public JmsReceiver receiver(){
        return new JmsReceiver();
    }

    @Bean
    public DefaultMessageListenerContainer listenerContainerFactory() {
        DefaultMessageListenerContainer factory = new DefaultMessageListenerContainer();
        System.out.println("*************************************************************" + connectionFactory);
        factory.setConnectionFactory(connectionFactory);
        factory.setDestination(queue);
        factory.setMessageListener(receiver());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JtaTransactionManager();
    }
}
