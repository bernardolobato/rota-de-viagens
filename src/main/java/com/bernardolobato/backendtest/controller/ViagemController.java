package com.bernardolobato.backendtest.controller;

import java.util.List;

import com.bernardolobato.backendtest.core.Rota;
import com.bernardolobato.backendtest.core.ViagemService;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.HttpMethod;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiParam;
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody;

public class ViagemController {

    @OpenApi(
        path = "/melhor-rota/:viagem",
        method = HttpMethod.GET,
        summary = "Devolve a melhor rota dada uma determinada viagem",
        pathParams = {
            @OpenApiParam(name = "viagem", description = "A viagem que desejamos consultar, no formato: ORG-DES. Por exemplo: GRU-CGD")
        }
    )
    public static void melhorRota(Context ctx) {
        try {
            ctx.use(ViagemService.class).setViagem(ctx.pathParam("viagem"));            
            ctx.json(new Object() {
                @SuppressWarnings("unused")
                public List<Rota> rotas = ctx.use(ViagemService.class).getMelhorRota();
                @SuppressWarnings("unused")
                public int custoTotal = ctx.use(ViagemService.class).getCustoTotal();
            });
        } catch(Exception e) {
            throw new BadRequestResponse(e.getMessage());
        }
    }


    @OpenApi(
        path = "/nova-rota",
        method = HttpMethod.POST,
        summary = "Adiciona nova rota",
        requestBody =  @OpenApiRequestBody(content = @OpenApiContent(from = Rota.class))
    )
    public static void novaRota(Context ctx) {
        Rota body = ctx.bodyValidator(Rota.class)
        .check(r -> r.getOrigem().length() == 3 && r.getOrigem() != null, "A origem deve ter 3 caracteres")
        .check(r -> r.getDestino().length() == 3 && r.getDestino() != null, "O destino deve ter 3 caracteres")
        .get();
        ctx.use(ViagemService.class).addRota(body);
    }
}