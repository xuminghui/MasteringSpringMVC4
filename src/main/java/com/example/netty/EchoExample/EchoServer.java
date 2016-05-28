package com.example.netty.EchoExample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class EchoServer {
	public void bind(int port) throws Exception {
		//配置服务器端的NIO线程组，专门用于网络事件的处理
		//一个用于服务器接收客户端的连接，另一个用于SocketChannel的网络读写
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();//NIO的启动类
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
					.childHandler(new ChildChannelHandler());
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			//ByteBuf delimiter=Unpooled.copiedBuffer("$_".getBytes());
			//ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
			ch.pipeline().addLast(new FixedLengthFrameDecoder(20));//通过telnet测试
			ch.pipeline().addLast(new StringDecoder());
			ch.pipeline().addLast(new EchoServerHandler());
		}
	}
	public static void main(String[] args) throws Exception{
		new EchoServer().bind(9999);
	}
}
