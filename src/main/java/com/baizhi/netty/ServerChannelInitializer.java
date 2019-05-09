package com.baizhi.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @author guozhiyang_vendor
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
//        pipeline.addLast(new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
//        pipeline.addLast(new ObjectMessageToMessageDecoder());
//        pipeline.addLast(new LengthFieldPrepender(2));
//        pipeline.addLast(new ObjectMessageToMessageEncoder());
        pipeline.addLast(new ServerChannelHandlerApater());
    }
}
