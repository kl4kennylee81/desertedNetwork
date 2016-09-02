package networkUtils;

import java.net.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.io.*;
import networkUtils.Message;

public class Connection {

	SocketChannel sChannel;
	Selector selector;
	
	
	boolean isReading;
	boolean isWritable;
	
	

	public Connection(SocketChannel s, Selector select) throws IOException {
		sChannel = s;

		// set nonblocking
		sChannel.configureBlocking(false);
		selector = select;
	}

	public SocketChannel getSocketChannel() {
		return sChannel;
	}
	
	public Selector getSelector(){
		return selector;
	}

	public int getRemotePort() {
		return getSocketChannel().socket().getPort();
	}

	public InetAddress getRemoteAddress() {
		return getSocketChannel().socket().getInetAddress();
	}

	public InetAddress getLocalAddress() {
		return getSocketChannel().socket().getLocalAddress();
	}

	public int getLocalPort() {
		return getSocketChannel().socket().getLocalPort();
	}

	public void closeConnection() throws IOException {
		getSocketChannel().close();
	}
//
//	public String read() throws IOException {
//		if (!isReading){
//			isReading = true;
//			getSocketChannel().register(getSelector(), SelectionKey.OP_READ);
//		}
//	}
//
//	public int write(String m) throws IOException {
//	}
}
