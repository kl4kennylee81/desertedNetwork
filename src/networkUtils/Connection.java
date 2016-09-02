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
import java.io.*;
import networkUtils.Message;

public class Connection {

	AsynchronousSocketChannel sChannel;

	Future<Integer> readFuture;
	ByteBuffer readBuffer;
	
	Future<Integer> writeFuture;
	ByteBuffer writeBuffer;

	public Connection(AsynchronousSocketChannel s) throws IOException {
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
	
	public String read() throws IOException {
		if (readFuture.equals(null)){
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
	
	public static String byteBufferToString(ByteBuffer bb){
	      Charset cs = Charset.forName("UTF-8");
	      int limits = bb.limit();
	      byte bytes[] = new byte[limits];
	      bb.get(bytes, 0, limits);
	      String msg = new String(bytes, cs);
	      return msg;
	}

	public Integer write(String msg) throws IOException, InterruptedException, ExecutionException {
		if (writeFuture.equals(null)){
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
