package commonFunctions;

import org.json.JSONObject;

public class JsonCreator {
	private  JSONObject JsonObj = null;
	
	public JsonCreator() {
		JsonObj = new JSONObject();
	}
	
	public  JSONObject getJsonObject() {
		if (JsonObj == null) {
			JsonObj = new JSONObject();
		}
		return JsonObj;
	}
	
	public JSONObject addProperty(String propNameString, String propValue) {
		JsonObj.put(propNameString, propValue);
		return JsonObj;
	}
}
