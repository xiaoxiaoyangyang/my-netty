package com.baizhi.demo;

import io.netty.buffer.ByteBuf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {
    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\aa.png");
        FileOutputStream out = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\bb.png");
        //获取通道
        FileChannel inChannel =in.getChannel();
        FileChannel outChannel = out.getChannel();

        //创建缓冲区
        ByteBuffer buffer=ByteBuffer.allocate(1024);

        while(true){
            buffer.clear();
            int n = inChannel.read(buffer);
            if(n==-1) {
                break;
            }
            buffer.flip();
            outChannel.write(buffer);
        }

        inChannel.close();
        outChannel.close();
        in.close();
        out.close();


    }
//    public static void mm() throws IOException {
//        FileInputStream inputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\aa.png");
//        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\bb.png");
//
//        FileChannel channel = inputStream.getChannel();
//        FileChannel channel1 = outputStream.getChannel();
//
//        ByteBuffer wrap = ByteBuffer.allocate(1024);
//
//        while (true){
//            wrap.clear();
//
//            int read = channel.read(wrap);
//            if(read==-1){
//                break;
//            }
//            wrap.flip();
//            channel1.write(wrap);
//        }
//
//        channel.close();
//        channel1.close();
//        inputStream.close();
//        outputStream.close();
//    }
}
