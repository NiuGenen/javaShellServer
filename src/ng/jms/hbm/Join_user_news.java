package ng.jms.hbm;

import java.util.HashSet;
import java.util.Set;

public class Join_user_news {

	private int userid;
	private String username;
	private Set<Table_news> news = new HashSet<>();
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<Table_news> getNews() {
		return news;
	}
	public void setNews(Set<Table_news> news) {
		this.news = news;
	}
}
