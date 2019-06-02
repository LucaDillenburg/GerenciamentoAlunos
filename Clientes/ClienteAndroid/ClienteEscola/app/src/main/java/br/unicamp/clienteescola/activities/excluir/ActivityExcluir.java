package br.unicamp.clienteescola.activities.excluir;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.unicamp.clienteescola.R;
import br.unicamp.clienteescola.activities.ActivityConsomeServico;
import br.unicamp.clienteescola.activities.alterar.ActivityAlterar;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.async_task.AsyncTaskCRUDAlunos;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.data_for_request.DataForRequest;

public class ActivityExcluir extends ActivityConsomeServico {

    protected Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir);

        this.btnExcluir = findViewById(R.id.btnExcluir);
        this.btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ra = ((EditText)findViewById(R.id.edtRA)).getText().toString();
                AsyncTaskCRUDAlunos crudAlunos = new AsyncTaskCRUDAlunos(ActivityExcluir.this);
                crudAlunos.execute(new DataForRequest(DataForRequest.OperacaoEscola.EXCLUIR, ra));
            }
        });

    }
}