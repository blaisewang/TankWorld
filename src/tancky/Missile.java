package tancky;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;



public class Missile {
	
	int x , y; //导弹的位置
	
	Direction dir ;  //导弹的方向
	
	
	TankClient tc;  //获取客户端对象的引用
	
	public static final int WIDTH = 10; //导弹的宽度
	public static final int HEIGHT = 10; //导弹的高度
	
	
	public static final int SPEED = 20;  //导弹的飞行速度
	
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
	
	
	
	
	
	
	//导弹的绘制方法
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
	
	
	//导弹的移动方法
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
	
	
	
	
	//碰撞检测辅助类
	public Rectangle getRect (){
		return new Rectangle (x , y , WIDTH , HEIGHT);
	}
	
	
	
	//子弹击中坦克采取的处理
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
