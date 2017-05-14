package ng.ws.server;

import org.json.*;

public class NewsInWS {
	private static long getUniqueID(){
		return System.currentTimeMillis();
	}
	private long id;
	public long getID(){
		return id;
	}
	
	private String title;
	private String content;
	private long time;
	
	public NewsInWS(){
		id = getUniqueID();
	}
	public NewsInWS(String title, String content, long time){
		setTitle(title);
		setContent(content);
		setTime(time);
		id = getUniqueID();
	}
	public NewsInWS(String json) throws JSONException {
		JSONObject obj = new JSONObject(json);
		setTitle( obj.getString("title") );
		setContent( obj.getString("content") );
		setTime( obj.getLong("time") );
		id = getUniqueID();
	}
	
	public JSONObject toJSONObject(){
		JSONObject ne = new JSONObject();
		ne.put("title", this.getTitle());
		ne.put("content", this.getContent());
		ne.put("time", this.getTime());
		ne.put("id", this.getID());
		return ne;
	}
	
	public void update(String json) throws JSONException{
		JSONObject obj = new JSONObject(json);
		setTitle( obj.getString("title") );
		setContent( obj.getString("content") );
		setTime( obj.getLong("time") );
	}
	
	public String getTitle()	{
		return	title;
	}
	public void setTitle(String title)	{
		this.title = title;
	}
	public String getContent()	{
		return content;
	}
	public void setContent(String content)	{
		this.content	=	content;
	}
	public long getTime(){
		return time;
	}
	public void setTime(long time){
		this.time = time;
	}
}
