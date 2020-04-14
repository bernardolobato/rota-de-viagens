package com.bernardolobato.backendtest.core;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.bernardolobato.backendtest.core.estrutura.Grafo;
import com.bernardolobato.backendtest.repositorio.ViagemRepositorio;

import kotlin.jvm.Throws;


public class ViagemService {
    private String origem;
    private String destino;
    private String route;
    private String repositorio;
    private Grafo grafoRotas;

    public ViagemService(String repositorio) {
        this.grafoRotas = new Grafo();
        this.carregaRotas(repositorio);
    }
    public ViagemService() {       
    }

    private void carregaRotas(String repositorio) {
        new ViagemRepositorio().carregaRotas(repositorio)
        .forEach(item -> this.grafoRotas.addAresta(
            item.getCusto(), 
            item.getOrigem(),
            item.getDestino())
        );
    }

    public void setViagem(String info) throws Exception {
        String[] parsed = info.split("-");
        if (this.grafoRotas.acharVertice(parsed[0]) == null){
            throw new Exception("A Origem informada nao existe nas rotas carregadas");
        }
        if (this.grafoRotas.acharVertice(parsed[1]) == null) {
            throw new Exception("O destino informado nao existe nas rotas carregadas");
        }
        this.origem = parsed[0];
        this.destino = parsed[1];

    }

    public String getOrigem() {
        return this.origem;
    }

    public Grafo getGrafoRotas() {
        return this.grafoRotas;
    }

   
    public String getDestino() {
        return this.destino;
    }

   
    public List<Rota> getMelhorRota() {
        return this.grafoRotas.melhorCaminhoDijkstra(this.origem, this.destino).getArestas()
            .stream().map(aresta->{
                return new Rota(aresta.getOrigem().getNome(), aresta.getDestino().getNome(), aresta.getPeso());
            }).collect(Collectors.toList());
    }

    public int getCustoTotal() {
       
        int sum = this.getMelhorRota().stream().reduce(0,
                (s, b) -> s + b.getCusto(),
                Integer::sum);
            return sum;
    }

    public void addRota(Rota rota) {
        new ViagemRepositorio().addRota(rota, repositorio);
    }

   
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ViagemService)) {
            return false;
        }
        ViagemService rotaDeViagem = (ViagemService) o;
        return Objects.equals(origem, rotaDeViagem.origem) && Objects.equals(destino, rotaDeViagem.destino) && Objects.equals(route, rotaDeViagem.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origem, destino, route);
    }

    @Override
    public String toString() {
        return "{" +
            " origem='" + getOrigem() + "'" +
            ", destino='" + getDestino() + "'" +
            ", route='" + getMelhorRota() + "'" +
            "}";
    }
}