package networkUtils;

import java.util.ArrayList;

import flexjson.JSONSerializer;

public class LobbyMessage extends Message{
	
	ArrayList<String> usernames;

	public LobbyMessage(ArrayList<String> usernames) {
		super();
		this.m_type = MessageType.LOBBY;
		this.usernames = usernames;
	}
	
	@Override
	public String toString() {
		String m = new JSONSerializer().deepSerialize(this);
		return m;
	}

}
