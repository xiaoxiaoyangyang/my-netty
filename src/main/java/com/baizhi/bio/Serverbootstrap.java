package com.baizhi.bio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Serverbootstrap {
    public static void main(String[] args) throws IOException {
        server03();
    }
    public static void server03() throws IOException {
//        1.	创建ServerSocket ss=new ServerSocket();
        ServerSocket ss=new ServerSocket();
//        2.	绑定监听端口ss.bind(new InetSocketAddress(9999));
        ss.bind(new InetSocketAddress(9999));
//        3.	等待客户端请求Socket s=ss.accept();(req,res)
        ExecutorService threadPool = Executors.newFixedThreadPool(150);
        while(true){
            System.out.println("等待请求到来...");
            final Socket s = ss.accept();
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("处理请求...");
                        //        4.	使用流操作请求和响应 套路IO
                        //①读请求
                        InputStream req = s.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(req);
                        BufferedReader br=new BufferedReader(inputStreamReader);
                        StringBuilder sb=new StringBuilder();
                        String line=null;
                        while((line=br.readLine())!=null){
                            sb.append(line);
                        }
                        System.out.println("服务器收到："+sb.toString());
                        //②写响应
                        OutputStream res = s.getOutputStream();
                        PrintWriter pw=new PrintWriter(res);
                        pw.println("time:"+new Date().toLocaleString());
                        pw.flush();
                        s.shutdownOutput();//告知客户端服务器写完
                        //        5.	关闭socket资源
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
    public static void server02() throws IOException {
//        1.	创建ServerSocket ss=new ServerSocket();
        ServerSocket ss=new ServerSocket();
//        2.	绑定监听端口ss.bind(new InetSocketAddress(9999));
        ss.bind(new InetSocketAddress(9999));
//        3.	等待客户端请求Socket s=ss.accept();(req,res)
         while(true){
             System.out.println("等待请求到来...");
             Socket s = ss.accept();
             System.out.println("处理请求...");
//        4.	使用流操作请求和响应 套路IO
             //①读请求
             InputStream req = s.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(req);
             BufferedReader br=new BufferedReader(inputStreamReader);
             StringBuilder sb=new StringBuilder();
             String line=null;
             while((line=br.readLine())!=null){
                 sb.append(line);
             }
             System.out.println("服务器收到："+sb.toString());
             //②写响应
             OutputStream res = s.getOutputStream();
             PrintWriter pw=new PrintWriter(res);
             pw.println("time:"+new Date().toLocaleString());
             pw.flush();
             s.shutdownOutput();//告知客户端服务器写完
//        5.	关闭socket资源
             s.close();
         }

    }
    public static void server0() throws IOException {
//        1.	创建ServerSocket ss=new ServerSocket();
        ServerSocket ss=new ServerSocket();
//        2.	绑定监听端口ss.bind(new InetSocketAddress(9999));
        ss.bind(new InetSocketAddress(9999));
//        3.	等待客户端请求Socket s=ss.accept();(req,res)
        System.out.println("等待请求到来...");
        Socket s = ss.accept();
        System.out.println("处理请求...");
//        4.	使用流操作请求和响应 套路IO
        //①读请求
        InputStream req = s.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(req);
        BufferedReader br=new BufferedReader(inputStreamReader);
        StringBuilder sb=new StringBuilder();
        String line=null;
        while((line=br.readLine())!=null){
            sb.append(line);
        }
        System.out.println("服务器收到："+sb.toString());
        //②写响应
        OutputStream res = s.getOutputStream();
        PrintWriter pw=new PrintWriter(res);
        pw.println("time:"+new Date().toLocaleString());
        pw.flush();
        s.shutdownOutput();//告知客户端服务器写完
//        5.	关闭socket资源
        s.close();
    }
}
