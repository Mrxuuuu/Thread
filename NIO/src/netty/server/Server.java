package netty.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

public class Server {

	public static void main(String[] args) {
		/**
		 * 1.创建服务类
		 * 2.boos线程监听 woker线程读写
		 * 3.设置BIOscoket工厂
		 * 4.设置管道工厂
		 * 
		 */
		//服务类
		ServerBootstrap bootstrap = new ServerBootstrap();
		//
		Executor bossExecutor=Executors.newCachedThreadPool() ;
		Executor workerExecutor=Executors.newCachedThreadPool();
//		设置工厂
		bootstrap.setFactory(new NioServerSocketChannelFactory(bossExecutor , workerExecutor));
		//设置管道工厂
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				// TODO Auto-generated method stub
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encoder", new StringEncoder());
				pipeline.addLast("HelloHandler", new Hellohandler());
				return pipeline;
			}
		});
		
		bootstrap.bind(new InetSocketAddress(10201));
		
		System.out.println("strat Server...");
		
	}
}
