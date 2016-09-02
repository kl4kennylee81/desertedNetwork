package networkUtils;

import flexjson.JSONSerializer;

public class BackMessage extends Message {
	
	// I can imagine this would hold whatever information to handle disconnects
	// I am just having everything be a string right now but it would be
	// properly filled in with a set of attributes necessary to carry out the operation
	String disconnect;
	
	Integer fromId;
	Integer toId;

	public BackMessage(int from, int to,String disconnect) {
		fromId = from;
		toId = to;
		this.m_type = MessageType.BACK;
		this.disconnect = disconnect;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		return disconnect;
	}
	
	public int getDest(){
		return toId;
	}
	
	public int getFrom(){
		return fromId;
	}

	@Override
	public String toString() {
		String m = new JSONSerializer().deepSerialize(this);
		return m;
	}

}
