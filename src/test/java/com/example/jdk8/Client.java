package com.example.jdk8;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

public class Client {
	private final AsynchronousSocketChannel client;

	public Client() throws IOException {
		client = AsynchronousSocketChannel.open();
	}

	public void sendMsg() throws InterruptedException, ExecutionException {
		client.connect(new InetSocketAddress("localhost", 8888));
		client.write(ByteBuffer.wrap("test".getBytes()));
	}

	public static void main(String... args) throws Exception {
		Client client = new Client();
		client.sendMsg();
		Thread.sleep(100000000);
	}

}
