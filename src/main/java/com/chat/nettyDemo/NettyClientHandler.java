package com.chat.nettyDemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 *  Netty 客户端处理业务
 * @author
 * @date 2020/4/22
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    //通道就绪
    public void  channelActive(ChannelHandlerContext ctx){
        System.out.println("client:"+ctx);
        //发送数据
        ctx.writeAndFlush(Unpooled.copiedBuffer("你好呀！", CharsetUtil.UTF_8));
    }

    //读取数据
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        ByteBuf buf = (ByteBuf)msg;
        System.out.println("服务器发来消息，"+buf.toString(CharsetUtil.UTF_8));
    }
}
