package br.unicamp.clienteescola.activities.consultar_alunos;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.clienteescola.R;
import br.unicamp.clienteescola.activities.ActivityConsomeServico;
import br.unicamp.clienteescola.activities.lista_alunos_adapter.ListaAlunosAdapter;
import br.unicamp.clienteescola.requisicoes_ao_servidor.aluno.Aluno;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.async_task.AsyncTaskCRUDAlunos;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.data_for_request.DataForRequest;

public class ActivityConsultarAlunos extends ActivityConsomeServico {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_alunos);

        this.tvResultado = findViewById(R.id.tvResultado);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // Chamar AsyncTask que fara a listagem dos alunos
        AsyncTaskCRUDAlunos asyncTask = new AsyncTaskCRUDAlunos(this);
        asyncTask.execute(new DataForRequest(DataForRequest.OperacaoEscola.CONSULTAR_ALUNOS));
    }

    public void colocarAlunosTela(List<Aluno> alunos)
    {
        ListaAlunosAdapter alunosAdapter = new ListaAlunosAdapter(this, android.R.layout.simple_list_item_1, alunos);
        ((ListView)findViewById(R.id.lvAlunos)).setAdapter(alunosAdapter);
    }

    public void limparAlunos()
    {
        ((ListView)findViewById(R.id.lvAlunos)).setAdapter(new ListaAlunosAdapter(this, android.R.layout.simple_list_item_1, new ArrayList<Aluno>()));
    }
}
