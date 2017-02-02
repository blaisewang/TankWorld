package tancky;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class MissileNewMsg implements Msg {
	
	
	int msgType = Msg.MISSILE_NEW_MSG ; 
	
	Missile m ;
	TankClient tc ; 
	
	
	public MissileNewMsg (Missile m) {
		this.m = m ;
	}
	
	public MissileNewMsg (TankClient tc){
		this.tc = tc ; 
	}
	

	public void send(DatagramSocket ds, String IP, int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream() ;
		DataOutputStream dos = new DataOutputStream(baos) ;
		try {
			dos.writeInt(msgType);
			dos.writeInt(m.tankID);
			dos.writeInt(m.id);
//System.out.println("TANKID IS " + m.tankID);   101   
//System.out.println("MISSILEID IS " + m.id);    1
			dos.writeInt(m.x);
			dos.writeInt(m.y);	
			dos.writeInt(this.m.dir.ordinal());
			dos.writeBoolean(m.isGood());
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

	public void parse(DataInputStream dis) {
		
		try {
			int tankId = dis.readInt();
			if(tankId == tc.myTank.id){
				return ;
				
			}
			int id = dis.readInt();
			
//System.out.println("TANKID IS " + tankId);   
//System.out.println("MISSILEID IS " + id); 
			
			int x = dis.readInt();
			int y = dis.readInt();
			Direction dir = Direction.values()[dis.readInt()];
			boolean good = dis.readBoolean();
//System.out.println("uuuuuuuu:" + tc.myTank.id);
			Missile m = new Missile (tankId,x,y,good,dir,tc);
			m.id = id ;
			tc.missiles.add(m);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
