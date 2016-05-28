package com.example.jdk8;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

import org.junit.Test;

public class CharsetsTest {
	@Test
	public void test() throws CharacterCodingException{
		Charset charset=Charset.defaultCharset();
		System.out.println(charset.displayName());
		CharsetDecoder decode=charset.newDecoder();
		CharsetEncoder encode=charset.newEncoder();
		CharBuffer buffer=CharBuffer.wrap(new char[]{'你','好'});
		ByteBuffer byteBuffer=encode.encode(buffer);
		System.out.println(Arrays.toString(byteBuffer.array()));
		
	}
}
