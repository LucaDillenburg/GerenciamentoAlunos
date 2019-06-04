package br.unicamp.clienteescola.activities.consultar_aluno;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.unicamp.clienteescola.R;
import br.unicamp.clienteescola.activities.ActivityConsomeServico;
import br.unicamp.clienteescola.activities.lista_alunos_adapter.ListaAlunosAdapter;
import br.unicamp.clienteescola.requisicoes_ao_servidor.aluno.Aluno;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.async_task.AsyncTaskCRUDAlunos;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.data_for_request.DataForRequest;

public class ActivityConsultarAluno extends ActivityConsomeServico {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_aluno);

        this.tvResultado = findViewById(R.id.tvResultado);

        findViewById(R.id.btnConsultar).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Chamar AsyncTask que fara a listagem dos alunos
                    final String ra = ((EditText)findViewById(R.id.edtRA)).getText().toString();
                    AsyncTaskCRUDAlunos asyncTask = new AsyncTaskCRUDAlunos(ActivityConsultarAluno.this);
                    asyncTask.execute(new DataForRequest(DataForRequest.OperacaoEscola.CONSULTAR_ALUNO, ra));
                }
            }
        );
    }

    public void colocarAlunoTela(Aluno aluno)
    {
        ArrayList<Aluno> auxArray = new ArrayList<Aluno>();
        auxArray.add(aluno);

        ListaAlunosAdapter alunosAdapter = new ListaAlunosAdapter(this, android.R.layout.simple_list_item_1, auxArray);
        ((ListView)findViewById(R.id.lvAluno)).setAdapter(alunosAdapter);
    }

    public void limparAluno()
    {
        ((ListView)findViewById(R.id.lvAluno)).setAdapter(new ListaAlunosAdapter(this, android.R.layout.simple_list_item_1, new ArrayList<Aluno>()));
    }
}
