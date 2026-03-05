package br.com.alura.screenmatch.services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import br.com.alura.screenmatch.modelos.Titulo;

public class GeradorArquivo {

    public static void gerarArquivo(String nomeArquivo, List<Titulo> listaTitulos) {
        try {
            FileWriter escrita = new FileWriter(nomeArquivo);
            escrita.write(ConversorJson.converterListaTituloEmJson(listaTitulos));
            escrita.close();
        } catch (IOException e) {
            System.out.println("Erro ao gerar o arquivo: " + e.getMessage());
        }   
    }
}
