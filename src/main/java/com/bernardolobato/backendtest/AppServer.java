package com.bernardolobato.backendtest;

import com.bernardolobato.backendtest.core.ViagemService;
import com.bernardolobato.backendtest.controller.ViagemController;

import io.javalin.Javalin;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;

public class AppServer {
    public static void main(String[] args) {

        ViagemService v = new ViagemService(args[0]);
        Javalin app = criaServidor();

        app.before(ctx->{
            ctx.register(ViagemService.class, v);
        });

       app.get("/melhor-rota/:viagem", ViagemController::melhorRota);
       app.post("/nova-rota", ViagemController::novaRota);

       app.start(7000);
    }

    private static Javalin criaServidor() {
        Info applicationInfo = new Info()
        .version("1.0")
        .description("Melhor rota de Viagem");

        return Javalin.create(config -> {
            config.registerPlugin(new OpenApiPlugin(new OpenApiOptions(applicationInfo)
            .path("/swagger-docs")
            .swagger(new SwaggerOptions("/swagger-ui"))
            .activateAnnotationScanningFor("com.bernardolobato.backendtest.controller")
          )
        );
        });
    }
}