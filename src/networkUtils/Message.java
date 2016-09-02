package networkUtils;

import flexjson.JSONSerializer;
import flexjson.locators.TypeLocator;
import flexjson.JSONDeserializer;

public abstract class Message {
	
	enum MessageType {NORMAL,USERNAME,LOBBY,CHALLENGE,INGAME,BACK};
	
	MessageType m_type;
	
	public Message(){
		m_type = MessageType.NORMAL;
	}
	
	public String getMessage(){
		return "";
	}
	
	public static Message jsonToMsg(String s){
		@SuppressWarnings("unchecked")
		Message m = new JSONDeserializer<Message>()
				.use(null, new TypeLocator<MessageType>("m_type")
                .add(Message.MessageType.NORMAL, NormalMessage.class)
                .add(Message.MessageType.USERNAME, UsernameMessage.class)
                .add(Message.MessageType.CHALLENGE, ChallengeMessage.class)
                .add(Message.MessageType.INGAME, InGameMessage.class)
                .add(Message.MessageType.LOBBY, LobbyMessage.class)
                .add(Message.MessageType.BACK, BackMessage.class))
                .deserialize(s);
		return m;	    
	}
}
