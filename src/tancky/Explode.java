package tancky;

import java.awt.*;


//爆炸类，控制子弹击中坦克之后的爆炸

public class Explode {
	
	int x, y;  //爆炸发生的位置
	
	private boolean live = true;  //爆炸是否存活  
	
	
	private TankClient tc ;  //持有客户端的引用
	
	int [] diameter =  {1,4,7,12,18,26,32,40,49,30,20,14,6}; //爆炸圆的直径
	
	int step = 0; //爆炸的程度

	//构造函数
	public Explode(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	//爆炸的绘制方法
	public void draw (Graphics g){
		if(this.live == false){
			tc.explodes.remove(this);
			return ;
		}
		
		if(step == diameter.length){
			live = false ;
			step = 0 ; 
			return ;
		}
		Color c = g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, diameter[step], diameter[step]);
		step++;
		g.setColor(c);
		
	}
	
	
	
	
	
	

}
