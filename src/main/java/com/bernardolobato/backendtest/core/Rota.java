package com.bernardolobato.backendtest.core;

public class Rota {
    private String origem;
    private String destino;
    private Integer custo; 
    
    public Rota(String origem, String destino, int custo) {
        this.origem = origem;
        this.destino = destino;
        this.custo = custo;
    }

    public Rota() {

    }

    /**
     * @return String return the origem
     */
    public String getOrigem() {
        return origem;
    }

    /**
     * @return String return the destino
     */
    public String getDestino() {
        return destino;
    }

    
    public Integer getCusto() {
        return custo;
    }

    @Override
    public String toString() {
        return getOrigem() + " -> " + getDestino() + " | " + this.getCusto();
    }
}