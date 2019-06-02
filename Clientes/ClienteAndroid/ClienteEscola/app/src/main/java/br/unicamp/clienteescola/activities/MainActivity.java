package br.unicamp.clienteescola.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.unicamp.clienteescola.R;
import br.unicamp.clienteescola.activities.alterar.ActivityAlterar;
import br.unicamp.clienteescola.activities.consultar_aluno.ActivityConsultarAluno;
import br.unicamp.clienteescola.activities.consultar_alunos.ActivityConsultarAlunos;
import br.unicamp.clienteescola.activities.excluir.ActivityExcluir;
import br.unicamp.clienteescola.activities.inserir.ActivityInserir;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pedir permissao para acessar servidor
        this.chegarPermissaoAcessarServidor();

        //programar click dos botoes
        findViewById(R.id.btnConsultarTodos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irParaOutraActivity(ActivityConsultarAlunos.class);
            }
        });

        findViewById(R.id.btnConsultarUm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irParaOutraActivity(ActivityConsultarAluno.class);
            }
        });

        findViewById(R.id.btnInserir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irParaOutraActivity(ActivityInserir.class);
            }
        });

        findViewById(R.id.btnAlterar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irParaOutraActivity(ActivityAlterar.class);
            }
        });

        findViewById(R.id.btnExcluir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irParaOutraActivity(ActivityExcluir.class);
            }
        });
    }

    protected static final int REQUEST_SERVIDOR = 1;
    protected void chegarPermissaoAcessarServidor()
    {
        System.out.println(ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) + " -- " + PackageManager.PERMISSION_GRANTED);

        // Checar permissões em tempo real se estiver acima da API 23
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.INTERNET, }, MainActivity.REQUEST_SERVIDOR);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MainActivity.REQUEST_SERVIDOR)
        {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Você deve dar permissão para o app internet http!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void irParaOutraActivity(Class classOutraActivity)
    {
        Intent intentOutraActivity = new Intent(MainActivity.this, classOutraActivity);
        startActivity(intentOutraActivity);
    }
}
