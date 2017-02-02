package tancky;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class MissileDeadMsg implements Msg {
	
	int msgType = Msg.MISSILE_DEAD_MSG ;
	
	int id ; 
	int tankId ; 
	
	
	TankClient tc ; 
	
	
	public MissileDeadMsg (int tankId ,int id ){
		this.tankId = tankId ; 
//System.out.println("TANKID IS " + tankId);
		this.id = id ;
	}
	
	
	public MissileDeadMsg (TankClient tc){
		this.tc = tc ; 
	}
	
	
	
	
	

	public void send(DatagramSocket ds, String IP, int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream() ;
		DataOutputStream dos = new DataOutputStream(baos) ;
		try {
			dos.writeInt(msgType);
			dos.writeInt(tankId);
//System.out.println("TANKID IS " + tankId);
			dos.writeInt(id);
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
			int tankId = dis.readInt();
			
			
			int id = dis.readInt();
			
			for(int i = 0 ; i < tc.missiles.size() ; i ++){
				Missile m = tc.missiles.get(i);
				if(m.tankID == tankId && m.id == id){
					m.live = false ;
					tc.explodes.add(new Explode(m.x, m.y, tc));
					break;
					
				}
			}
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
