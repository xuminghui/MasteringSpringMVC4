package com.example.jdk8;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AIOTest {
	public static final int byteCount = 10000000;
	public static final int bufferCount = 100;

	public static void main(String[] args) throws UnsupportedEncodingException {
		int cpu=Runtime.getRuntime().availableProcessors();
		System.out.println(cpu);
		ExecutorService es = Executors.newFixedThreadPool(2);

		try {
			new AIOTest().writeFileAsyn("large.txt", es);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public static void a() {
		String sendString = "你好,服务器. ";
		ByteBuffer sendBuffer = ByteBuffer.wrap(sendString.getBytes("UTF-8"));
		// 从ByteBuffer中读出String
		Charset cs = Charset.forName("UTF-8");
		byte[] bs = new byte[sendBuffer.limit()];
		sendBuffer.get(bs);
		String news = new String(bs, cs);
		// String news = new String(sendBuffer.array(), cs);

	}*/

	public void writeFileAsyn(String fileName, ExecutorService es) throws Exception {
		
		Path path = Paths.get("largeFile.txt");
		AsynchronousFileChannel channel = AsynchronousFileChannel.open(path,
				EnumSet.of(StandardOpenOption.WRITE), es);
		ByteBuffer[] buffers = new ByteBuffer[bufferCount];
		long start=System.currentTimeMillis();
		for (int i = 0; i < bufferCount; i++) {
			buffers[i] = ByteBuffer.allocate(byteCount);
			buffers[i].put(fillData(i+1));
			buffers[i].flip();
			channel.write(buffers[i], i * byteCount);
		}
		Thread.sleep(1000);
		es.shutdown();
		es.awaitTermination(1000, TimeUnit.SECONDS);
		channel.close();
		System.out.println(System.currentTimeMillis()-start);
	}

	private byte[] fillData(int i) {
		String content="行数:"+i+"------------可编辑，系统提供默认时间，默认开售时间为 当前系统时间\n";
		return content.getBytes();
	}
}
