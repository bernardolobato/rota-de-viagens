package com.bernardolobato.backendtest;

import static org.junit.Assert.assertTrue;

import com.bernardolobato.backendtest.core.estrutura.Grafo;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
 public class AppTest {

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
        //this.grafo.imprimeArvore();
    }

    @Test
    public void shouldAnswerWithTrue() {
        Grafo resultado = this.grafo.melhorCaminhoDijkstra("GRU", "SCL");
        assertTrue( true );
    }
}
