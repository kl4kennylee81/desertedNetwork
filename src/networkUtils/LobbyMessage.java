package networkUtils;

import flexjson.JSONSerializer;

public class LobbyMessage extends Message{

	public LobbyMessage() {
		super();
		this.m_type = MessageType.LOBBY;
	}
	
	@Override
	public String toString() {
		String m = new JSONSerializer().deepSerialize(this);
		return m;
	}

}
