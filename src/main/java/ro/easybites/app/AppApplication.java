package ro.easybites.app;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppApplication {

//---------comment for local run
    @Value("${server.port.http}")
    private int serverPortHttp;

    @Value("${server.port}")
    private int serverPortHttps;
//-------------------------------


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AppApplication.class);
        app.run(args);
    }


//---------comment for local run
    @Bean
    public ServletWebServerFactory servletWebServerFactory(){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){

            @Override
            protected void postProcessContext(Context context){
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDANTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }

        };

        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnection());

        return tomcat;
    }

    private Connector httpToHttpsRedirectConnection() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setSecure(false);
        connector.setPort(serverPortHttp);
        connector.setRedirectPort(serverPortHttps);
        return connector;
    }
//-------------------------------

}
