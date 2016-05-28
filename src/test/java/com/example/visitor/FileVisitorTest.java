package com.example.visitor;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

import com.google.common.base.Objects;

public class FileVisitorTest extends SimpleFileVisitor<Path>{
	private void find(Path dir){
		System.out.printf("访问-%s:%s%n",Files.isDirectory(dir)?"目录":"文件",dir.getFileName());
	}
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		find(dir);
		return FileVisitResult.CONTINUE;
	}
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		find(dir);
		return FileVisitResult.CONTINUE;
	}
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		find(file);
		if(Objects.equal(".svn", file.getFileName())){
			System.out.println(file.toString());
		}
		return FileVisitResult.CONTINUE;
	}
	public static void main(String[] args) {
		try {
			/**
			 * jdk1.5新特性
			 */
			EnumSet<FileVisitOption> set=EnumSet.noneOf(FileVisitOption.class);
			set=EnumSet.allOf(FileVisitOption.class);
			Files.walkFileTree(Paths.get(args[0]),set, Integer.MAX_VALUE,new FileVisitorTest());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
