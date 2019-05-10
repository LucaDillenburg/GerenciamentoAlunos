package clientews;

import java.io.*; //BufferedReader, InputStreamReader
import java.net.*; //url, httpurlconnection
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson; //to Json

import aluno.Aluno;

public class ClienteWS
{
	public static String chamarMetodoServidor(String url, String tipoMetodo) throws IOException
	{
		return ClienteWS.chamarMetodoServidor(url, tipoMetodo, true);
	}
	public static String chamarMetodoServidor(String url, String tipoMetodo, boolean temRetorno, Object... arguments) throws IOException
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

        //response code
        int responseCode = con.getResponseCode();

        //retorno do metodo
        String jsonReturn = "";
        if (temRetorno)
        {
        	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = bufferedReader.readLine()) != null)
                response.append(inputLine);
            jsonReturn = response.toString(); // retorno em JSON
            bufferedReader.close();
        }

        con.disconnect();
        return jsonReturn;
    }

	public static String encodeString(String str) throws UnsupportedEncodingException
	{
		return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
	}
}
