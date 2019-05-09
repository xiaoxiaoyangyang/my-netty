package com.baizhi.nettytwo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.List;

public class ObjectMessageToMessageEncoder extends MessageToMessageEncoder<Object> {


    /**
     *
     * @param channelHandlerContext
     * @param o 需要编码的对象
     * @param list ：数据帧
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, List<Object> list) throws Exception {
        ByteBuf buf=channelHandlerContext.alloc().buffer();
        buf.writeBytes(SerializationUtils.serialize((Serializable) o));
        list.add(buf);
    }
}
