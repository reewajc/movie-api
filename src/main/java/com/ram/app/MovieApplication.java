package com.ram.app;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/api")
@OpenAPIDefinition(
        info = @Info(
                title = "Movie API",
                version = "1.0.0",
                description = "API for managing movies and actors from the Sakila database",
                contact = @Contact(
                        name = "Movie API Support",
                        email = "support@movieapi.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        tags = {
                @Tag(name = "Film Resource", description = "Operations related to films"),
                @Tag(name = "Actor Resource", description = "Operations related to actors")
        }
)
public class MovieApplication extends Application {
    // This class is intentionally empty. It's used to configure JAX-RS and OpenAPI
}