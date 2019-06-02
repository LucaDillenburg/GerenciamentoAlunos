package br.unicamp.clienteescola.activities.inserir;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.unicamp.clienteescola.R;
import br.unicamp.clienteescola.activities.ActivityConsomeServico;
import br.unicamp.clienteescola.activities.alterar.ActivityAlterar;
import br.unicamp.clienteescola.requisicoes_ao_servidor.aluno.Aluno;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.async_task.AsyncTaskCRUDAlunos;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.data_for_request.DataForRequest;

public class ActivityInserir extends ActivityConsomeServico {

    protected Button btnIncluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        this.btnIncluir = findViewById(R.id.btnIncluir);
        this.btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String ra = ((EditText)findViewById(R.id.edtRA)).getText().toString();
                final String novoNome = ((EditText)findViewById(R.id.edtNome)).getText().toString();
                final String novoEmail = ((EditText)findViewById(R.id.edtEmail)).getText().toString();

                try
                {
                    Aluno aluno = new Aluno(ra, novoNome, novoEmail);

                    AsyncTaskCRUDAlunos crudAlunos = new AsyncTaskCRUDAlunos(ActivityInserir.this);
                    crudAlunos.execute(new DataForRequest(DataForRequest.OperacaoEscola.INSERIR, aluno));
                }catch(IllegalArgumentException e)
                {
                    setMsgErro("Dados invalidos!");
                }
            }
        });
    }
}
