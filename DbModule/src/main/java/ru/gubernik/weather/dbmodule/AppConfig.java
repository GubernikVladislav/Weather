package ru.gubernik.weather.dbmodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import ru.gubernik.weather.dbserviceapi.service.RemoteProxy;

@Configuration
@ComponentScan(basePackages = {"ru.gubernik.weather.dbmodule"})
public class AppConfig {

    @Autowired
    private RemoteProxy remoteProxy;

    @Bean(name = "/WeatherService")
    public HessianServiceExporter hessianServiceExporter(){
        HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
        hessianServiceExporter.setService(remoteProxy);
        hessianServiceExporter.setServiceInterface(RemoteProxy.class);
        return hessianServiceExporter;
    }

}
