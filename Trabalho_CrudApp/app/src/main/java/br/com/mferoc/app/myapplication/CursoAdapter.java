package br.com.mferoc.app.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoHolder> {

    private final List<Curso> cursos;
    //Curso curso;

    public CursoAdapter(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public void adicionarCurso(Curso curso) {
        cursos.add(curso);
        notifyItemInserted(getItemCount());
    }

    public void atualizarCurso(Curso curso) {
        cursos.set(cursos.indexOf(curso), curso);
        notifyItemChanged(cursos.indexOf(curso));
    }

    public void removerCurso(Curso curso) {
        int pos = cursos.indexOf(curso);
        cursos.remove(pos);
        notifyItemRemoved(pos);
    }

    private Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

    @Override
    public CursoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CursoHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(CursoHolder holder, final int position) {
        holder.nomeCurso.setText(cursos.get(position).getNome());

        holder.btnEditar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity(v);
                Intent intent = activity.getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("curso", cursos.get(position));
                activity.finish();
                activity.startActivity(intent);
            }
        });

        final Curso curso = cursos.get(position);
        holder.btnExcluir.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação")
                        .setMessage("Deseja realmente excluir?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                Curso curso = cursos.get(position);
                                CursoDAO dao = new CursoDAO(view.getContext());
                                boolean sucesso = dao.excluir(curso.getId());
                                if(sucesso) {
                                    removerCurso(curso);
                                    Snackbar.make(view, "Excluiu! :)", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                } else {
                                    Snackbar.make(view, "Ops! Erro! Não excluiu :(!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create()
                        .show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return cursos != null ? cursos.size() : 0;
    }
}





























