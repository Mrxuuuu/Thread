package netty.Client;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

public class client {

	/**
	 * 1.定义客户端类 2.boss线程监听，worker读写 3.设置NIO工厂 4.设置管道工厂
	 */

	public static void main(String[] args) {
		// 定义客户端类
		ClientBootstrap Bootstrap = new ClientBootstrap();
		// 定义线程
		Executor bossExecutor = Executors.newCachedThreadPool();
		Executor workerExecutor = Executors.newCachedThreadPool();
		// 设置工厂
		Bootstrap.setFactory(new NioClientSocketChannelFactory(bossExecutor, workerExecutor));
		// 设置管道
		Bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

			@Override
			public ChannelPipeline getPipeline() throws Exception {
				// TODO Auto-generated method stub
				// 创建一个管道
				ChannelPipeline Pipeline = Channels.pipeline();
				Pipeline.addLast("decoder", new StringDecoder());
				Pipeline.addLast("encoder", new StringEncoder());
				Pipeline.addLast("hiHandler", new hiHandler());
				return Pipeline;
			}
		});
		// 获得服务端连接
		ChannelFuture connect = Bootstrap.connect(new InetSocketAddress(10201));
		// 获得连接通道
		Channel channel = connect.getChannel();
		System.out.println("连接已建立完成");

		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.println("请输入...");
			channel.write(in.next());
		}

	}
}
