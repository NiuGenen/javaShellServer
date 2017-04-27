package ng.jms.hbm;

public class Join_news_user_topic {

	private int newsid;
	private Table_user user;
	private String title;
	private String cntnt;
	private long time;
	private Table_topic topic;
	public int getNewsid() {
		return newsid;
	}
	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCntnt() {
		return cntnt;
	}
	public void setCntnt(String cntnt) {
		this.cntnt = cntnt;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public Table_topic getTopic() {
		return topic;
	}
	public void setTopic(Table_topic topic) {
		this.topic = topic;
	}
	public Table_user getUser() {
		return user;
	}
	public void setUser(Table_user user) {
		this.user = user;
	}
	
}
