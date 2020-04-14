package com.bernardolobato.backendtest;

import java.util.List;
import java.util.Scanner;

import com.bernardolobato.backendtest.core.Rota;
import com.bernardolobato.backendtest.core.ViagemService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
            ViagemService viagem = new ViagemService(args[0]);
            Scanner s = new Scanner(System.in);
            while(true) {
                System.out.println("Please enter the route (type 'exit' to quit): ");
                String route = s.next();
                if (route.equals("exit")) {
                    s.close();
                    System.exit(0);
                }
                viagem.setViagem(route);
                List<Rota> rotas = viagem.getMelhorRota();
                rotas.forEach(r->{
                    System.out.println(r.toString());
                });
                System.out.println("Total Cost: " + viagem.getCustoTotal());
            }
        }
}
