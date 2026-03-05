package br.com.alura.screenmatch.principal;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import br.com.alura.screenmatch.services.ConversorJson;
import br.com.alura.screenmatch.services.GeradorArquivo;
import br.com.alura.screenmatch.services.HttpService;
import br.com.alura.screenmatch.services.InteracaoUsuario;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        InteracaoUsuario interacaoUsuario = new InteracaoUsuario();
        List<Titulo> listaTitulos = new ArrayList<>();
        

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

        GeradorArquivo.gerarArquivo("filmes.json", listaTitulos);
        System.out.println("--- FIM ---");
    }
}
