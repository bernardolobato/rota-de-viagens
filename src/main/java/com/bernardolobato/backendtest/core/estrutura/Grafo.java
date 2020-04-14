package com.bernardolobato.backendtest.core.estrutura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo {

	private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
	private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
	
	public void addAresta(int peso, String origem, String destino) {
		int i,j,k;
		
		i = this.addVertice(origem);
		j = this.addVertice(destino);
		
		Aresta a = new Aresta(peso,
				this.vertices.get(i),
				this.vertices.get(j));
		
		this.arestas.add(a);
		k = this.arestas.size();
		
		//adiciona aresta na lista de arestas incidentes em cada vertice
		this.vertices.get(i).addIncidentes(this.arestas.get(k-1));
		this.vertices.get(j).addIncidentes(this.arestas.get(k-1));
	}
			
	public int addVertice(String nome){
		int i= this.posicaoVertice(nome); 
		
		if(i==this.vertices.size()){
			this.vertices.add(new Vertice(nome));
			return (this.vertices.size() - 1);
		}		
		return i;
	}
	
	public ArrayList<Vertice> getVertices() {
		return vertices;
	}
	
	public int posicaoVertice(String nome){
		int i;
		
		for (i=0; i<this.vertices.size() ; i++) {
			if (this.vertices.get(i).getNome().equals(nome)){
				return i;
			}
		}
		
		//se nao encontrar retorna o tamanho da lista vertices
		return this.vertices.size();
	}
	
	public Vertice acharVertice(String nome){
		int p = this.posicaoVertice(nome);
		if (p == this.vertices.size()) {
			return null;
		}
		return this.vertices.get(this.posicaoVertice(nome));
	}
	
	public Aresta acharAresta(Vertice vet1, Vertice vet2){
		for(int i=0; i<this.arestas.size();i++){
			if( ((this.arestas.get(i).getOrigem().getNome().equals(vet1.getNome())) &&
				(this.arestas.get(i).getDestino().getNome().equals(vet2.getNome()))) ||
				((this.arestas.get(i).getOrigem().getNome().equals(vet2.getNome())) &&
				(this.arestas.get(i).getDestino().getNome().equals(vet1.getNome()))) ){
				return this.arestas.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Aresta> getArestas() {
		return arestas;
	}
	
	public Grafo 
	melhorCaminhoDijkstra(String nomeV1, String nomeV2) {
    	ArrayList<Vertice> menorCaminho = new ArrayList<>();
		ArrayList<Vertice> naoVisitados = new ArrayList<Vertice>();

		Vertice v1 = this.acharVertice(nomeV1);
		Vertice v2 = this.acharVertice(nomeV2);
		
		menorCaminho.add(v1);
		this.getVertices().forEach(item-> {
			if (item.getNome().equals(v1.getNome())) {
				item.setDistancia(0);
			}
		    else {
				item .setDistancia(9999);
			}
			naoVisitados.add(item);
		});

		Collections.sort(naoVisitados);

        while (!naoVisitados.isEmpty()) {
			Vertice atual = naoVisitados.get(0);

			atual.getVizinhos().forEach(vizinho -> {
				Aresta arestaAtual;
                Vertice verticeCaminho;

                if (!vizinho.isVisitado()) {
                	arestaAtual = this.acharAresta(atual,vizinho);
                    if (vizinho.getDistancia() > (atual.getDistancia() + arestaAtual.getPeso())) {
                        vizinho.setDistancia(atual.getDistancia()
                                        + arestaAtual.getPeso());
                        vizinho.setPai(atual);
                        if (vizinho.equals(v2)) {
                            menorCaminho.clear();
                            verticeCaminho = vizinho;
							menorCaminho.add(vizinho);
							
                            while (verticeCaminho.getPai() != null) {
                                menorCaminho.add(verticeCaminho.getPai());
                                verticeCaminho = verticeCaminho.getPai();
                            }
                            Collections.sort(menorCaminho);
                        }
                    }
                }
			});
            atual.setVisitado(true);
            naoVisitados.remove(atual);

            Collections.sort(naoVisitados);
		}		
        return this.montaRetorno(menorCaminho);
	}

	private Grafo montaRetorno(List<Vertice> menorCaminho) {
		Grafo result = new Grafo();
		this.vertices.forEach(item->item.setVisitado(false));
		menorCaminho.forEach(item-> {
            if (item.getPai() != null) {
                result.arestas.add(this.acharAresta(item, item.getPai()));
            }
		});
		return result;
	}
}