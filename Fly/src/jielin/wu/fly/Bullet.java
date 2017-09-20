package jielin.wu.fly;

public class Bullet extends FlyingObject{
	private int speed =3;
	
	public Bullet(int x,int y){
	image=ShootGame.bullet;
	width=image.getWidth();
	height=image.getHeight();
	this.x=x;
	this.y=y;
	}
	
	public boolean outofBounds(){
		return y<=-height;
	}

	@Override
	public void step() {
		y-=speed;
		
	}
}
