package com.bernardolobato.backendtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.bernardolobato.backendtest.core.Rota;
import com.bernardolobato.backendtest.repositorio.ViagemRepositorio;

import org.junit.Test;

public class ViagemRepositorioTest {

    @Test
    public void deveCarregarListaDeRotas() {
        ViagemRepositorio repo = new ViagemRepositorio();
        List<Rota> rotas = repo.carregaRotas("src/test/resources/com/bernardolobato/backendtest/rotas.csv");
        assertTrue(rotas.size() == 7);
        assertTrue(rotas.get(0).getOrigem().equals("GRU"));
        assertTrue(rotas.get(0).getDestino().equals("BRC"));
        assertTrue(rotas.get(0).getCusto().equals(10));
    }

    @Test
    public void deveInserirUmaNovaRota() {
        ViagemRepositorio repo = new ViagemRepositorio();
        String nomeArquivo = "rotaTmp.csv";
        this.criaNovoArquivo(nomeArquivo);

        Rota r = new Rota("ABC", "DEF", 1);
        repo.addRota(r, "rotaTmp.csv");
        String linha = this.carregaPrimeiraLinhaDoArquivo(nomeArquivo);
        
        assertEquals("ABC,DEF,1", linha);
    }

    private String carregaPrimeiraLinhaDoArquivo(String nome) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(nome));
            return br.readLine();
        } catch (Exception e) {
            throw new RuntimeException("Arquivo não localizado");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void criaNovoArquivo(String nome) {
        File file = new File(nome);        
        try{
            //para criar o arquivo
            if (file.exists()) {
                file.delete();
            }
           file.createNewFile();
        
        }catch(IOException io){
            io.printStackTrace();
        }
    }

}