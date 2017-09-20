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

/** ��Ϸ������ */
public class ShootGame extends JPanel {// ShootGame�����
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

	public Hero hero = new Hero();// Ӣ�ۻ�����
	public Bullet[] bullets = {};
	public FlyingObject[] flyings = {};// �л���С�۷�

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
		g.drawImage(backgroud, 0, 0, null);// ��ͼƬ
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

	/** ��Ӣ�ۻ� */
	public void paintHero(Graphics g) {
		g.drawImage(hero.image, hero.x, hero.y, null);
	}

	/** ���ӵ� */
	public void paintBullets(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];// ��i���ӵ�����
			g.drawImage(b.image, b.x, b.y, null);
		}
	}

	/** ������������ */
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

	private Timer timer;// ��ʱ��
	private int interval = 10;// ��ʱʱ����(ms)

	/** ����ִ�� */
	public void action() {
		// ����¼�������
		MouseAdapter l = new MouseAdapter() {// �����࣬����һ���������
			/** ��д����ƶ����� */
			public void mouseMoved(MouseEvent e) {
				if (state == RUNNING) {
					int x = e.getX();// �õ�����x����
					int y = e.getY();
					hero.moveTo(x, y);// hero�ƶ���x��y
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
		this.addMouseMotionListener(l);// Ϊ��ǰ����������ƶ�������

		timer = new Timer();// ������ʱ������
		timer.schedule(new TimerTask() {// �ڲ��࣬����������TimerTask�Ķ���
			// ����run����,��ʱִ��run()
			@Override
			public void run() {
				if (state == RUNNING) {
					// �÷������볡--new���������
					enterAction();
					// �������߲�
					stepAction();
					shootAction();// ���䣨�ӵ��볡��
					bangAction();// �ӵ������
					outOfBoundsAction();
					checkGameOverAction();
				}
				repaint();// �ػ棨����paint������
			}

		}, interval, interval);// ��ʱ����
	}

	public void checkGameOverAction() {
		if (isGameOver()) {
			state=GAME_OVER;
		}
	}

	public boolean isGameOver() {
		for (int i = 0; i < flyings.length; i++) {
			int index = -1;// ��¼ײ���ĵķ���������
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

	/** �ӵ�����ˣ���ײ�� */
	public void bangAction() {
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			bang(b);// �ӵ��ͷ�������ײ
		}
	}

	private int score = 0;

	public void bang(Bullet b) {
		int index = -1;// ���з����������
		for (int j = 0; j < flyings.length; j++) {
			FlyingObject obj = flyings[j];
			if (obj.shootBy(b)) {
				index = j;
				break;
			}
		}
		if (index != -1) {// ����
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

	/** ��� */
	public void shootAction() {
		shootIndex++;
		if (shootIndex % 30 == 0) {// 300ms����һ��
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length);
		}
	}

	int flyEnterAction = 0;

	/** �����������hero��heroֻ����һ�Σ��볡 */
	public void enterAction() {
		flyEnterAction++;
		if (flyEnterAction % 40 == 0) {// ÿ40ms��һ��
			FlyingObject obj = nextOne();// �������
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length - 1] = obj;
		}
	}

	/** ������ɵл������۷� */
	// ��������:���ɶ���ķ�����һ��Ϊstatic��
	public static FlyingObject nextOne() {
		Random rand = new Random();
		int type = rand.nextInt(20);// [0,19)
		if (type == 0) {
			return new Bee();
		} else {
			return new Airplane();
		}
	}

	/** �������߲� */
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
		frame.add(game); // �����ӵ�������
		frame.setSize(WIDTH, HEIGHT);// ��С
		frame.setAlwaysOnTop(true);// �������ϲ�
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Ĭ�Ϲر�
		frame.setLocationRelativeTo(null);// �������λ�ã����м�
		frame.setVisible(true);// ��ʾ--�������paint����

		game.action();

	}

}
