package com.bernardolobato.backendtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;

import com.bernardolobato.backendtest.core.Rota;
import com.bernardolobato.backendtest.core.ViagemService;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
 public class ViagemServiceTest {

    @Test
    public void devePreencherOrigem() {
        ViagemService rota = new ViagemService();
        rota.setViagem("ABC-DEF");
        assertTrue(rota.getOrigem().equals("ABC"));
    }
    @Test
    public void devePReencherDestino() {
        ViagemService rota = new ViagemService();
        rota.setViagem("ABC-DEF");
        assertTrue(rota.getDestino().equals("DEF"));
    }
    @Test(expected = Exception.class)
    public void deveLancaExceptionQuandoStringEstaIncorreta() {
        new ViagemService("").setViagem("abcdef");;
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenFileNotExists() {
        new ViagemService("ABCDEF");
    }

    @Test
    public void shouldLoadRoutes() {
        ViagemService vs = new ViagemService("src/test/resources/com/bernardolobato/backendtest/rotas.csv");
        assertEquals(7, vs.getGrafoRotas().getArestas().size());
        assertEquals(5, vs.getGrafoRotas().getVertices().size());
    }

    @Test
    public void shouldCalcRoute() {
        ViagemService rota = new ViagemService("src/test/resources/com/bernardolobato/backendtest/rotas.csv");
        rota.setViagem("GRU-CDG");
        List<Rota> rotas = rota.getMelhorRota();
        rotas = rota.getMelhorRota();
        assertEquals(rotas.size(), 4);
        assertEquals(rota.getCustoTotal(), 40);
    }

}
