package cliente.api_response;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class APIResponse
{
    protected int code;
    protected String content = null;

    //construtores
    public APIResponse()
    { }

    public APIResponse(int code)
    {
        this(code, "");
    }

    public APIResponse(int code, String responseObject)
    {
        this.setCode(code);
        this.setContent(responseObject);
    }

    
    //getters
    public int getCode()
    {
        return this.code;
    }

    public String getContentAsString()
    {
        return this.content;
    }
    
    public JsonObject getContentAsJsonObject()
    {
    	return new JsonParser().parse(this.content).getAsJsonObject();
    }

    //setters
    public void setCode(int code)
    {
        this.code = code;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    
    //metodos obrigatorios
    public String toString()
    {
    	return "{Code: "+this.code+"; Content: "+this.content+"}";
    }
    
    public int hashCode()
    {
    	int ret = 7;
    	ret = ret*3 + new Integer(this.code).hashCode();
    	ret = ret*3 + this.content.hashCode();
    	return ret;
    }
    
    public boolean equals(Object obj)
    {
    	if (!(obj instanceof APIResponse))
    		return false;
    	if (this==obj)
    		return true;
    	
    	APIResponse response = (APIResponse)obj;
    	if (response.code != this.code)
    		return false;
    	if (response.content==null && this.content==null)
    		return true;
    	if ((response.content!=null && this.content==null) || (response.content==null && this.content!=null))
    		return false;
    	return response.content.equals(this.content); // nenhuma das duas strings sao nulas
    }
    
    public APIResponse(APIResponse modelo)
    {
    	this.code = modelo.code;
    	this.content = modelo.content;
    }
    
    public Object clone()
    {
    	return new APIResponse(this);
    }
}
