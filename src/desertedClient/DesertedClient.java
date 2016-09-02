package desertedClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

import networkUtils.Connection;
import networkUtils.Message;
import networkUtils.NormalMessage;


public class DesertedClient {
  public static void main(String[] args) throws Exception {
    Connection c = new Connection();
    c.connect("localhost", 8989);
    
    int count = 0;
    while(true){
	    String s = getTextFromUser();
	    Message msg = new NormalMessage(s);
	    while (c.write(msg) == null){
	    }
	    
	    Message response = c.readMsg();
	    while(response == null){
	    	response = c.readMsg();
	    }
	    count++;
	    System.out.printf("%d:%s\n",count,response.getMessage());
    }
    
    
  }

private static String getTextFromUser() throws Exception{
    System.out.print("Please enter a  message  (Bye  to quit):");
    BufferedReader consoleReader = new BufferedReader(
        new InputStreamReader(System.in));
    String msg = consoleReader.readLine();
    return msg;
  }
}