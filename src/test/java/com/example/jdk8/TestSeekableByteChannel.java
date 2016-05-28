package com.example.jdk8;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class TestSeekableByteChannel {

	public static void main(String[] args) throws IOException {
		SeekableByteChannel  channel=Files.newByteChannel(Paths.get("test.txt"), EnumSet.of(StandardOpenOption.WRITE));
		ByteBuffer buffer=ByteBuffer.wrap(new byte[]{'1','2'});
		channel.position(channel.size());
		channel.write(buffer);
		channel.close();
	}

}
