package br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.async_task;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.unicamp.clienteescola.activities.ActivityConsomeServico;
import br.unicamp.clienteescola.activities.consultar_aluno.ActivityConsultarAluno;
import br.unicamp.clienteescola.activities.consultar_alunos.ActivityConsultarAlunos;
import br.unicamp.clienteescola.requisicoes_ao_servidor.aluno.Aluno;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.api_exception.APIException;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.api_response.APIResponse;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.clientews.ClienteWS;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.async_task.AsyncTaskWithThrowable;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.cliente_escola.ClienteEscola;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.data_for_request.DataForRequest;

public class AsyncTaskCRUDAlunos extends AsyncTaskWithThrowable<DataForRequest, Void, APIResponse> {

    //variaveis
    protected ActivityConsomeServico clientActivity;
    protected DataForRequest parametros = null;

    public AsyncTaskCRUDAlunos(ActivityConsomeServico clientActivity)
    {
        super();
        this.clientActivity = clientActivity;
    }


    //background work
    @Override
    protected APIResponse doInBackground(DataForRequest... parametrosAsyncTasks) throws IOException, APIException {
        this.parametros = parametrosAsyncTasks[0];

        APIResponse apiResponse = null;
        switch (this.parametros.getOperacao())
        {
            case CONSULTAR_ALUNOS: //Vetor de Objects: vazio
                apiResponse = ClienteEscola.consultarAlunos();
                break;

            case CONSULTAR_ALUNO: //Vetor de Objects: [0]:RA
                apiResponse = ClienteEscola.consultarAluno((String)this.parametros.getOutrosObjetos()[0]/*ra do aluno a ser consultado*/);
                break;

            case INSERIR: //Vetor de Objects: [0]:Aluno
                apiResponse = ClienteEscola.inserir((Aluno)this.parametros.getOutrosObjetos()[0]/*aluno a ser incluido*/);
                break;

            case ALTERAR: //Vetor de Objects: [0]:Aluno
                apiResponse = ClienteEscola.alterar((Aluno)this.parametros.getOutrosObjetos()[0]/*aluno a ser alterado*/);
                break;

            case EXCLUIR: //Vetor de Objects: [0]:RA
                apiResponse = ClienteEscola.excluir((String)this.parametros.getOutrosObjetos()[0]/*ra do aluno a ser excluido*/);
                break;
        }

        return apiResponse;
    }

    @Override
    protected void onPostExecute(ResultHolder<APIResponse> resultHolder) { //executado depois que doInBackground acaba
        super.onPostExecute(resultHolder);

        try
        {
            final APIResponse apiResponse = resultHolder.getResult();

            String resultado = "";
            switch (this.parametros.getOperacao())
            {
                case CONSULTAR_ALUNOS:
                    JsonArray alunosArray = apiResponse.getContentAsJsonArray();
                    List<Aluno> alunos = new LinkedList<Aluno>();
                    for (int i = 0; i < alunosArray.size(); i++)
                    {
                        final JsonElement elementAluno = alunosArray.get(i);
                        alunos.add(new Gson().fromJson(elementAluno, Aluno.class));
                    }
                    ((ActivityConsultarAlunos)this.clientActivity).colocarAlunosTela(alunos);
                    break;

                case CONSULTAR_ALUNO:
                    Aluno aluno = new Gson().fromJson(apiResponse.getContentAsString(), Aluno.class);
                    ((ActivityConsultarAluno)this.clientActivity).colocarAlunoTela(aluno);
                    break;

                case INSERIR:
                    resultado = "Aluno incluido com sucesso!";
                    break;

                case ALTERAR:
                    resultado = "Aluno alterado com sucesso!";
                    break;

                case EXCLUIR:
                    resultado = "Aluno excluido com sucesso!";
                    break;
            }

            this.clientActivity.setResultado(resultado);
        }
        catch(APIException | IOException exception)
        {
            final APIResponse apiResponse = ((exception instanceof APIException) ? ((APIException)exception).getResponse() : null);

            if ((apiResponse == null) ? exception instanceof IOException : !apiResponse.ehResponseConhecido())
                // se o erro eh desconhecido
                this.clientActivity.setMsgErro("Erro desconhecido! Tente novamente mais tarde..." +
                        ((apiResponse != null) ? " Response: " + apiResponse : ""));
            else
            {
                if (APIResponse.tipoResponseCode(apiResponse.getCode()) == APIResponse.ResponseType.CLIENT_ERROR)
                    this.clientActivity.setMsgErro(apiResponse.getContentAsString());
                else //ResponseType.SERVER_ERROR
                    this.clientActivity.setMsgErro("Erro no servidor: tente novamente mais tarde...");
            }

            //outros
            switch (this.parametros.getOperacao())
            {
                case CONSULTAR_ALUNOS:
                    ((ActivityConsultarAlunos)this.clientActivity).limparAlunos();
                    break;

                case CONSULTAR_ALUNO:
                    ((ActivityConsultarAluno)this.clientActivity).limparAluno();
                    break;
            }
        }
        catch(Throwable throwable)
        {
            // nao vai chegar aqui
        }

        this.clientActivity.finishedAsyncTask();
    }


    //outros / secundarios
    @Override
    protected void onPreExecute() { //executado antes de doInBackground
        super.onPreExecute();
        this.clientActivity.startedAsyncTask();
    }

    @Override
    protected void onProgressUpdate(Void... none) { //eh executado depois de doInBackground
        super.onProgressUpdate(none);
        clientActivity.progressUpdate();
    }
}