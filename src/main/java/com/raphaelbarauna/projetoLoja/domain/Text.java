package com.raphaelbarauna.projetoLoja.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Text implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String texto;
    private String language;
    private String complement;


    public Text() {
    }

    public Text(String texto, String language, String complement) {
        this.texto = texto;
        this.language = language;
        this.complement = complement;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    @Override
    public String toString() {
        return "Text{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", language='" + language + '\'' +
                ", complement='" + complement + '\'' +
                '}';
    }
}
