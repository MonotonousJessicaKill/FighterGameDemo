package jielin.wu.fly;
import java.awt.image.BufferedImage;
/**飞行物类*/
public abstract class FlyingObject {
	protected int width;   //宽
	protected int height;  //高
	protected int x;
	protected int y;   //高
	protected BufferedImage image;  //图片
	
	/**走步*/
	public abstract void step();
	
	/**敌人被打*/
	public boolean shootBy(Bullet b){
		int x=b.x;
		int y=b.y;
		return x>this.x&&x<this.x+width
				&&
				y>this.y&&y<this.y+height;
	}
	/**检测出界*/
	public abstract boolean outofBounds();
}
