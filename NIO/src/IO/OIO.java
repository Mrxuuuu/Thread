package IO;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OIO {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();

		try {
//			ServerSocket在10101端口监听
			ServerSocket server = new ServerSocket(10101);
			System.out.println("连接到服务器");

			while (true) {
				// 从端口监听到socket并获取到socket对象
				Socket accept = server.accept();
				// 处理socket消息
				executorService.execute(new Runnable() {

					@Override
					public void run() {
						// 多线程情况下监听
						// TODO Auto-generated method stub
						try {
							handler(accept);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void handler(Socket accept) throws IOException {
		// TODO Auto-generated method stub
		// 读socket里封装的字节流信息
		InputStream is = accept.getInputStream();
		// 创建字节数组
		byte[] by = new byte[1024];
		//
		while (true) {
			//
			int len = is.read(by);
			// 如果不是最后一个字节
			if (len != -1) {
				System.out.println(new String(by, 0, len));
			}

		}

	}
}
