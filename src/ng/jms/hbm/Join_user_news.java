package ng.jms.hbm;

import java.util.HashSet;
import java.util.Set;

public class Join_user_news {

	private int userid;
	private String name;
	private String passwd;
	private Set<Table_news> news = new HashSet<>();
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Table_news> getNews() {
		return news;
	}
	public void setNews(Set<Table_news> news) {
		this.news = news;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
