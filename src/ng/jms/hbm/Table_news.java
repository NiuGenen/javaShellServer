package ng.jms.hbm;

public class Table_news {
	private int id;
	private int userid;
	private String title;
	private String cntnt;
	private long time;
	private int topicid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
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
	public int getTopicid() {
		return topicid;
	}
	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}
}
