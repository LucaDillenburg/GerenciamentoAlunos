package br.unicamp.clienteescola;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExcluirActivity extends AppCompatActivity {

    protected Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir);

        this.btnExcluir = findViewById(R.id.btnExcluir);
        this.btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Fazer a exclusao
                // Nome das variaveis: edtRA, btnExcluir e txvErro

            }
        });

    }
}
