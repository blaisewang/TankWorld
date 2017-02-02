package tancky;

import java.awt.*;


//��ը�࣬�����ӵ�����̹��֮��ı�ը

public class Explode {
	
	int x, y;  //��ը������λ��
	
	private boolean live = true;  //��ը�Ƿ���  
	
	
	private TankClient tc ;  //���пͻ��˵�����
	
	int [] diameter =  {1,4,7,12,18,26,32,40,49,30,20,14,6}; //��ըԲ��ֱ��
	
	int step = 0; //��ը�ĳ̶�

	//���캯��
	public Explode(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	//��ը�Ļ��Ʒ���
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
