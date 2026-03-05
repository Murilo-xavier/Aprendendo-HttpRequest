package br.com.alura.screenmatch.services;

import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;

public class ConversorJson {
    
    private static Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create(); 

    public static TituloOmdb converterJsonEmTituloOmdb(String json) {
        return gson.fromJson(json, TituloOmdb.class);
    }

    public static Titulo converterJsonEmTitulo(String json) {
        TituloOmdb meuTituloOmdb = converterJsonEmTituloOmdb(json);
        return new Titulo(meuTituloOmdb);
    }
    public static String converterListaTituloEmJson(List<Titulo> listaTitulos) {
        return gson.toJson(listaTitulos);
    }
    //TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
}
