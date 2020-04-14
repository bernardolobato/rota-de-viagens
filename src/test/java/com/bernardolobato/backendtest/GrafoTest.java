package com.bernardolobato.backendtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.bernardolobato.backendtest.core.estrutura.Aresta;
import com.bernardolobato.backendtest.core.estrutura.Grafo;
import com.bernardolobato.backendtest.core.estrutura.Vertice;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
 public class GrafoTest {

    Grafo grafo;

    @Before
    public void loadData() {
        this.grafo = new Grafo();
        this.grafo.addAresta(10, "GRU", "BRC");
        this.grafo.addAresta(10, "BRC", "GRU");
        this.grafo.addAresta(20, "GRU", "SCL");
        this.grafo.addAresta(75, "GRU", "CDG");
        this.grafo.addAresta(56, "GRU", "ORL");
        this.grafo.addAresta(5,  "BRC", "SCL");
        this.grafo.addAresta(20, "SCL", "ORL");
        this.grafo.addAresta(5,  "ORL", "CDG");
    }

    @Test
    public void deveRetornarOMelhorCaminho() {
        Grafo resultado = this.grafo.melhorCaminhoDijkstra("GRU", "CDG");
        assertEquals(4, resultado.getArestas().size());
        assertEquals("BRC", resultado.getArestas().get(0).getDestino().getNome());
        assertEquals("SCL", resultado.getArestas().get(1).getDestino().getNome());
        assertEquals("ORL", resultado.getArestas().get(2).getDestino().getNome());
        assertEquals("CDG", resultado.getArestas().get(3).getDestino().getNome());
    }
    
    @Test
    public void deveAcharAresta() {
        this.grafo.addAresta(1,"111", "222");
        Aresta a = this.grafo.acharAresta(new Vertice("111"), new Vertice("222"));
        assertNotNull(a);
    }

    @Test
    public void deveRetornarNullQuandoArestaNaoExiste() {
        this.grafo.addAresta(1,"111", "222");
        Aresta a = this.grafo.acharAresta(new Vertice("aaa"), new Vertice("222"));
        assertNull(a);
    }

    @Test
    public void deveAdicionarVertice() {
        int index = this.grafo.addVertice("111");
        assertEquals(5, index);
    }

    @Test
    public void deveRetornarIndiceDeVerticeJaCadastrado() {
        int index = this.grafo.addVertice("GRU");
        assertEquals(index,0);
    }

    @Test
    public void deveAcharVertice() {
        Vertice v = this.grafo.acharVertice("GRU");
        assertEquals(v.getNome(), "GRU");
    }

    @Test
    public void deveRetornarNullSeVerticeNaoExiste() {
        Vertice v = this.grafo.acharVertice("111");
        assertNull(v);
    }

}
