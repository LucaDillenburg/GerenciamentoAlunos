package br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.clientews;

import java.io.*; //BufferedReader, InputStreamReader
import java.net.*; //url, httpurlconnection
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson; //to Json

import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.api_exception.APIException;
import br.unicamp.clienteescola.requisicoes_ao_servidor.cliente.api_response.*;


public class ClienteWS
{
    //pre-requisicao
    public static String encodeString(String str) throws UnsupportedEncodingException
	{
		return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
	}

    //requisicao
	public static APIResponse chamarMetodoServidor(String url, String tipoMetodo) throws IOException, APIException
	{
		return ClienteWS.chamarMetodoServidor(url, tipoMetodo, false);
	}

	public static APIResponse chamarMetodoServidor(String url, String tipoMetodo, boolean endpointEhVoid, Object... arguments) throws IOException, APIException
    {
        //URL
        URL objURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection) objURL.openConnection();

        //conexao com WebService
        con.setRequestMethod(tipoMetodo);
        con.setRequestProperty("Content-Type", "application/json");
        
        //Passar objetos como parametros para o WebService
        if (arguments.length > 0)
        {
            con.setDoOutput(true); //true = informacoes no body
        	OutputStream os = con.getOutputStream();
            for (Object arg : arguments)
            {
                //Formata em json o item da lista a ser inserido com POST
                String jsonFromObj = new Gson().toJson(arg);

                //Escreve o output tranformado em Bytes
                os.write(jsonFromObj.getBytes());
            }
        }

        //Response
        APIResponse response = new APIResponse();

        //response code
        response.setCode(con.getResponseCode());

        final boolean isResponseOK = APIResponse.tipoResponseCode(response.getCode()) == APIResponse.ResponseType.OK;

        //retorno do metodo
        String jsonReturn = "";
        if (!endpointEhVoid || !isResponseOK)
        {
        	//inicializar stream reader (eh diferente para status OK e status Erro)
        	BufferedReader bufferedReader;
        	if (isResponseOK)
        		bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        	else
        		bufferedReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            
        	String inputLine;
            StringBuffer responseStr = new StringBuffer();
            while((inputLine = bufferedReader.readLine()) != null)
                responseStr.append(inputLine);
            jsonReturn = responseStr.toString(); // retorno em JSON
            bufferedReader.close();
        }

        con.disconnect();
        response.setContent(jsonReturn);

        if (!isResponseOK)
            throw new APIException("Erro ao fazer requisicao ao servidor.", response);

        return response;
    }
}
