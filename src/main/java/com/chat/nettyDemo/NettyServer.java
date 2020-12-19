package com.chat.nettyDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *  Netty 服务端
 * @author
 * @date 2020/4/22
 */
public class NettyServer {
    public static void main(String[] args) throws Exception{
        //1.创建线程组接受客户端连接
        EventLoopGroup bossGroup =  new NioEventLoopGroup();

        //2.创建线程组 处理网络操作
        EventLoopGroup workerGroup =  new NioEventLoopGroup();

        //3. 创建服务器端启动助手配置参数
        ServerBootstrap bootstrap =  new ServerBootstrap();
        //4.配置2个线程组
        bootstrap.group(bossGroup,workerGroup)
                //5.使用 NioServerSocketChannel 作为服务器端通道实现
                .channel(NioServerSocketChannel.class)
                //6. 设置线程队列等待连接数量
                .option(ChannelOption.SO_BACKLOG,128)
                //7. 保持活动连接状态
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                //8.创建通道初始化对象
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    public void initChannel(SocketChannel sc){
                        //9. 往 pipeline 自定义的服务器端处理类加进去
                        sc.pipeline().addLast(new NettyServerHandler());
                    }
                });
        System.out.println("==========Server is ready==========");
        //10.绑定端口  非阻塞sync()
        ChannelFuture cf = bootstrap.bind(9999).sync();
        System.out.println("==========Server is starting==========");


        //11 关闭通道（异步） 关闭线程组 ()
        cf.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
