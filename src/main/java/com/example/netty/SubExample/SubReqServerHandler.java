package com.example.netty.SubExample;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		SubscribeReq req=(SubscribeReq)msg;
		ctx.writeAndFlush(resp(req.getSubReqID()));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
	private SubscribeResp resp(int reqId){
		SubscribeResp resp=new SubscribeResp();
		resp.setSubReqID(reqId);
		resp.setRespCode("0");
		return resp;
	}
}
