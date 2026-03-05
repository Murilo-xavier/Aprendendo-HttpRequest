package br.com.alura.screenmatch.principal;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import br.com.alura.screenmatch.services.ConversorJson;
import br.com.alura.screenmatch.services.HttpService;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        String busca = "";
        List<Titulo> listaTitulos = new ArrayList<>();
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();

        while (!busca.equalsIgnoreCase("sair")) {

            System.out.println("Digite um filme para busca: ");
            busca = sc.nextLine();

            if (busca.equalsIgnoreCase("sair")) {
                break;
            }


            String json = HttpService.buscarDadosString(busca);
            //System.out.println(json); // teste para verificar o json retornado da API

            // Titulo meuTitulo = gson.fromJson(json, Titulo.class);
            TituloOmdb meuTituloOmdb = ConversorJson.converterJsonEmTituloOmdb(json);
            //System.out.println("\n-- MEU TITULO OMDB: " + meuTituloOmdb); // teste para verificar o objeto criado a partir do json

            try {
                Titulo meuTitulo = ConversorJson.converterJsonEmTitulo(json);
                listaTitulos.add(meuTitulo);

            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro no argumento da busca: " + e.getMessage());
            }
        }

        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(listaTitulos));
        escrita.close();
        System.out.println("--- FIM ---");
        sc.close();
    }
}
