package br.com.lessons.tarefa1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btCalcular;
    EditText etCampo1, etCampo2, etResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCampo1 = findViewById(R.id.etNota1);
        etCampo2 = findViewById(R.id.etNota2);
        etResultado = findViewById(R.id.etResultado);
        btCalcular = findViewById(R.id.btCalcular);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                float nota1, nota2, media;
                nota1 = Float.parseFloat(etCampo1.getText().toString());
                nota2 = Float.parseFloat(etCampo2.getText().toString());
                media = (nota1 + nota2) / 2;

                String strMedia = String.valueOf(media);

                if (media >= 6) {
                    etResultado.setTextColor(Color.parseColor("#00B090"));
                    etResultado.setText(strMedia + " Aprovado!");
                } else {
                    etResultado.setTextColor(Color.parseColor("#B00020"));
                    etResultado.setText(strMedia + " Reprovado!");
                }
            }
        });
    }
}
