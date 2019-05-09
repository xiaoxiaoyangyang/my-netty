package com.baizhi.nettytwo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.apache.commons.lang3.SerializationUtils;

import java.nio.ByteBuffer;
import java.util.List;

public class ObjectMessageToMessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byte[] bytes=new byte[byteBuf.readableBytes()];
        //将byteBuf数据写入字节数组
        byteBuf.readBytes(bytes);
        Object o = SerializationUtils.deserialize(bytes);
        list.add(o);
    }
}
