package com.baizhi.nio2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class Serverbootstrap {
    private static List<SelectionKey> writeQueue=new Vector<>();
    public static void main(String[] args) throws IOException {
        server01();
    }

    public static void server01() throws IOException {
        ServerSocketChannel ss=ServerSocketChannel.open();
        ss.socket().bind(new InetSocketAddress(9999));
        //设置为通道为非阻塞
        ss.configureBlocking(false);
        //创建通道选择器
        Selector selector = Selector.open();
        //注册请求转发事件
        ss.register(selector,SelectionKey.OP_ACCEPT);

        //遍历事件队列
        while(true){
            //通道选择器当中是否有事件
            int select = selector.select();
            if(select>0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    final SelectionKey next = iterator.next();
                    //判断时间的类型
                    if(next.isAcceptable()){
                        ServerSocketChannel channel =(ServerSocketChannel) next.channel();
                        SocketChannel accept = channel.accept();


                        accept.configureBlocking(false);
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        accept.register(selector,SelectionKey.OP_READ,outputStream);
                    }else if(next.isReadable()){
                        SocketChannel socketChannel =(SocketChannel)next.channel();
                        ByteArrayOutputStream outputStream =(ByteArrayOutputStream) next.attachment();
                        ByteBuffer allocate = ByteBuffer.allocate(1024);

                        allocate.clear();
                        int read = socketChannel.read(allocate);
                        allocate.flip();
                        if(read==-1){
                            next.cancel();
                            //selector.select();
                            //socketChannel.register(selector,SelectionKey.OP_WRITE,outputStream);
                           // writeQueue.add(next);
                            new Thread(){
                                @Override
                                public void run() {
                                    writeQueue.add(next);
                                    //打破阻塞
                                    selector.wakeup();
                                }
                            }.start();
                        }else{
                            outputStream.write(allocate.array(),0,read);
                        }
                    }else if(next.isWritable()){
                        SocketChannel socketChannel =(SocketChannel)next.channel();
                        ByteArrayOutputStream outputStream =(ByteArrayOutputStream) next.attachment();
                        System.out.println("服务器收到："+new String(outputStream.toByteArray()));

                        socketChannel.write(ByteBuffer.wrap(new Date().toLocaleString().getBytes()));
                        socketChannel.socket().shutdownOutput();

                        socketChannel.close();
                    }
                    //移出事件队列！=移除注册队列
                    iterator.remove();
                }
            }else{
                while(!writeQueue.isEmpty()){
                    SelectionKey remove = writeQueue.remove(0);
                    remove.channel().register(selector,SelectionKey.OP_WRITE,remove.attachment());
                }
            }
        }
    }
}
