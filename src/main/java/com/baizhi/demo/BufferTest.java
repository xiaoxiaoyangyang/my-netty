package com.baizhi.demo;

import java.nio.ByteBuffer;

public class BufferTest {
    public static void main(String[] args) {
        //创建ByteBuffer
        byte[] bytes=new byte[5];
        ByteBuffer buffer=ByteBuffer.wrap(bytes);
        showMetics("init ",buffer);
        buffer.clear();
        buffer.put((byte)'a');
        buffer.put((byte)'b');
        showMetics("w 2 bytes ",buffer);
//        buffer.position(0);
//        buffer.limit(2);
        buffer.flip();
        while(buffer.hasRemaining()){
            System.out.println((char)buffer.get());
        }
        showMetics("read 2 bytes ",buffer);
        buffer.clear();
        buffer.put((byte)'c');
        showMetics("write 1 bytes ",buffer);
        buffer.flip();
        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
    public static void showMetics(String msg,ByteBuffer buffer){
        System.out.println(msg+"\t"+buffer.position()+" "+buffer.limit()+" "+buffer.capacity());
    }
}
