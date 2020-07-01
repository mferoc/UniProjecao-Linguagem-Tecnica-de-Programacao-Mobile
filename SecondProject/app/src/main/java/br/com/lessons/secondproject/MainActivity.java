package br.com.lessons.secondproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btButton;
    EditText etText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btButton = (Button) findViewById(R.id.btEnviar);
        etText = (EditText) findViewById(R.id.etPrincipal);
        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "Dados Enviados!\n";

                if (etText.getText().length() > 0) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle("Sucesso!");
                    alertDialog.setMessage(msg+"\n"+"Seu nome é: "+ etText.getText());
                    alertDialog.show();
                    etText.setText("");
                }
                else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setTitle("Erro!!!");
                    alertDialogBuilder.setMessage("O campo deve ser preenchido");
                    alertDialogBuilder.show();
                }
            }
        });

    }
}
