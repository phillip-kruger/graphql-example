package com.github.phillipkruger.user.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@ApplicationPath("/rest")
@OpenAPIDefinition(
        info = @Info(
                title = "Profile Service", 
                version = "1.0.0",
                contact = 
                        @Contact(
                                name = "Phillip Kruger", 
                                email = "phillip.kruger@phillip-kruger.com",
                                url = "http://www.phillip-kruger.com")
                ),servers = @Server(url = "/",description = "Localhost")
        )
public class ApplicationConfig extends Application {

}
