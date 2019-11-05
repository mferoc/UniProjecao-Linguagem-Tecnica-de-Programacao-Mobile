package br.com.mferoc.app.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CursoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Verifica se está adicionando ou editando
        Intent intent = getIntent();
        if (intent.hasExtra("curso")) {
            findViewById(R.id.includemain).setVisibility(View.INVISIBLE);
            findViewById(R.id.includecadastro).setVisibility(View.VISIBLE);
            findViewById(R.id.fab).setVisibility(View.INVISIBLE);
            cursoEditado = (Curso) intent.getSerializableExtra("curso");
            EditText txtNome = findViewById(R.id.txtNome);
            EditText txtCategoria = findViewById(R.id.txtCategoria);
            EditText txtCargaH = findViewById(R.id.txtCargaH);

            txtNome.setText(cursoEditado.getNome());
            txtCategoria.setText(cursoEditado.getCategoria());
            txtCargaH.setText(cursoEditado.getCarga());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.includemain).setVisibility(View.INVISIBLE);
                findViewById(R.id.includecadastro).setVisibility(View.VISIBLE);
                findViewById(R.id.fab).setVisibility(View.INVISIBLE);
            }
        });

        Button btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText txtNome = findViewById(R.id.txtNome);
                EditText txtCategoria = findViewById(R.id.txtCategoria);
                EditText txtCargaH = findViewById(R.id.txtCargaH);

                findViewById(R.id.includemain).setVisibility(View.VISIBLE);
                findViewById(R.id.includecadastro).setVisibility(View.INVISIBLE);
                findViewById(R.id.fab).setVisibility(View.VISIBLE);

                txtNome.setText("");
                txtCategoria.setText("");
                txtCargaH.setText("");

                if(cursoEditado != null) {
                    cursoEditado  = null;
                }
            }
        });

        Button btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtNome = findViewById(R.id.txtNome);
                EditText txtCategoria = findViewById(R.id.txtCategoria);
                EditText txtCargaH = findViewById(R.id.txtCargaH);

                String nome = txtNome.getText().toString();
                String categoria = txtCategoria.getText().toString();
                String cargaH = txtCargaH.getText().toString();

                CursoDAO dao = new CursoDAO(getBaseContext());
                boolean salvou;
                if (cursoEditado != null)
                    salvou = dao.salvar(cursoEditado.getId(), nome, categoria, cargaH);
                else
                    salvou = dao.salvar(nome, categoria, cargaH);

                if (salvou) {
                    //Adicionando ao adapter
                    Curso curso = dao.retornarUltimo();
                    if (cursoEditado != null) {
                        adapter.atualizarCurso(curso);
                        cursoEditado = null;
                        Snackbar.make(view, "Oba! Curso editado! :)", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        adapter.adicionarCurso(curso);
                        Snackbar.make(view, "Oba! Curso salvo! :)", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                    //limpando campos
                    txtNome.setText("");
                    txtCategoria.setText("");
                    txtCargaH.setText("");

                    findViewById(R.id.includemain).setVisibility(View.VISIBLE);
                    findViewById(R.id.includecadastro).setVisibility(View.INVISIBLE);
                    findViewById(R.id.fab).setVisibility(View.VISIBLE);
                } else {
                    Snackbar.make(view, "Ops! Erro! Os dados não foram gravados :(", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        configurarRecycler();
    }


    private void configurarRecycler() {
        //Configurando gerenciador de layout pra ser uma lista
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Adicionando adapter para colocar os objetos na lista
        CursoDAO dao = new CursoDAO(this);
        adapter = new CursoAdapter(dao.retornaTodos());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    Curso cursoEditado = null;
    private int getIndex(Spinner spinner, String str) {
        int index = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(str)){
                index = i;
                break;
            }
        }

        return index;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
