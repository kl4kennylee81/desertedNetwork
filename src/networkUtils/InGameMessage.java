package networkUtils;

import flexjson.JSONSerializer;

public class InGameMessage extends Message {
	
	// actually when we move this into the proper game repo 
	// the message will be of type of list of actionNodes
	String actionNodeList;
	
	Integer fromId;
	Integer toId;

	public InGameMessage(int from, int to,String actionNodeList) {
		this.m_type = MessageType.INGAME;
		fromId = from;
		toId = to;
		this.actionNodeList = actionNodeList;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return actionNodeList;
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
