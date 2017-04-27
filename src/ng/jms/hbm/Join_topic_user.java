package ng.jms.hbm;

import java.util.HashSet;  
import java.util.Set; 

public class Join_topic_user {

	private int topicid;
	private String name;
	private Set<Table_user> user = new HashSet<>();
	public int getTopicid() {
		return topicid;
	}
	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Table_user> getUser() {
		return user;
	}
	public void setUser(Set<Table_user> user) {
		this.user = user;
	}
	
}
