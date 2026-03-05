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
import br.com.alura.screenmatch.services.InteracaoUsuario;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        InteracaoUsuario interacaoUsuario = new InteracaoUsuario();
        List<Titulo> listaTitulos = new ArrayList<>();
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();

        while (!interacaoUsuario.getBusca().equalsIgnoreCase("sair")) {
            interacaoUsuario.solicitarBusca();

            if (interacaoUsuario.getBusca().equalsIgnoreCase("sair")) {
                break;
            }
            String json = HttpService.buscarDadosString(interacaoUsuario.getBusca());

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
    }
}
