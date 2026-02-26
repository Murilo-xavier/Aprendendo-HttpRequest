package br.com.alura.screenmatch.excecao;

public class ErroDeConversaoDeAnoException extends RuntimeException {

    private String menssage;

    public ErroDeConversaoDeAnoException(String menssagem) {
        this.menssage = menssagem;
    }

    @Override
    public String getMessage() {
        return menssage;
    }
}
