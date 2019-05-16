package com.baizhi.nio;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.sun.xml.internal.ws.addressing.WsaTubeHelperImpl;
import org.apache.commons.lang3.ObjectUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class Bootstrap {
    public static void main9(String[] args) throws IOException {
//        1.	创建Socket s=new Socket();
        SocketChannel s=SocketChannel.open();
//        2.	连接服务器s.connect(new InetSocketAddress(ip,9999));
        s.connect(new InetSocketAddress("10.5.1.221",12345));
//        3.	使用流操作请求和响应 套路IO
        //①发请求
        String res="AT+STACH0=1,3\r\n";
        s.write(ByteBuffer.wrap(res.getBytes()));
        s.socket().shutdownOutput();//告知服务请求内容发送结束
        //②读取服务器响应
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();//创建一个输出流
        while(true){
            buffer.clear();
            int n = s.read(buffer);
            if(n==-1) break;
            buffer.flip();
            baos.write(buffer.array(),0,n);
        }

        System.out.println("客户端收到："+new String(baos.toByteArray()));

//        4.	关闭socket资源
        s.close();
    }

    public static void main3(String[] args) throws IOException {
        SocketChannel open = SocketChannel.open();
        open.connect(new InetSocketAddress("10.5.1.221",12345));
        String st="AT+STACH0=1,3\n";
        open.write(ByteBuffer.wrap(st.getBytes()));
        open.socket().shutdownOutput();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (true){
            allocate.clear();
            int read = open.read(allocate);
            if(read==-1) {
                break;
            }
            allocate.flip();
            outputStream.write(allocate.array(),0,read);
        }
        System.out.println("客户端收到的信息-------------"+new String(outputStream.toByteArray()));
    }

    public static void main(String[] args) throws IOException {
        SocketChannel open = SocketChannel.open();
        open.connect(new InetSocketAddress("10.5.1.221",12345));
        open.write(ByteBuffer.wrap(("AT+STACH0=1,3\n").getBytes()));
        open.socket().shutdownOutput();

        ByteBuffer allocate = ByteBuffer.allocate(1024);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (true){
            allocate.clear();
            int write = open.read(allocate);
            if(write==-1) {
                break;
            }
            outputStream.write(allocate.array(),0,write);
        }
        System.out.println("你还好码"+new String(outputStream.toByteArray()));
        open.close();
    }

    public static void main10(String[] args) throws IOException {
        SocketChannel open = SocketChannel.open();
        open.connect(new InetSocketAddress("localhost",9999));
        open.write(ByteBuffer.wrap(("ni hao ya").getBytes()));
        open.socket().shutdownOutput();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ServerSocketChannel open1 = ServerSocketChannel.open();
        open1.socket().bind(new InetSocketAddress(9999));
        SocketChannel accept = open1.accept();
    }
}

