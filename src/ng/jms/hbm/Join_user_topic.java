package ng.jms.hbm;

import java.util.HashSet;  
import java.util.Set; 

public class Join_user_topic {

	private int userid;
	private String name;
	private String passwd;
	private Set<Table_topic> topic = new HashSet<>();
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
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Set<Table_topic> getTopic() {
		return topic;
	}
	public void setTopic(Set<Table_topic> topic) {
		this.topic = topic;
	}
	
}
