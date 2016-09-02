package networkUtils;

import flexjson.JSONSerializer;

public class ChallengeMessage extends Message {

	String opponent;
	
	
	public ChallengeMessage(int from, int to,String opponent) {
		super();
		this.m_type = MessageType.CHALLENGE;
		this.opponent = opponent;
	}

	@Override
	public String getMessage() {
		return this.opponent;
	}

	@Override
	public String toString() {
		String m = new JSONSerializer().deepSerialize(this);
		return m;
	}

}
