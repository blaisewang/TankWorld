package tancky;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.Random;;

public class Tank {
	
	int tankX; //̹�˵�x����
	int tankY; //̹�˵�y����
	
	int id ; 
	
	
	
	//���水����״̬������ȷ��̹�˵��ƶ�����
	boolean bUp = false; 
	boolean bDown = false;
	boolean bLeft = false;
	boolean bRight = false;
	
	
	private static Random r = new Random() ;
	
	
	private int step = r.nextInt(12) + 3 ;
	
	
	
	//̹�˵��ƶ�����
	 Direction dir ;
	 Direction barrelDir = Direction.D;
	
	
	TankClient tc ; //���пͻ��˵����ã� ������и��ָ�ֵ
	
	
	private boolean good ; // ��ʾ̹�˵���Ӫ��  true Ϊ�����Ӫ��  falseΪ������Ӫ
	
	
	public boolean isGood() {
		return good;
	}

	public void setGood (boolean good){
		this.good = good ;
		
	}
	private boolean live = true;  //̹��Ĭ�ϴ�false��Ϊ̹������ 
	
	
	
	
	//̹�˵�����
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}


	public static final int TANKW = 30;  //̹�˵Ŀ��
	public static final int TANKH = 30;  //̹�˵ĸ߶�
	public static final int SPEED = 15;  //̹�˵��ƶ��ٶȣ�ˮƽ��ֱ��
	
	public static final int upLimit = 16; //���ڱ������ĸ߶�
	
	
	
	
	
	//���췽��
	public Tank (int x , int y, boolean good){
		this.tankX = x;
		this.tankY = y;
		this.good = good;
	}
	
	
	//���ͻ������õĹ��췽��
	public Tank (int x, int y , boolean good, Direction dir ,TankClient tc){
		this(x,y,good);
		this.dir = dir;
		this.tc = tc;
	}
	
	//���̹���Ƿ񳬳���Ļ��Ե������������false�� �ڷ�Χ�ڷ���true
	private boolean checkEdge (int x , int y ){
		if(x >= 0 && x <= (TankClient.GAME_WIDTH - TANKW ) 
		   && y >= upLimit && y <= TankClient.GAME_HEIGHT - TANKH)
		{
			return true;
			
		}
		else {
			return false;
		}
	}
	
	
	//̹�˵��ƶ�����
	private void move (){
		int tempX,tempY;
		switch(dir){
		
		case U :
			tempY = tankY - SPEED;
			if(checkEdge(tankX,tempY) == true){
				tankY = tempY;
			}
			break;
			
		case RU :
			tempX = tankX + SPEED;
			tempY = tankY - SPEED;
			if(checkEdge(tempX,tempY) == true){
				tankX = tempX;
				tankY = tempY;
			}
			break;
			
		case R :
			tempX = tankX + SPEED;
			if(checkEdge(tempX,tankY) == true){
				tankX = tempX;
			}
			break;
			
		case RD :
			
			tempX = tankX + SPEED;
			tempY = tankY + SPEED;
			if(checkEdge(tempX,tempY) == true){
				tankX = tempX;
				tankY = tempY;
			}
			break;
		
		case D :
			
			
			tempY = tankY + SPEED;
			if(checkEdge(tankX,tempY) == true){
				tankY = tempY;
			}
			
			break;
			
		case LD :
			tempX = tankX - SPEED;
			tempY = tankY + SPEED;
			if(checkEdge(tempX,tempY) == true){
				tankX = tempX;
				tankY = tempY;
			}
			break;
			
		case L :
			
			tempX = tankX - SPEED;
			
			if(checkEdge(tempX,tankY) == true){
				tankX = tempX;
			}
			break;
		
		case LU :
			
			tempX = tankX - SPEED;
			tempY = tankY - SPEED;
			if(checkEdge(tempX,tempY) == true){
				tankX = tempX;
				tankY = tempY;
			}
			break;
			
		case STOP:
			break;
		}
		
		
		
		//�������̹�˺͵з�̹�˵���Ͳ����
		if(this.dir != Direction.STOP){
			this.barrelDir = this.dir;
		}
		
		/*if(this.good == false){
			Direction[] dirs = Direction.values();
			if(step == 0){
				step = r.nextInt(12) + 3 ;
				int rn = r.nextInt(dirs.length);
				this.dir = dirs[rn];
				
			}
			step -- ;
			
			
			
			if(r.nextInt(40)>35){
				this.fire();
			}
			
			

			

			
			//�˴�һ��ʼû������жϵ�ǰ̹�˷����ǲ���stop�����������µ�̹��ΪSTOPʱ����Ϸ��������쳣
			//�����Ż���������з�̹�˺����̹�˵ķ���ͬʱ������һ��
			
			//if(this.dir != Direction.STOP){
				//this.barrelDir = this.dir;
			//}  
			
		}*/
		
	}
	
	
	//�Ӱ���״̬ȷ��̹�˵ķ���
	private void location (){
		
		Direction oldDir = this.dir;
		
		if(bUp && !bDown && !bLeft && !bRight ){dir = Direction.U;}
		
		else if(bUp && !bDown && !bLeft && bRight ){dir = Direction.RU;}
		
		else if(!bUp && !bDown && !bLeft && bRight ){dir = Direction.R;}
		
		else if(!bUp && bDown && !bLeft && bRight ){dir = Direction.RD;}
		
		else if(!bUp && bDown && !bLeft && !bRight ){dir = Direction.D;}
		
		else if(!bUp && bDown && bLeft && !bRight ){dir = Direction.LD;}
		
		else if(!bUp && !bDown && bLeft && !bRight ){dir = Direction.L;}
		
		else if(bUp && !bDown && bLeft && !bRight ){dir = Direction.LU;}
		
		else if(!bUp && !bDown && !bLeft && !bRight ){dir = Direction.STOP;}
		
		
		if(this.dir != oldDir){
			TankMoveMsg msg = new TankMoveMsg (id , this.tankX, this.tankY, dir , this.barrelDir);
			tc.nc.send(msg);
		}
		
		
	}
	
	//̹������Ļ�ͼ����
	public void draw (Graphics g){
		
		if(this.live == false){
			if(this.good == false){
				tc.enemyTanks.remove(this);
				
			}
			return ;
		}
		Color c = g.getColor();
		if(this.good == true){
			g.setColor(new Color (231, 76, 60));
		}
		else{
			g.setColor(new Color (26 ,186 , 154));
		}
		
		g.fillRect(this.tankX, this.tankY, this.TANKW, Tank.TANKH);
		g.drawString("ID : " + this.id , this.tankX,  this.tankY - 10 );
		g.setColor(Color.black);
		
		int x1 = this.tankX + this.TANKW / 2 ; 
		int y1 = this.tankY + this.TANKH / 2 ;
		int x2 = countX2();
		int y2 = countY2();
	
		g.drawLine(x1, y1, x2, y2);
		g.setColor(c);
		move();
	}
	
	
	private int countX2 () {
		double x = 0 ;
		switch(barrelDir){
		case U :
			x = this.tankX + this.TANKW / 2 ; 
			break;
			
		case RU :
			x = this.tankX + this.TANKW * 1.5 ;
			break;
			
		case R :
			x = this.tankX + this.TANKW * 1.5 ;
			
			break;
			
		case RD :
			x = this.tankX + this.TANKW * 1.5 ;
			break;
		
		case D :
			x = this.tankX + this.TANKW / 2 ; 
			break;
			
		case LD :
			x = this.tankX - this.TANKW / 2 ; 
		
			break;
			
		case L :
			x = this.tankX - this.TANKW / 2 ; 
			break;
		case LU :
			x = this.tankX - this.TANKW / 2 ; 
			break;
		default:
			break;
		}
		
		
		int rx = (int)x;
		return rx;
		
	}
	
	
	private int countY2 () {
		double y = 0 ;
		switch(barrelDir){
		case U :
			y = this.tankY - this.TANKH / 2 ; 
			break;
			
		case RU :
			y = this.tankY - this.TANKH / 2 ; 
			break;
			
		case R :
			y = this.tankY + this.TANKH / 2 ;
			
			break;
			
		case RD :
			y = this.tankY + this.TANKH * 1.5 ;
			break;
		
		case D :
			y = this.tankY + this.TANKH * 1.5 ;
			break;
			
		case LD :
			y = this.tankY + this.TANKH * 1.5 ;
		
			break;
			
		case L :
			y = this.tankY + this.TANKW / 2 ;
			break;
		case LU :
			y = this.tankY - this.TANKH / 2 ; 
			break;
		default:
			break;
		}
		
		
		int ry = (int)y;
		return ry;
		
	}
	
	
	//�������ķ���
	public void keyPressed (KeyEvent e){
		int key = e.getKeyCode();
		switch(key){
	
		case KeyEvent.VK_W:
			bUp = true;
			break;
	
		case KeyEvent.VK_S:
			bDown = true;
			break;
		
		case KeyEvent.VK_A:
			bLeft = true;
			break;
			
		case KeyEvent.VK_D:
			bRight = true;
			break;
		}	
		location();
	}

	
	
	public Missile fire (){
		
		
		if(this.live == false ){
			return null ;
		}
		
		//x,y ���¼����˵�����λ�ã�ʹ�������Դ�̹�˵����ķ���
		int x = this.tankX + Tank.TANKW/2 - Missile.WIDTH/2;
		int y = this.tankY + Tank.TANKH/2 - Missile.HEIGHT/2;

		
		Missile m = new Missile (id,x, y,good, this.barrelDir,this.tc);
//System.out.println("TANKID IS " + m.tankID);   101
//System.out.println("MISSILEID IS " + m.id);    1
		tc.missiles.add(m);
		
		MissileNewMsg msg = new MissileNewMsg (m);
		tc.nc.send(msg);
		
		return m;
	}



	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		
		case KeyEvent.VK_J:
			fire();
			break;
			
			
		case KeyEvent.VK_W:
			bUp = false;
			break;
	
		case KeyEvent.VK_S:
			bDown = false;
			break;
		
		case KeyEvent.VK_A:
			bLeft = false;
			break;
			
		case KeyEvent.VK_D:
			bRight = false;
			break;
		}	
		location();
		
	}
	
	
	
	//��ײ���ĸ�����
	public Rectangle getRect (){
		return new Rectangle (tankX , tankY , TANKW , TANKH);
	}
	
	
	
	
}
