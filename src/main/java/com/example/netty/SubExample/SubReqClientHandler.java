package com.example.netty.SubExample;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqClientHandler extends ChannelHandlerAdapter {
	private static final Logger logger=Logger.getLogger(SubReqClientHandler.class);
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for(int i=0;i<10;i++){
			ctx.write(subReq(i));
		}
		ctx.flush();
	}
	private SubscribeReq subReq(int i){
		SubscribeReq req=new SubscribeReq();
		req.setProductName("productName");
		req.setSubReqID(i);
		req.setUserName("xuminghui");
		return req;
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("receive:  "+msg);
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
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
