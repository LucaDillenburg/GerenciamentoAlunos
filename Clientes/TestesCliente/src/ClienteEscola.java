import java.io.*; //bf e println
import java.net.*;

import com.google.gson.Gson; //to Json
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import aluno.*;
import cliente.clientews.ClienteWS;
import cliente.api_response.APIResponse;

public class ClienteEscola
{
	private static int ULTIMA_OPCAO = 6;
    private static int OPCAO_SAIR = 99;
    private static String URL = "http://localhost:8080/build/webresources/escola/";

    // testa se pode-se colocar texto normal em JsonObject
    // conclusao: nao funciona.
    // solucao: fazer um metodo que retorna o response content como String e outro como JsonObject
    public static void teste1()
    {	
    	try {
    		APIResponse response = new APIResponse(200, "funcionou!");
    		String responseContent = response.getContentAsString();
    		System.out.println(responseContent);
    	}catch(Exception e) {e.printStackTrace();}
    }
    
    // testa onde e como a mensagem de erro eh recebida 
    // conclusao: 
    // solucao: 
    public static void teste2()
    {	
    	try {
    		APIResponse response = ClienteWS.chamarMetodoServidor(URL+"aa/", "GET");
    		System.out.println(response+"");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static void main(String[] args)
    {
    	teste2();
    }
}
