package ng.ws.server;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The implementation of the NewsWebService JAX-WS Web Service.
 *
 * @author niugenen
 */
@WebService(serviceName = "NewsWebService", portName = "NewsWeb", name = "NewsWeb", endpointInterface = "ng.ws.server.NewsWebService",
targetNamespace = "http://www.shell.server/ng/ws/NewsWeb")
public class NewsWebServiceImpl implements NewsWebService{

	private Map<Long,NewsInWS> news_map;
	
	public NewsWebServiceImpl(){
		news_map = new HashMap<Long, NewsInWS>();
	}
	
	@Override
	public String testWS() {
		return "Welcome visit web service";
	}

	@Override
	public boolean publish(String jsonNews_publish) {
		boolean ret = true;
		try{
			NewsInWS n = new NewsInWS( jsonNews_publish );
			while(news_map.containsKey(n.getID()))
				n = new NewsInWS( jsonNews_publish );
			news_map.put(n.getID(), n);
		}catch(JSONException e){
			System.out.println("Fail to create NewsInWS via json");
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}

	@Override
	public String getNews(String key) {
		JSONObject ret = new JSONObject();
		int count = 0;
		JSONArray news = new JSONArray();
		for(NewsInWS n:news_map.values()){
			if(n.getTitle().contains(key) || n.getContent().contains(key)){
				news.put(n.toJSONObject());
				count++;
			}
		}
		ret.put("count", count);
		ret.put("news", news);
		return ret.toString();
	}

	@Override
	public boolean modifyNews(String op, long newsid, String json) {
		boolean ret = true;
		
		switch(op){
		case "delete":
			if(news_map.containsKey(newsid)){
				news_map.remove(newsid);
			}
			else{
				ret = false;
			}
		break;
		case "update":
			if(news_map.containsKey(newsid)){
				news_map.get(newsid).update(json);
			}
			else{
				ret = false;
			}
		break;
		default:
			ret = false;
			break;
		}
		
		return ret;
	}

	@Override
	public String getNewsByid(long newsid) {
		if(news_map.containsKey(newsid))
			return news_map.get(newsid).toJSONObject().toString();
		else
			return null;
	}

}
