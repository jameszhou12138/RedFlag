import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class RedFlag extends JFrame {
	//睡眠时间
	private final long sleepTime = 50;
	//状态
	private boolean status = true;
	//计时器
	TimeController timeController = new TimeController(0, false);
	//拦截器数量上限
	private final int MAX_INTERCEPTOR_NUMBER = 10;
	//总发射拦截器数量
	private int totalInterceptorNumber = 0;
	//画布
	Canvas canvas;
	//计时标签
	JLabel timeLabel = new JLabel("time(s):");
	//计时文本
	JTextField timeText = new JTextField("0");
	//计数标签
	JLabel interceptorLabel = new JLabel("count(s):");
	//计数文本
	JTextField interceptorText = new JTextField("0/0");
	//开始按钮
	JButton startButton = new JButton("start");
	//结束按钮
	JButton stopButton = new JButton("stop");
	//拦截按钮
	JButton interceptButton = new JButton("intercept");
	//加速按钮
	JButton accelerateButton = new JButton("accelerate");
	//减速按钮
	JButton decelerateButton = new JButton("decelerate");
	//控制面板
	JPanel controlPanel = new JPanel();

	public RedFlag(int width, int height) {
		//设置可视
		this.setVisible(true);
		//JFrame关闭后退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置标题
		this.setTitle("Red Flag 9 -- Developer: James Zhou");
		//设置窗口大小
		this.setSize(width, height);
		//打开后屏幕居中
		this.setLocationRelativeTo(getOwner());
		//清空布局管理器
		this.setLayout(null);

		//添加画布
		canvas = new Canvas(width, height - 80);
		canvas.setBounds(0, 0, width, height - 80);
		canvas.setBackground(Color.WHITE);
		this.add(canvas);

		//添加控制面板
		controlPanel.setBounds(0, height - 80, width, 40);
		this.add(controlPanel);

		//清空控制面板布局管理器
		controlPanel.setLayout(null);
		//控制面板添加计时标签
		timeLabel.setBounds(10, 10, 50, 25);
		controlPanel.add(timeLabel);
		//控制面板添加计时文本
		timeText.setBounds(70, 10, 50, 25);
		timeText.setEnabled(false);
		controlPanel.add(timeText);
		//控制面板添加开始按钮
		startButton.setBounds(130, 10, 80, 25);
		controlPanel.add(startButton);
		//控制面板添加加速按钮
		accelerateButton.setBounds(220, 10, 100, 25);
		controlPanel.add(accelerateButton);
		//控制面板添加拦截按钮
		interceptButton.setBounds(330, 10, 200, 25);
		controlPanel.add(interceptButton);
		//控制面板添加减速按钮
		decelerateButton.setBounds(540, 10, 100, 25);
		controlPanel.add(decelerateButton);
		//控制面板添加暂停按钮
		stopButton.setBounds(650, 10, 80, 25);
		stopButton.setEnabled(false);
		controlPanel.add(stopButton);
		//控制面板添加计时标签
		interceptorLabel.setBounds(740, 10, 60, 25);
		controlPanel.add(interceptorLabel);
		//控制面板添加计时文本
		interceptorText.setBounds(810, 10, 50, 25);
		interceptorText.setEnabled(false);
		controlPanel.add(interceptorText);
		//计时器
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timeController, 0L, 1000L);

		//开始按钮监听事件
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				startButton.setEnabled(false);
				stopButton.setEnabled(true);
				timeController.setStatus(true);
				timeController.run();
				status = true;
				runThreads();
			}
		});
		//加速按钮监听事件
		accelerateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (canvas.getMissileSpeed() == 16) {
					accelerateButton.setEnabled(false);
					return;
				}
				accelerateButton.setEnabled(true);
				decelerateButton.setEnabled(true);
				int missileSpeed = canvas.getMissileSpeed() * 2;
				int interceptorSpeed = canvas.getInterceptorSpeed() * 2;
				canvas.adjustSpeed(missileSpeed, interceptorSpeed);
			}
		});
		//拦截按钮监听事件
		interceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (startButton.isEnabled()) {
					return;
				}
				if (canvas.interceptors.size() < MAX_INTERCEPTOR_NUMBER) {
					interceptButton.setEnabled(true);
					canvas.interceptors.add(new Interceptor(canvas.getWidth() / 2, canvas.getHeight() - 10, canvas.getInterceptorSpeed()));
				} else {
					interceptButton.setEnabled(false);
				}
			}
		});
		//减速按钮监听事件
		decelerateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (canvas.getMissileSpeed() == 1) {
					decelerateButton.setEnabled(false);
					return;
				}
				accelerateButton.setEnabled(true);
				decelerateButton.setEnabled(true);
				int missileSpeed = canvas.getMissileSpeed() / 2;
				int interceptorSpeed = canvas.getInterceptorSpeed() / 2;
				canvas.adjustSpeed(missileSpeed, interceptorSpeed);
			}
		});
		//暂停按钮监听事件
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				startButton.setEnabled(true);
				stopButton.setEnabled(false);
				timeController.setStatus(false);
				timeController.run();
				status = false;
				runThreads();
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			//默认初始大小为900*600
			new RedFlag(900, 600);
		});
	}

	//计时器类
	class TimeController extends TimerTask {
		private int number;
		private boolean status;

		public TimeController(int number, boolean status) {
			this.number = number;
			this.status = status;
		}

		@Override
		public void run() {
			if (status) {
				timeText.setText(String.valueOf(timeController.getNumber()));
				number++;
			}
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

		public int getNumber() {
			return number;
		}

		public boolean isStatus() {
			return status;
		}
	}

	//运行多线程
	public void runThreads() {
		MissileThread missileThread = new MissileThread();
		InterceptorThread interceptorThread = new InterceptorThread();
		GenerationThread generationThread = new GenerationThread();
		missileThread.start();
		interceptorThread.start();
		generationThread.start();
	}

	//导弹移动线程
	public class MissileThread extends Thread {
		@Override
		public void run() {
			while (status) {
				try {
					sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				canvas.crash();
				for (int i = 0; i < canvas.missiles.size(); i++) {
					Missile missile = canvas.missiles.get(i);
					if (missile.isToRight()) {
						int dx = missile.getX() + missile.getSpeed();
						if (dx >= getWidth()) {
							canvas.missiles.remove(i);
							i--;
						} else {
							missile.setX(dx);
							canvas.missiles.set(i, missile);
						}
					} else {
						int dx = missile.getX() - missile.getSpeed();
						if (dx <= 0) {
							canvas.missiles.remove(i);
							i--;
						} else {
							missile.setX(dx);
							canvas.missiles.set(i, missile);
						}
					}
				}
				canvas.repaint();
			}
		}
	}

	//拦截器移动线程
	public class InterceptorThread extends Thread {
		@Override
		public void run() {
			while (status) {
				try {
					sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				canvas.crash();
				for (int i = 0; i < canvas.interceptors.size(); i++) {
					Interceptor interceptor = canvas.interceptors.get(i);
					int dy = interceptor.getY() - interceptor.getSpeed();
					if (dy <= 0) {
						canvas.interceptors.remove(i);
						i--;
					} else {
						interceptor.setY(dy);
						canvas.interceptors.set(i, interceptor);
					}
				}
				if (canvas.interceptors.size() < MAX_INTERCEPTOR_NUMBER) {
					interceptButton.setEnabled(true);
				} else {
					interceptButton.setEnabled(false);
				}
				canvas.repaint();
			}
		}
	}

	//导弹生成线程
	public class GenerationThread extends Thread {
		@Override
		public void run() {
			while (status) {
				long currentSleepTime = (long) (Math.random() * 1000 + 500);
				try {
					sleep(currentSleepTime);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				int x = (int) (Math.random() * canvas.getWidth());
				int y = (int) (Math.random() * canvas.getHeight());
				boolean toRight = (x < canvas.getWidth() / 2);
				Missile missile = new Missile(x, y, canvas.getMissileSpeed(), toRight);
				canvas.missiles.add(missile);
				totalInterceptorNumber++;
				interceptorText.setText(canvas.getHitTimes() + "/" + totalInterceptorNumber);
			}

		}

	}
}

class Canvas extends JPanel {
	//命中次数
	private int hitTimes = 0;
	//导弹速度
	private int missileSpeed = 4;
	//拦截器速度
	private int interceptorSpeed = 8;
	//导弹
	List<Missile> missiles = new ArrayList<>();
	//拦截器
	List<Interceptor> interceptors = new ArrayList<>();

	public Canvas(int width, int height) {
		this.setSize(width, height);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (Missile missile : missiles) {
			missile.drawMissile(g);
		}
		for (Interceptor interceptor : interceptors) {
			interceptor.drawInterceptor(g);
		}
	}

	public int getHitTimes() {
		return hitTimes;
	}

	public int getMissileSpeed() {
		return missileSpeed;
	}

	public int getInterceptorSpeed() {
		return interceptorSpeed;
	}

	public void setHitTimes(int hitTimes) {
		this.hitTimes = hitTimes;
	}

	public void setMissileSpeed(int missileSpeed) {
		this.missileSpeed = missileSpeed;
	}

	public void setInterceptorSpeed(int interceptorSpeed) {
		this.interceptorSpeed = interceptorSpeed;
	}

	//调整导弹和拦截器速度
	public void adjustSpeed(int missileSpeed, int interceptorSpeed) {
		//导弹速度上限和下限，共5档速度（1、2、4、8、16）
		if (missileSpeed < 1 || missileSpeed > 16) {
			return;
		}
		//更新导弹速度
		this.missileSpeed = missileSpeed;
		for (int i = 0; i < missiles.size(); i++) {
			Missile missile = missiles.get(i);
			missile.setSpeed(this.missileSpeed);
			missiles.set(i, missile);
		}
		//更新拦截器速度
		this.interceptorSpeed = interceptorSpeed;
		for (int i = 0; i < interceptors.size(); i++) {
			Interceptor interceptor = interceptors.get(i);
			interceptor.setSpeed(this.interceptorSpeed);
			interceptors.set(i, interceptor);
		}
	}

	//拦截器和相碰撞
	public void crash() {
		for (int i = 0; i < missiles.size(); i++) {
			for (int j = 0; j < interceptors.size(); j++) {
				int x1 = missiles.get(i).getX();
				int y1 = missiles.get(i).getY();
				int x2 = interceptors.get(j).getX();
				int y2 = interceptors.get(j).getY();
				int r1 = missiles.get(i).getSize() / 2;
				int r2 = interceptors.get(j).getSize() / 2;
				if ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) <= (r1 + r2) * (r1 + r2)) {
					missiles.remove(i);
					interceptors.remove(j);
					i--;
					hitTimes++;
					break;
				}
			}
		}
	}
}


//导弹类
class Missile {
	private Color color = Color.blue;
	private int x = 0, y = 0;
	private int size = 30;
	private int speed = 4;
	private boolean toRight = true;

	public Missile(int x, int y, int speed, boolean toRight) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.toRight = toRight;
	}

	@Override
	public String toString() {
		return "Missile{" +
				"color=" + color +
				", x=" + x +
				", y=" + y +
				", size=" + size +
				", speed=" + speed +
				", toRight=" + toRight +
				'}' + "\n";
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setToRight(boolean toRight) {
		this.toRight = toRight;
	}

	public Color getColor() {
		return color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSize() {
		return size;
	}

	public int getSpeed() {
		return speed;
	}

	public boolean isToRight() {
		return toRight;
	}

	//绘制导弹
	public void drawMissile(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(color);
		g2.fillOval(x, y, size, size);
	}
}

//拦截器类
class Interceptor {
	private Color color = Color.red;
	private int x = 0, y = 0;
	private int size = 20;
	private int speed = 8;

	public Interceptor(int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "Interceptor{" +
				"color=" + color +
				", x=" + x +
				", y=" + y +
				", size=" + size +
				", speed=" + speed +
				'}' + "\n";
	}

	public Color getColor() {
		return color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSize() {
		return size;
	}

	public int getSpeed() {
		return speed;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	//绘制拦截器
	public void drawInterceptor(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(color);
		g2.fillOval(x, y, size, size);
	}
}
