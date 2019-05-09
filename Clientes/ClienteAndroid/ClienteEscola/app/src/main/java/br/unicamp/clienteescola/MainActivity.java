package br.unicamp.clienteescola;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected Button btnConsultarTodos, btnConsultarUm, btnInserir, btnAlterar, btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnConsultarTodos = findViewById(R.id.btnConsultarTodos);
        this.btnConsultarTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentActivityConsultarTodos = new Intent(MainActivity.this, ConsultarTodosActivity.class);
                startActivity(intentActivityConsultarTodos);
            }
        });

        this.btnConsultarUm = findViewById(R.id.btnConsultarUm);
        this.btnConsultarUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentActivityConsultarUm = new Intent(MainActivity.this, ConsultarUmActivity.class);
                startActivity(intentActivityConsultarUm);
            }
        });

        this.btnInserir = findViewById(R.id.btnInserir);
        this.btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentActivityInserir = new Intent(MainActivity.this, InserirActivity.class);
                startActivity(intentActivityInserir);
            }
        });

        this.btnAlterar = findViewById(R.id.btnAlterar);
        this.btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentActivityAlterar = new Intent(MainActivity.this, AlterarActivity.class);
                startActivity(intentActivityAlterar);
            }
        });

        this.btnExcluir = findViewById(R.id.btnExcluir);
        this.btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentActivityExcluir = new Intent(MainActivity.this, ExcluirActivity.class);
                startActivity(intentActivityExcluir);
            }
        });
    }
}
