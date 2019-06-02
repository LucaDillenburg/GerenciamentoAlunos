package cliente.clientews;

import java.io.*; //BufferedReader, InputStreamReader
import java.net.*; //url, httpurlconnection
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson; //to Json

import cliente.api_response.APIResponse;


public class ClienteWS
{
    //pre-requisicao
    public static String encodeString(String str) throws UnsupportedEncodingException
	{
		return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
	}

    //requisicao
	public static APIResponse chamarMetodoServidor(String url, String tipoMetodo) throws IOException
	{
		return ClienteWS.chamarMetodoServidor(url, tipoMetodo, true);
	}
	public static APIResponse chamarMetodoServidor(String url, String tipoMetodo, boolean temRetorno, Object... arguments) throws IOException
    {
        //URL
        URL objURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection) objURL.openConnection();
        con.setDoOutput(true);

        //conexao com WebService
        con.setRequestMethod(tipoMetodo);
        con.setRequestProperty("Content-Type", "application/json");
        
        //Passar objetos como parametros para o WebService
        if (arguments.length > 0)
        {
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

        //retorno do metodo
        String jsonReturn = "";
        if (temRetorno)
        {
        	//inicializar stream reader (eh diferente para status OK e status Erro)
        	BufferedReader bufferedReader;
        	if (ClienteWS.tipoResponseCode(response.getCode()) == ClienteWS.RESPONSE_OK)
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
        return response;
    }

    //response
    public static final int RESPONSE_OK = 200;
    public static final int RESPONSE_CLIENT_ERROR = 400;
    public static final int RESPONSE_SERVER_ERROR = 500;
    public static final int RESPONSE_OUTRO = 0; //outro

    public static final int[] RESPONSES = {ClienteWS.RESPONSE_OK, ClienteWS.RESPONSE_CLIENT_ERROR, ClienteWS.RESPONSE_SERVER_ERROR};
    public static int tipoResponseCode(int responseCode)
    {
        for (int defaultResponseCode : ClienteWS.RESPONSES)
            if (responseCode >= defaultResponseCode && responseCode <= defaultResponseCode + 99)
                return defaultResponseCode;
        return ClienteWS.RESPONSE_OUTRO;
    }
}
