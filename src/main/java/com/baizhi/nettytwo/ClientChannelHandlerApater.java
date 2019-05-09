package com.baizhi.nettytwo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

public class ClientChannelHandlerApater extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("错了~ "+cause.getMessage());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    //    User user = new User("xiaoming","123456");
        String st="AT+STACH0=1,3\r\n";
        ctx.writeAndFlush(st);
//        ChannelFuture channelFuture = ctx.writeAndFlush(new Close());
//        channelFuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
//        channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端收到："+msg);
    }
}

