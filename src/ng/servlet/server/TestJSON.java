package ng.servlet.server;

import org.junit.*;

import static org.junit.Assert.*;

import org.json.*;

public class TestJSON {

	private String json1 = "{'key':'value'}";
	private String json2 = "{'outkey':{'inkey':'value'}}";
	private String json3 = "{'array':['a1','a2','a3']}";
	
	@Test
	public void test_json_resolve(){
		
		System.out.println("------in test_json_resolve");

		JSONObject obj1 = new JSONObject(json1);
		JSONObject obj2 = new JSONObject(json2);
		JSONObject obj3 = new JSONObject(json3);
		
		assertEquals("value", obj1.get("key") );
		
		System.out.println(obj2.get("outkey"));
		assertEquals("value", obj2.getJSONObject("outkey").get("inkey") );
		
		JSONArray array = obj3.getJSONArray("array");

		assertEquals(3, array.length());
		assertEquals("a1", array.get(0));
		assertEquals("a2", array.get(1));
		assertEquals("a3", array.get(2));
		
	}
	
	@Test
	public void test_json_create(){

		System.out.println("-------in test_json_create");
		
		JSONObject obj = new JSONObject();
		
		obj.append("append","value");
		System.out.println(obj.toString());
		
		obj.put("put", "value");
		System.out.println(obj.toString());
	}
}
