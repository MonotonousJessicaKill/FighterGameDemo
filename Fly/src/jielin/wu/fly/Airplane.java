package jielin.wu.fly;

import java.util.Random;

/**�л���������Ҳ�ǵ���*/
public class Airplane extends FlyingObject implements Enemy{
	private int speed = 2;
	
	public Airplane(){
		image=ShootGame.airplane;
		width=image.getWidth();
		height=image.getHeight();
		y=-height;
		Random rand=new Random();
		x=rand.nextInt(ShootGame.WIDTH-width);
		
	}
	
	public boolean outofBounds(){
		return y>=ShootGame.HEIGHT;
	}
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 5;
	}
	/**�ɻ��߲�*/
	@Override
	public void step() {
		y+=speed;
		
	}

}
