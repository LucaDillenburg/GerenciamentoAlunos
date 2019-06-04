package br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.cliente_escola;

import java.io.IOException;

import br.unicamp.clienteescola.requisicoes_ao_servidor.aluno.Aluno;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.api_exception.APIException;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.api_response.APIResponse;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.clientews.ClienteWS;

import static br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.data_for_request.DataForRequest.OperacaoEscola.CONSULTAR_ALUNOS;

public class ClienteEscola {

    protected static final String IP = "143.106.200.100";
    protected static final String PORTA = "8080";
    protected static final String URL_BASE = "http://" + IP + ":" + PORTA + "/build/webresources/escola/";

    public static APIResponse consultarAlunos() throws IOException, APIException
    {
        return ClienteWS.chamarMetodoServidor(ClienteEscola.URL_BASE + "getAlunos/", "GET");
    }

    public static APIResponse consultarAluno(String ra) throws IOException, APIException
    {
        return ClienteWS.chamarMetodoServidor(URL_BASE + "getAluno/" + ra, "GET");
    }

    public static APIResponse inserir(Aluno aluno) throws IOException, APIException
    {
        return ClienteWS.chamarMetodoServidor(URL_BASE + "incluirAluno", "POST", true,
                aluno);
    }

    public static APIResponse alterar(Aluno aluno) throws IOException, APIException
    {
        return ClienteWS.chamarMetodoServidor(URL_BASE + "alterarAluno", "PUT", true,
                aluno);
    }

    public static APIResponse excluir(String ra) throws IOException, APIException
    {
        return ClienteWS.chamarMetodoServidor(URL_BASE + "excluirAluno/" + ra, "PUT", true);
        // JAR-RS nao aceita metodo @Delete
    }

}
