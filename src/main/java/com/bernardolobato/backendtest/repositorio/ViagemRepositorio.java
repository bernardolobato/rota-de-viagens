package com.bernardolobato.backendtest.repositorio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.bernardolobato.backendtest.core.Rota;

import org.eclipse.jetty.util.Scanner;

public class ViagemRepositorio {
    String csvDivisor = ",";

    public ViagemRepositorio() {

    }

    public void addRota(Rota rota, String repositorio) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(repositorio));
            String lr = br.readLine();
            String nl = "";
            if (lr!=null) {
                nl = "\n";
            }
            FileWriter arq = new FileWriter(repositorio, true);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.print(nl+rota.getOrigem() + csvDivisor+rota.getDestino() + csvDivisor + rota.getCusto());
            arq.close();
        } catch (Exception e) {
            e.printStackTrace();
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

    public List<Rota> carregaRotas(String repositorio) {
        BufferedReader br = null;
        String linha = "";
        List<Rota> rotas = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(repositorio));
            while ((linha = br.readLine()) != null) {
                String[] rota = linha.split(csvDivisor);
                rotas.add(new Rota(rota[0], rota[1], Integer.parseInt(rota[2])));
            }
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
        return rotas;
    } 
}