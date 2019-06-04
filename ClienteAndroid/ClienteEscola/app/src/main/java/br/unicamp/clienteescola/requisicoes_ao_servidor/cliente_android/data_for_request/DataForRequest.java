package br.unicamp.clienteescola.requisicoes_ao_servidor.cliente_android.data_for_request;

public class DataForRequest {

    public static //operacoes/servicos oferecidos
    enum OperacaoEscola {
        CONSULTAR_ALUNOS,
        CONSULTAR_ALUNO,
        INSERIR,
        EXCLUIR,
        ALTERAR
    };

    protected OperacaoEscola operacao;
    protected Object[] outrosObjetos;

    public DataForRequest(OperacaoEscola operacao, Object... outrosObjetos) {
        this.operacao = operacao;
        this.outrosObjetos = outrosObjetos;
    }

    //getters
    public OperacaoEscola getOperacao()
    {
        return this.operacao;
    }

    public Object[] getOutrosObjetos()
    {
        return this.outrosObjetos;
    }
}
