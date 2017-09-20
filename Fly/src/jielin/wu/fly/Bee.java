package jielin.wu.fly;

import java.util.Random;

/**蜜蜂类*/
public class Bee extends FlyingObject implements Award {
	
	private int xSpeed=1;
	private int ySpeed=2;
	private int awardType;
	//构造器
	public Bee(){
		image=ShootGame.bee;
		width=image.getWidth();
		height=image.getHeight();//获取图片的高
		y=-height;
		Random rand=new Random();
		x= rand.nextInt(ShootGame.WIDTH-width);
		awardType=rand.nextInt(2);
	}
	
	public boolean outofBounds(){
		return y>=ShootGame.HEIGHT;
	}
	
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return awardType;
	}

	/**蜜蜂走步*/
	@Override
	public void step() {
		x+=xSpeed;
		y+=ySpeed;
		if(x<=0){
			xSpeed=1;
		}
		if(x>ShootGame.WIDTH-width){
			xSpeed=-1;
		}
		
	}

}
