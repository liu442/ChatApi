package com.chat;

import com.chat.conf.netty.WSServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 启动 netty 服务
 * 监听spring boot  启动完成后 启动netty 服务
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.err.println("启动 netty 服务。。");
		if (event.getApplicationContext().getParent() == null) {
			System.err.println("看看是否启动了2次 。。");
			try {
				WSServer.getInstance().start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
