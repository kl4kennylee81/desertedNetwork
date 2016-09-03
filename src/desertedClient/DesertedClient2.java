package desertedClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import networkUtils.ChallengeMessage;
import networkUtils.Connection;
import networkUtils.InGameMessage;
import networkUtils.LobbyMessage;
import networkUtils.Message;
import networkUtils.NormalMessage;
import networkUtils.UsernameMessage;


public class DesertedClient2 {
	
  public static void main(String[] args) throws Exception {

    Connection c = new Connection();
    DesertedClient dc = new DesertedClient(c,false);
    dc.connect.connect("localhost", 8989);
    
    while(true){
    	dc.processState();
    }
    
    
    
    
  }
}