package jielin.wu.fly;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject {
	private BufferedImage[] images;
	private int index;// Í¼Æ¬½»»»¼ÆÊý
	private int doubleFire;
	private int life;
	
	public boolean outofBounds(){
		return false;
	}
	public int getLife(){
		return life;
	}
	public boolean hit(FlyingObject other){
		return (other.x>=x-other.width&&other.x<=x+width)
				&&
				(other.y>=y-other.height&&other.y<=y+height);
	}

	public void substractLife(){
		life--;
	}
	
	public void setDoubleFire(int doubleFire){
		this.doubleFire=doubleFire;
	}
	public Hero() {
		image = ShootGame.hero0;
		width = image.getWidth();
		height = image.getHeight();
		x = 150;
		y = 400;
		doubleFire = 0;
		life = 3;
		images = new BufferedImage[] { ShootGame.hero0, ShootGame.hero1 };

	}

	/** Ìí¼ÓË«±¶»ðÁ¦ */
	public void addDoubleFire() {
		doubleFire += 40;
	}

	/** Ìí¼ÓÉúÃü */
	public void addLife() {
		life++;
	}

	/** HeroËæÊó±ê¶¯ */
	public void moveTo(int x, int y) {
		this.x = x - this.width / 2;
		this.y = y - this.height / 2;
	}

	@Override
	public void step() {// ÇÐÍ¼Æ¬
		image = images[index++ / 10 % images.length];
	}

	/** ·¢Éä×Óµ¯ */
	public Bullet[] shoot() {
		int xStep = this.width / 4;
		int yStep = 20;
		if (doubleFire > 0) {// Ë«±¶
			Bullet[] bullets = new Bullet[2];
			bullets[0] = new Bullet(this.x + 1 * xStep, this.y - yStep);
			bullets[1] = new Bullet(this.x + 3 * xStep, this.y - yStep);
			return bullets;
		} else {// µ¥±¶
			Bullet[] bullets = new Bullet[1];
			bullets[0] = new Bullet(this.x + 2 * xStep, y - yStep);
			return bullets;
		}
	}
}
