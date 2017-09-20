package jielin.wu.fly;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** 游戏主界面 */
public class ShootGame extends JPanel {// ShootGame是面板
	public static final int WIDTH = 400;
	public static final int HEIGHT = 600;
	public static BufferedImage backgroud;
	public static BufferedImage start;
	public static BufferedImage gameover;
	public static BufferedImage pause;
	public static BufferedImage airplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage hero0;
	public static BufferedImage hero1;

	public Hero hero = new Hero();// 英雄机对象
	public Bullet[] bullets = {};
	public FlyingObject[] flyings = {};// 敌机与小蜜蜂

	static {
		try {
			backgroud = ImageIO.read(ShootGame.class.getResource("background.png"));
			start = ImageIO.read(ShootGame.class.getResource("start.png"));
			gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));
			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
			airplane = ImageIO.read(ShootGame.class.getResource("airplane.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			bee = ImageIO.read(ShootGame.class.getResource("bee.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int state;
	private static final int START = 0;
	private static final int RUNNING = 1;
	private static final int PAUSE = 2;
	private static final int GAME_OVER = 3;

	public ShootGame() {
		flyings = new FlyingObject[2];
		flyings[0] = new Airplane();
		flyings[1] = new Bee();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(backgroud, 0, 0, null);// 画图片
		paintHero(g);
		paintBullets(g);
		paintFlyingObjects(g);
		paintScore(g);
		paintState(g);
	}

	public void paintState(Graphics g) {
		switch (state) {
		case START:
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}

	/** 画英雄机 */
	public void paintHero(Graphics g) {
		g.drawImage(hero.image, hero.x, hero.y, null);
	}

	/** 画子弹 */
	public void paintBullets(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];// 第i个子弹对象
			g.drawImage(b.image, b.x, b.y, null);
		}
	}

	/** 画飞行器敌人 */
	public void paintFlyingObjects(Graphics g) {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject fly = flyings[i];
			g.drawImage(fly.image, fly.x, fly.y, null);
		}
	}

	public void paintScore(Graphics g) {
		g.setColor(new Color(0x00FF00));
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		g.drawString("SCORE:" + score, 10, 25);
		g.drawString("LIFE:" + hero.getLife(), 10, 45);
	}

	private Timer timer;// 定时器
	private int interval = 10;// 定时时间间隔(ms)

	/** 启动执行 */
	public void action() {
		// 鼠标事件适配器
		MouseAdapter l = new MouseAdapter() {// 匿名类，产生一个子类对象
			/** 重写鼠标移动方法 */
			public void mouseMoved(MouseEvent e) {
				if (state == RUNNING) {
					int x = e.getX();// 得到鼠标的x坐标
					int y = e.getY();
					hero.moveTo(x, y);// hero移动到x，y
				}
			}

			public void mouseClicked(MouseEvent e) {
				switch (state) {
				case START:
					state = RUNNING;
					break;
				case GAME_OVER:
					hero = new Hero();
					flyings = new FlyingObject[0];
					score = 0;
					state = START;
					bullets = new Bullet[0];
					break;
				}
			}

			public void mouseExited(MouseEvent e) {
				if (state != GAME_OVER) {
					state = PAUSE;
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (state == PAUSE) {
					state = RUNNING;
				}
			}
		};

		this.addMouseListener(l);
		this.addMouseMotionListener(l);// 为当前面板添加鼠标移动监听器

		timer = new Timer();// 创建定时器对象
		timer.schedule(new TimerTask() {// 内部类，创建抽象类TimerTask的对象
			// 覆盖run方法,定时执行run()
			@Override
			public void run() {
				if (state == RUNNING) {
					// 让飞行物入场--new飞行物对象
					enterAction();
					// 飞行物走步
					stepAction();
					shootAction();// 发射（子弹入场）
					bangAction();// 子弹打敌人
					outOfBoundsAction();
					checkGameOverAction();
				}
				repaint();// 重绘（调用paint方法）
			}

		}, interval, interval);// 定时触发
	}

	public void checkGameOverAction() {
		if (isGameOver()) {
			state=GAME_OVER;
		}
	}

	public boolean isGameOver() {
		for (int i = 0; i < flyings.length; i++) {
			int index = -1;// 记录撞衫的的飞行物索引
			FlyingObject obj = flyings[i];
			if (hero.hit(obj)) {
				hero.substractLife();
				hero.setDoubleFire(0);
				index = i;
			}
			if (index != -1) {
				FlyingObject t = flyings[index];
				flyings[index] = flyings[flyings.length - 1];
				flyings[flyings.length - 1] = t;
				flyings = Arrays.copyOf(flyings, flyings.length - 1);
			}
		}
		return hero.getLife() <= 0;
	}

	public void outOfBoundsAction() {
		int index = 0;
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if (!f.outofBounds()) {
				flyingLives[index++] = f;
			}
		}
		flyings = Arrays.copyOf(flyingLives, index);
		index = 0;
		Bullet[] bulletLives = new Bullet[bullets.length];
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if (!b.outofBounds()) {
				bulletLives[index++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index);
	}

	/** 子弹打敌人（碰撞） */
	public void bangAction() {
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			bang(b);// 子弹和飞行物碰撞
		}
	}

	private int score = 0;

	public void bang(Bullet b) {
		int index = -1;// 集中飞行物的索引
		for (int j = 0; j < flyings.length; j++) {
			FlyingObject obj = flyings[j];
			if (obj.shootBy(b)) {
				index = j;
				break;
			}
		}
		if (index != -1) {// 击中
			FlyingObject one = flyings[index];
			FlyingObject t = flyings[index];
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = t;
			flyings = Arrays.copyOf(flyings, flyings.length - 1);
			if (one instanceof Enemy) {
				Enemy e = (Enemy) one;
				score += e.getScore();
			} else if (one instanceof Award) {
				Award a = (Award) one;
				int type = a.getType();
				switch (type) {
				case Award.DOUBLE_FIRE:
					hero.addDoubleFire();
					break;
				case Award.LIFE:
					hero.addLife();
					break;
				}
			}
		}
	}

	int shootIndex = 0;

	/** 射击 */
	public void shootAction() {
		shootIndex++;
		if (shootIndex % 30 == 0) {// 300ms发射一次
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length);
		}
	}

	int flyEnterAction = 0;

	/** 飞行物（不包括hero，hero只进场一次）入场 */
	public void enterAction() {
		flyEnterAction++;
		if (flyEnterAction % 40 == 0) {// 每40ms来一次
			FlyingObject obj = nextOne();// 随机生成
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length - 1] = obj;
		}
	}

	/** 随机生成敌机或者蜜蜂 */
	// 工厂方法:生成对象的方法，一般为static的
	public static FlyingObject nextOne() {
		Random rand = new Random();
		int type = rand.nextInt(20);// [0,19)
		if (type == 0) {
			return new Bee();
		} else {
			return new Airplane();
		}
	}

	/** 飞行物走步 */
	public void stepAction() {
		for (int i = 0; i < flyings.length; i++) {
			flyings[i].step();
		}
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].step();
		}
		hero.step();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Fly");
		ShootGame game = new ShootGame();
		frame.add(game); // 将面板加到画框中
		frame.setSize(WIDTH, HEIGHT);// 大小
		frame.setAlwaysOnTop(true);// 总在最上层
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 默认关闭
		frame.setLocationRelativeTo(null);// 不设相对位置，在中间
		frame.setVisible(true);// 显示--尽快调用paint方法

		game.action();

	}

}
