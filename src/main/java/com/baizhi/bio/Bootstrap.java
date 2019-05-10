package com.baizhi.bio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;

public class Bootstrap {
    public static void main1(String[] args) throws IOException {
//        1.	创建Socket s=new Socket();
        Socket s=new Socket();
//        2.	连接服务器s.connect(new InetSocketAddress(ip,9999));
        s.connect(new InetSocketAddress("localhost",9999));
//        3.	使用流操作请求和响应 套路IO
        //①发请求
        OutputStream res = s.getOutputStream();
        PrintWriter pw=new PrintWriter(res);
        pw.println("你好，服务器我是客户端2！");
        pw.flush();
        s.shutdownOutput();//告知服务请求内容发送结束

        //②读取服务器响应
        InputStream req = s.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(req);
        BufferedReader br=new BufferedReader(inputStreamReader);
        StringBuilder sb=new StringBuilder();
        String line=null;
        while((line=br.readLine())!=null){
            sb.append(line);
        }
        System.out.println("客户端收到："+sb.toString());

//        4.	关闭socket资源
        s.close();
    }

    public static void main6(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",8888));
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println("你好，我是客户端");
        printWriter.flush();
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line=null;
        while ((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 1, 2, 2, 4, 1, 4, 5, 5, 2,2,2);
        Map<String, List<Integer>> map = new HashMap<String,List<Integer>>();
        int mark=integers.get(0);
        List<Integer> list = new ArrayList<>();
        for(Integer in:integers){
            if(in==mark){
                list.add(in);
            }else{
                mark=in;
                String s = UUID.randomUUID().toString().replaceAll("-", "");
                map.put(s,list);
                list = new ArrayList<>();
                list.add(in);
            }
        }
        String s1 = UUID.randomUUID().toString().replaceAll("-", "");
        map.put(s1,list);
        map.forEach((s, integers1) -> {
            System.out.print(s);
            System.out.print(integers1.toString());
            System.out.println("-------------------->");
        });
    }
}
