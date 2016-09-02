package networkUtils;

import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.*;
import networkUtils.Message;

public class Connection {

	private static AtomicInteger atomic_int = new AtomicInteger(0);
	
	int id;
	AsynchronousSocketChannel sChannel;

	Future<Integer> readFuture;
	ByteBuffer readBuffer;
	
	Future<Integer> writeFuture;
	ByteBuffer writeBuffer;
	
	public Connection() throws IOException{
    	id = atomic_int.incrementAndGet();
		sChannel = AsynchronousSocketChannel.open();
		readFuture = null;
		readBuffer = ByteBuffer.allocate(2048);
		
		writeFuture = null;
		writeBuffer =  ByteBuffer.allocate(2048);
	}

	public Connection(AsynchronousSocketChannel s) throws IOException {
    	id = atomic_int.incrementAndGet();
		sChannel = s;
		readFuture = null;
		readBuffer = ByteBuffer.allocate(2048);
		
		writeFuture = null;
		writeBuffer =  ByteBuffer.allocate(2048);
	}

	public AsynchronousSocketChannel socketChannel() {
		return sChannel;
	}

	public SocketAddress getRemoteAddress() throws IOException {
		return socketChannel().getRemoteAddress();
	}

	public SocketAddress getLocalAddress() throws IOException {
		return socketChannel().getLocalAddress();
	}

	public void closeConnection() throws IOException {
		socketChannel().close();
	}
	
	public boolean connect(String host,int port){
	    SocketAddress saddr = new InetSocketAddress(host,port);
	    return connect(saddr);
	}
	
	public boolean connect(SocketAddress saddr){
	    Future<Void> result = sChannel.connect(saddr);
	    try {
			result.get();
			return true;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String read() throws IOException {
		if (readFuture != null){
			if (readFuture.isDone()) {
			     String s = byteBufferToString(readBuffer);
			     readFuture = null;
			     return s;
			}
			else {
				return null;
			}
		}
		else {
			readBuffer.clear();
			readFuture = sChannel.read(readBuffer);
			
			if (readFuture.isDone()) {
			     String s = byteBufferToString(readBuffer);
			     readFuture = null;
			     return s;
			}
			else {
				return null;
			}
		}
	}
	
	public Message readMsg() throws IOException{
		String s = this.read();
		Message msg = Message.jsonToMsg(s);
		return msg;
	}
	
	public static String byteBufferToString(ByteBuffer bb){
	      Charset cs = Charset.forName("UTF-8");
	      int limits = bb.limit();
	      byte bytes[] = new byte[limits];
	      bb.rewind();
	      bb.get(bytes, 0, limits);
	      bb.rewind();
	      String msg = new String(bytes, cs);
	      return msg;
	}
	
	public Integer write(Message msg) throws IOException, InterruptedException, ExecutionException{
		return write(msg.getMessage());
	}

	public Integer write(String msg) throws IOException, InterruptedException, ExecutionException {
		if (writeFuture != null){
			if (writeFuture.isDone()){
				int bytes_written = writeFuture.get();
				writeFuture = null;
				return bytes_written;
			}
			else {
				return null;
			}
		}
		else {
		    writeBuffer.clear();
		    Charset cs = Charset.forName("UTF-8");
		    byte[] data = msg.getBytes(cs);
		    writeBuffer.put(data);
		    writeBuffer.flip();
			writeFuture = sChannel.write(writeBuffer);
			
			if (writeFuture.isDone()){
				int bytes_written = writeFuture.get();
				writeFuture = null;
				return bytes_written;
			}
			else {
				return null;
			}
		}
	}
}
