package br.com.lessons.formproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvLinguagem, tvFLuente;
    Button btEnviar;
    EditText etNome, etEmail;
    CheckBox cbJava, cbC, cbJs;
    RadioButton rbS, rbN;

    ArrayList<String> linguagem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLinguagem = findViewById(R.id.tvLinguagem);
        tvFLuente = findViewById(R.id.tvFluente);
        btEnviar = findViewById(R.id.btEnviar);
        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        cbJava = findViewById(R.id.cbJava);
        cbC = findViewById(R.id.cbC);
        cbJs = findViewById(R.id.cbJavascript);
        rbS = findViewById(R.id.rbSim);
        rbN = findViewById(R.id.rbNao);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acao(view);
            }
        });
    }

    public void acao(View view) {

        linguagem.clear();

        String nome = "Nome: " + etNome.getText().toString() + "\n";
        String email = "Email: " + etEmail.getText().toString() + "\n";
        String linguagemPreferida = "Linguagens prediletas: \n";
        if (cbJava.isChecked())
            linguagem.add("Java");
        if (cbC.isChecked())
            linguagem.add(", C");
        if (cbJs.isChecked())
            linguagem.add(", Javascript");

        int i;
        for (i = 0; i < 3; i++) {
            if (linguagem.get(i).length() > 0) {
                linguagemPreferida = linguagemPreferida + linguagem.get(i);
            }
        }

        String fluente = "\nÉ fluente em inglês: ";
        if (rbS.isChecked())
            fluente = fluente + "Sim";
        if (rbN.isChecked())
            fluente = fluente + "Não";

        String msg = "Dados \n";

        Toast.makeText(MainActivity.this, msg + nome + email + linguagemPreferida + fluente, Toast.LENGTH_LONG).show();

    }
}
