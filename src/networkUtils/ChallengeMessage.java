package networkUtils;

import com.google.gson.Gson;

import flexjson.JSONSerializer;

public class ChallengeMessage extends Message {

	String from;
	String to;
	String opponent;
	
	public ChallengeMessage(){
		super();
		this.m_type = MessageType.CHALLENGE;
		from = "";
		to = "";
		opponent = "";
	}
	
	public ChallengeMessage(String from,String to,String opponent) {
		super();
		this.from = from;
		this.to = to;
		this.m_type = MessageType.CHALLENGE;
		this.opponent = opponent;
	}

	@Override
	public String toString() {
		return (new Gson()).toJson(this);
	}
	
	public String getOpponent(){
		return this.opponent;
	}

}
