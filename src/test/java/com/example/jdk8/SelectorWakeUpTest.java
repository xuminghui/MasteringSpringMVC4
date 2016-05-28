package com.example.jdk8;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SelectorWakeUpTest {
	public static void main(String[] args) throws Exception {

		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		final Selector selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		ExecutorService es = Executors.newCachedThreadPool();
		es.submit(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					selector.wakeup();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		while (true) {
			selector.select();
			System.out.println("收到请求");
			Set<SelectionKey> keys = selector.selectedKeys();
			System.out.println(keys.size());
			Iterator<SelectionKey> iterator = keys.iterator();
			while (iterator.hasNext()) {
				SocketChannel channel = (SocketChannel) iterator.next().channel();
				iterator.remove();
			}

		}
	}
}
