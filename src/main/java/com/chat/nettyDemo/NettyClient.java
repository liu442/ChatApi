package com.chat.nettyDemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Netty 客户端
 * @author
 * @date 2020/4/22
 */
public class NettyClient {

    public static void main(String[] args) throws Exception{
        //1.创建线程组
        EventLoopGroup group =  new NioEventLoopGroup();
        //2.创建客户端启动
        Bootstrap bootstrap=new Bootstrap();
        //3.设置线程组
        bootstrap.group(group)
                //4.设置客户端通道实现类
                .channel(NioSocketChannel.class)
                //5.创建通道初始化对象
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected  void initChannel(SocketChannel socketChannel) throws Exception{
                        //6.添加自定义handler
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });
        //7 启动客户端连接服务器端(异步非阻塞)
        ChannelFuture cf = bootstrap.connect("127.0.0.1",9999).sync();
        //8. 关闭连接(异步非阻塞)
        cf.channel().closeFuture().sync();
    }
}
