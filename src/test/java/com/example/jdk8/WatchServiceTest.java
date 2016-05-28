package com.example.jdk8;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchServiceTest {
	private WatchService watchService;

	public WatchServiceTest(Path path) throws IOException {
		watchService = FileSystems.getDefault().newWatchService();
		path.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
	}

	public void handleEvents() throws InterruptedException {
		while (true) {
			WatchKey key = watchService.take();
			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind kind = event.kind();
				if (kind == OVERFLOW) {// 事件可能lost or discarded
					continue;
				}
				WatchEvent<Path> e = (WatchEvent<Path>) event;
				Path fileName = e.context();
				System.out.printf("Event %s has happened,which fileName is %s%n", kind.name(), fileName);
				if (!key.reset())
					break;
			}
		}
	}

	public static void main(String args[]) throws IOException, InterruptedException {
		if (args.length != 1) {
			System.out.println("请设置要监听的文件目录作为参数");
			System.exit(-1);
		}
		new WatchServiceTest(Paths.get(args[0])).handleEvents();
	}

}
