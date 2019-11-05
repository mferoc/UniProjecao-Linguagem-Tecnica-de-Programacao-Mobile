package br.com.mferoc.app.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    private final String TABLE_CURSOS = "cursos";
    private DbGateway gw;

    public CursoDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public boolean salvar(String nome, String categoria, String carga) {

        return salvar(0, nome, categoria, carga) ;
    }

    public boolean salvar(int id, String nome, String categoria, String carga) {

        //Inserir utilizando boas práticas recomendadas pela documentação do Android
        ContentValues cv = new ContentValues();
        cv.put("nome", nome);
        cv.put("categoria", categoria);
        cv.put("carga", carga);

        if (id > 0)
            return gw.getDatabase().update(TABLE_CURSOS, cv, "id=?", new String[]{ id + "" }) > 0;
        else
            return gw.getDatabase().insert(TABLE_CURSOS, null, cv) > 0;
    }

    public List<Curso> retornaTodos() {
        List<Curso> cursos = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM cursos", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String categoria = cursor.getString(cursor.getColumnIndex("categoria"));
            String cargaH = cursor.getString(cursor.getColumnIndex("carga"));

            cursos.add(new Curso(id, nome, categoria, cargaH));
        }
        cursor.close();
        return cursos;
    }

    public Curso retornarUltimo() {
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM cursos ORDER BY id DESC", null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String categoria = cursor.getString(cursor.getColumnIndex("categoria"));
            String cargaH = cursor.getString(cursor.getColumnIndex("carga"));
            cursor.close();
            return new Curso(id, nome, categoria, cargaH);
        }

        return null;
    }

    public boolean excluir(int id) {
        return gw.getDatabase().delete(TABLE_CURSOS, "id=?", new String[]{ id + "" }) > 0;
    }
}
