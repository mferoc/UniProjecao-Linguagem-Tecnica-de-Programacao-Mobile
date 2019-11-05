package br.com.mferoc.app.myapplication;

import android.content.Context;

import java.io.Serializable;

public class Curso implements Serializable {

    private int id;
    private String nome;
    private String categoria;
    private String carga;

    public Curso(int id, String nome, String categoria, String carga) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.carga = carga;
    }


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCarga() {
        return carga;
    }

    @Override
    public boolean equals(Object o){
        return this.id == ((Curso)o).id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
