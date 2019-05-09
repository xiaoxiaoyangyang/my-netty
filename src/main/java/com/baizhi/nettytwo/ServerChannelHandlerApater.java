package com.baizhi.nettytwo;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;


public class ServerChannelHandlerApater extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("错了："+cause.getMessage());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        User msg1 = (User) msg;
        System.out.println(msg1.toString());
//        ChannelFuture channelFuture = ctx.writeAndFlush(msg);
//        channelFuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
//        channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
//        //关闭通道
//        if(msg instanceof Close){
//            channelFuture.addListener(ChannelFutureListener.CLOSE);
//        }
    }
}
