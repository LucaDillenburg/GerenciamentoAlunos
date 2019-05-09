package br.unicamp.clienteescola;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InserirActivity extends AppCompatActivity {

    protected Button btnIncluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        this.btnIncluir = findViewById(R.id.btnIncluir);
        this.btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Fazer a inclusao
                // Nome das variaveis: edtRA, edtNome e edtEmail
                //                     btnIncluir
                //                     txvErro

            }
        });

        //

    }
}
