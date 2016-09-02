package networkUtils;

import flexjson.JSONSerializer;

public class UsernameMessage extends Message {

	String username;
	
	public UsernameMessage(String username) {
		super();
		this.m_type = MessageType.USERNAME;
		this.username = username;
	}

	@Override
	public String getMessage() {
		return this.username;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	@Override
	public String toString() {
		String m = new JSONSerializer().deepSerialize(this);
		return m;
	}

	
}