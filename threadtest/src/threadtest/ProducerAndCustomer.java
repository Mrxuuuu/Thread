package threadtest;

public class ProducerAndCustomer {

	// ����ֵ
	private final static int max = 10;
	// ��ǰ�����
	private int num = 0;
	// ������
	private Object obj = new Object();

	/**
	 * ������
	 * 
	 * @param name
	 */
	public void producer(String name) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) { // ͬ����
					synchronized (obj) {
						if (num < max) {
							System.out.println("������:" + name + "  " + "���������ڣ�" + (num + 1 )+ "��");
							num++;
							System.out.println("��ǰ��棺NUM=" + num);
							// ˯��1��
							try {
								Thread.currentThread().sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// �̻߳���
							obj.notify();
						} else {
							System.out.println("�������...�����ߵȴ���...");
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
	 * ������
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
							System.out.println("������: " + name + " " + "�������ѵڣ�" + num + "��");
							num--;
							System.out.println("��ǰ��棺NUM=" + num);
							try {
								Thread.currentThread().sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							obj.notify();
						} else {
							System.out.println("�ֿ��ѿ�..���ڵȴ�����...");
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
