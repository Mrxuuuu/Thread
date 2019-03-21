package threadtest;

public class ProducerAndCustomer {

	// 库存峰值
	private final static int max = 10;
	// 当前库存量
	private int num = 0;
	// 锁对象
	private Object obj = new Object();

	/**
	 * 生产者
	 * 
	 * @param name
	 */
	public void producer(String name) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) { // 同步锁
					synchronized (obj) {
						if (num < max) {
							System.out.println("生产者:" + name + "  " + "正在生产第：" + (num + 1 )+ "个");
							num++;
							System.out.println("当前库存：NUM=" + num);
							// 睡眠1秒
							try {
								Thread.currentThread().sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// 线程唤醒
							obj.notify();
						} else {
							System.out.println("库存已满...生产者等待中...");
							try {
								obj.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				}
			}
		});

		t.start();
	}

	/**
	 * 消费者
	 * 
	 * @param name
	 */
	public void customer(String name) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					synchronized (obj) {
						if (num > 0) {
							System.out.println("消费者: " + name + " " + "正在消费第：" + num + "个");
							num--;
							System.out.println("当前库存：NUM=" + num);
							try {
								Thread.currentThread().sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							obj.notify();
						} else {
							System.out.println("仓库已空..正在等待生产...");
							try {
								obj.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		});

		t.start();
	}

}
