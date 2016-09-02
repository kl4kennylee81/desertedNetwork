package networkUtils;

import flexjson.JSONSerializer;
import networkUtils.Message.MessageType;

public class NormalMessage extends Message{

	String text;
	
	Integer fromId;
	Integer toId;
	
	public NormalMessage(int from,int to,String text) {
		super();
		this.m_type = MessageType.NORMAL;
		this.text = text;
		this.fromId = from;
		this.toId = to;
	}
	
	public NormalMessage(String msg) {
		super();
		this.m_type = MessageType.NORMAL;
		this.text = msg;
		this.fromId = -1;
		this.toId = -1;
	}

	@Override
	public String getMessage() {
		return this.text;
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
