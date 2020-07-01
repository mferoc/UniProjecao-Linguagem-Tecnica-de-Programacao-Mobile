package br.com.mferoc.app.myapplication;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CursoHolder extends RecyclerView.ViewHolder {

    public TextView nomeCurso;
    public ImageButton btnEditar;
    public ImageButton btnExcluir;

    public CursoHolder(View itemView) {
        super(itemView);
        nomeCurso = itemView.findViewById(R.id.nomeCurso);
        btnEditar = itemView.findViewById(R.id.btnEdit);
        btnExcluir = itemView.findViewById(R.id.btnDelete);
    }
}
