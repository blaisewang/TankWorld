package tancky;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;



public class Missile {
	
	int x , y; //������λ��
	
	Direction dir ;  //�����ķ���
	
	
	TankClient tc;  //��ȡ�ͻ��˶��������
	
	public static final int WIDTH = 10; //�����Ŀ��
	public static final int HEIGHT = 10; //�����ĸ߶�
	
	
	public static final int SPEED = 20;  //�����ķ����ٶ�
	
	public static int ID = 1 ; 
	
	
	
	int tankID ; 
	int id ; 
	
	private boolean good;
	
	
	public boolean isGood() {
		return good;
	}



	boolean live = true;
	
	
	public Missile(int tankID , int x, int y, Direction dir) {
		this.tankID = tankID ; 
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.id = ID ++ ; 
	}
	
	
	public Missile (int tankID , int x , int y , boolean good, Direction dir , TankClient tc){
		this(tankID,x,y,dir);
		this.good = good ;
		this.tc = tc;
	}
	
	
	
	
	
	
	//�����Ļ��Ʒ���
	public void draw (Graphics g){
		
		if(this.live == false){
			tc.missiles.remove(this);
			return;
		}
		Color c = g.getColor();
		
		if(this.good == true){
			g.setColor(Color.black);
			
		}
		
		else{
			g.setColor(Color.blue);
			
		}
		
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		move();
	}
	
	
	//�������ƶ�����
	public void move (){
		switch(dir){
		
		case U :
			y -= SPEED;
			break;
			
		case RU :
			x += SPEED;
			y -= SPEED;
			break;
			
		case R :
			x += SPEED;
			break;
			
		case RD :
			x += SPEED;
			y += SPEED;
			break;
		
		case D :
			y += SPEED;
			break;
			
		case LD :
			x -= SPEED;
			y += SPEED;
			break;
			
		case L :
			x -= SPEED;
			break;
		
		case LU :
			x -= SPEED;
			y -= SPEED;
			break;
			
		case STOP:
			break;
		}
		
		
		if(x < 0 || y < 0 || y > TankClient.GAME_HEIGHT || x > TankClient.GAME_WIDTH){
			this.live = false;
			
		}
		
		
	}
	
	
	
	
	//��ײ��⸨����
	public Rectangle getRect (){
		return new Rectangle (x , y , WIDTH , HEIGHT);
	}
	
	
	
	//�ӵ�����̹�˲�ȡ�Ĵ���
	public boolean hitTank (Tank t){
		if( this.live && this.getRect().intersects(t.getRect()) == true &&  t.isLive() == true && this.good != t.isGood()  ){
			t.setLive(false);
			this.live = false;
			Explode e = new Explode (x , y , tc);
			tc.explodes.add(e);
			return true;
		}
		
		return false ;
	}
	
	
	
	public boolean hitTanks (List <Tank> tanks){
		for(int i = 0 ; i < tanks.size() ; i ++){
			if( hitTank(tanks.get(i)) == true){
				
				return true ;
				
			}	
		}
		return false ;
		
	}
	
	
	
	
	

}
