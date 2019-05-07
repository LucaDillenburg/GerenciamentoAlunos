package br.unicamp.clienteescola;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ConsultarUmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_um);

        // Nome das variaveis: edtRA (editBox)
        //                     _dynamic (listView)
        //                     btnConsultar
        //                     txvErro (TextView para possiveis erros)
    }
}
