package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
	private Selector selector;
	
	//1.初始化服务
	public void initServer(int port) throws IOException
	{
		
		//1.先获得一个通道
		ServerSocketChannel socketChannel = ServerSocketChannel.open();
//		设置非阻塞
		socketChannel.configureBlocking(false);
		//将通道绑定到对应端口
		socketChannel.socket().bind(new InetSocketAddress(port));
		//2.获得一个通道管理器
		selector = Selector.open();
		//3.注册绑定通道和通道管理器
//		SelectionKey.OP_ACCEPT  当事件到达时，通道管理器会返回，否则一直阻塞
		socketChannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	//2.请求监听
	public void serverlisten() throws IOException{
		//无限循环监听
		 while(true)
		 {//监听到事件到达，返回
		  selector.select();
		  //使用迭代器轮询监听
		  Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
		  while(iterator.hasNext())
		  {
			 //先获取selectionKey
			  SelectionKey selectionKey = iterator.next();
//			  再移除已经访问的
			  iterator.remove();
			  //根据selectionKey来处理请求
			  handler(selectionKey);
		  }
		 }
	}
	
	////3.处理请求
	public void handler(SelectionKey key) throws IOException {
//		 3.1:处理接受请求
		if(key.isAcceptable())
		{//允许接受的则先处理接收请求
			//提取通道信息
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			//获取客户端连接通道
			SocketChannel channel = server.accept();
			channel.configureBlocking(false);//设置非阻塞
			//设置读权限
			channel.register(selector, SelectionKey.OP_READ);	
		}
		if(key.isReadable()) {
//			3.2：处理读请求
			//
			SocketChannel channel = (SocketChannel) key.channel();
			//创建缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			int read = channel.read(buffer);
			//if
			if(read>0)
			{
				//
				byte[] bs = buffer.array();
				String msg=new String(bs);
				System.out.println("收到消息："+msg);
				
				ByteBuffer src=ByteBuffer.wrap(bs);
				channel.write(src);
			}
			else {
				System.out.println("没有服务，已关闭");
				key.cancel();
			}
		}
	}
	

//  
	public static void main(String[] args) throws IOException {
		NIOServer nIOServer=new NIOServer();
		nIOServer.initServer(10101);
		nIOServer.serverlisten();
	
	}
}
