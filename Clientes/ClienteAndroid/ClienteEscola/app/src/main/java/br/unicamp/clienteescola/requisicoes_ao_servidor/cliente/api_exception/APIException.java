package br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.api_exception;

import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.api_response.APIResponse;

public class APIException extends Exception {
    protected APIResponse response;

    public APIException(String exceptionMessage, APIResponse response)
    {
        super(exceptionMessage);
        this.response = response;
    }

    public APIResponse getResponse()
    {
        return this.response;
    }
}