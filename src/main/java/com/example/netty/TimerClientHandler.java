package com.example.netty;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimerClientHandler extends ChannelHandlerAdapter {
	private static final Logger logger=Logger.getLogger(TimerClientHandler.class);
	private byte[] req;
	private int counter;
	public TimerClientHandler(){ 
		req=("query time order"+System.getProperty("line.separator")).getBytes();
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf message=null;
		for(int i=0;i<100;i++){
			message=Unpooled.buffer(req.length);
			message.writeBytes(req);
			ctx.writeAndFlush(message);
		}
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		/*ByteBuf buf=(ByteBuf)msg;
		byte[] req=new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body=new String(req,"UTF-8");*/
		String body=(String)msg;
		System.out.println("now is "+body+"; the counter is : "+ ++counter);
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.warn("exception: "+cause.getMessage());
		ctx.close();
	}
	public static void main(String[] args) {
		System.out.println(System.getProperties());
		
	}
}
