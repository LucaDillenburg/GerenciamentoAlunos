package br.unicamp.clienteescola;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlterarActivity extends AppCompatActivity {

    protected Button btnAlterar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        this.btnAlterar = findViewById(R.id.btnAlterar);
        this.btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Fazer a alteracao
                // Nome das variaveis: cbxManterNome e cbxManterEmail (Checkbox caso queira manter um dos valores)
                //                     edtNome, edtEmail e edtRA (editBoxes)
                //                     btnAlterar
                //                     txvErro (TextView pra possiveis erros)

            }
        });

    }
}
