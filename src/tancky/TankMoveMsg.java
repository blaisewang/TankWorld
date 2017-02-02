package tancky;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TankMoveMsg implements Msg{
	
	
	int msgType = Msg.TANK_MOVE_MSG ;
	
	int id ; 
	int x ; 
	int y ;
	Direction dir ;
	Direction ptDir ;
	
	TankClient tc ;
	
	
	
	public TankMoveMsg(int id, int x , int y ,Direction dir , Direction ptDir) {
		this.id = id;
		this.x = x ; 
		this.y = y ;
		this.dir = dir;
		this.ptDir = ptDir ;
	}



	public TankMoveMsg(TankClient tc) {
		this.tc = tc ;
	}



	public void send(DatagramSocket ds, String IP, int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream() ;
		DataOutputStream dos = new DataOutputStream(baos) ;
		try {
			dos.writeInt(msgType);
			dos.writeInt(this.id);
			dos.writeInt(x);
			dos.writeInt(y);	
			dos.writeInt(this.dir.ordinal());
			dos.writeInt(this.ptDir.ordinal());
			dos.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte[] buf = baos.toByteArray();
		
		DatagramPacket dp = new DatagramPacket (buf , buf.length , new InetSocketAddress(IP , udpPort)) ; 
		try {
			ds.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void parse(DataInputStream dis) {
		try {
			int id = dis.readInt();
			
			if(tc.myTank.id == id){
				return ;
			}
			
			int x = dis.readInt();
			int y = dis.readInt();
			Direction dir = Direction.values()[dis.readInt()];
			Direction ptDir = Direction.values()[dis.readInt()];
			
			boolean exist = false ; 
			
			
			for(int i = 0 ; i < tc.enemyTanks.size() ; i ++){
				Tank t = tc.enemyTanks.get(i);
				if(t.id == id){
					t.tankX = x ;
					t.tankY = y ;
					t.dir = dir ;
					t.barrelDir = ptDir;
					exist = true;
					break;
				}
			}
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	
	
	
	
	

}
