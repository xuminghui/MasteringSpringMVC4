package com.example.jdk8;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class FilesTest {
	@Test
	public void testCopy() throws Exception{
		Path path = Paths.get("F:\\", "test.html");
		URI u = URI.create("http://java.sun.com/");
		try (InputStream in = u.toURL().openStream()) {
			Files.copy(in, path);
		}
		

	}
}
