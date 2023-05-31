package mz.ac.isutc.lecc31.mt2.notas_armazenamentointerno;

import java.io.Serializable;

public class Nota implements Serializable {
    private String titulo, conteudo;

    public Nota(String titulo, String conteudo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
