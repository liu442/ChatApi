package com.chat.nettyDemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Netty 服务端处理业务
 * @author
 * @date 2020/4/22
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //重写读取数据方法
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        System.out.println("server:"+ctx);
        ByteBuf buf = (ByteBuf)msg;
        System.out.println("客户端发来消息，"+buf.toString(CharsetUtil.UTF_8));
    }
    //重写读取完毕 回复消息
    public void channelReadComplete(ChannelHandlerContext ctx){
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务端回信",CharsetUtil.UTF_8));
    }
    //重写异常发生 关掉
    public void  exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        ctx.close();
    }
}
