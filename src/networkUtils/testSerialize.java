package networkUtils;

import flexjson.JSONDeserializer;

import flexjson.JSONSerializer;

import networkUtils.Message;
import networkUtils.InGameMessage;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class testSerialize {
	
	public static enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT,
		NONE
	}
	
	int[] coordinates;
	Direction d;
	int hi;
	Message m;
	
	public String toString(){
		
		return (new Gson()).toJson(this);
	}
	
	public testSerialize(int[] arr,Message m){
		this.coordinates = arr;
		this.hi = 0;
		this.m = m;
	}
	
	public static void main(String[] args) {
		ArrayList<String> users = new ArrayList<String>();
		users.add("blaze");
		Message m = new LobbyMessage(users);
		
		String m_string = m.toString();
		
		Message m2 = Message.jsonToMsg(m_string);
		
		System.out.println(m2);
	}

}
