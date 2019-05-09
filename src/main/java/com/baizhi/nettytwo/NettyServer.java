package com.baizhi.nettytwo;

import com.baizhi.nettytwo.ServerChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //1.创建服务启动引导
        ServerBootstrap sbt=new ServerBootstrap();
        //2.配置线程池组 boss worker
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup work=new NioEventLoopGroup();
        //3.配置线程池组
        sbt.group(boss,work);
        //4.设置服务器实现
        sbt.channel(NioServerSocketChannel.class);
        //5.初始化通信管道
        sbt.childHandler(new ServerChannelInitializer());
        //保持心跳  临时的存储大小默认是50
        sbt.option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
        //6.绑定端口启动服务
        System.out.println("服务器启动了1。。。");
        ChannelFuture channelFuture = sbt.bind(12000).sync();
        //7.关闭SocketChannel
        channelFuture.channel().closeFuture().sync();
        //8.关闭线程资源
        boss.shutdownGracefully();
        work.shutdownGracefully();

    }
}