package br.com.alura.screenmatch.services;

import java.util.Scanner;

public class InteracaoUsuario {
    private Scanner sc = new Scanner(System.in);
    private String busca = "";

    public String solicitarBusca() {
        System.out.println("Digite um filme para busca: ");
        busca = sc.nextLine();
        return busca;
    }

    public String getBusca() {
        return busca;
    }
}
