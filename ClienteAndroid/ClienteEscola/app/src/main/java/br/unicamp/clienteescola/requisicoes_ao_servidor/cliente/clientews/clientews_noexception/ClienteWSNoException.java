package br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.clientews.clientews_noexception;

import java.io.IOException;

import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.clientews.ClienteWS;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.api_response.APIResponse;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.api_exception.APIException;

/**
 * A unica diferenca dessa classe para ClienteWS eh que mesmo que o response code nao seja OK, ele nao da throw APIException
 */
public class ClienteWSNoException extends ClienteWS
{
    /**
     * A unica diferenca desse metodo para o mesmo metodo da classe pai eh que se a Response do servidor nao for OK, ele da throw APIException
    */
    public static APIResponse chamarMetodoServidor(String url, String tipoMetodo, boolean endpointEhVoid, Object... arguments) throws IOException
    {
        try
        {
            return ClienteWS.chamarMetodoServidor(url, tipoMetodo, endpointEhVoid, arguments);
        }catch(APIException apiException)
        {
            return apiException.getResponse();
        }
    }
}