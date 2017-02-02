package tancky;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TankNewMsg implements Msg{
	
	
	int msgType = Msg.TANK_NEW_MSG;
	
	
	Tank tank ;

	TankClient tc ; 
	
	public TankNewMsg(Tank tank) {
		this.tank = tank;
	}



	public TankNewMsg(TankClient tc) {
		this.tc = tc ; 
	}



	public void send(DatagramSocket ds , String IP  , int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream() ;
		DataOutputStream dos = new DataOutputStream(baos) ;
		try {
			dos.writeInt(msgType);
			dos.writeInt(tank.id);
			dos.writeInt(tank.tankX);
			dos.writeInt(tank.tankY);
			dos.writeInt(tank.dir.ordinal());
			dos.writeBoolean(tank.isGood());
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
			int id = dis.readInt();
			if(tc.myTank.id == id){
				return ;
			}
			
			int x = dis.readInt();
			int y = dis.readInt();
			Direction dir = Direction.values()[dis.readInt()];
			boolean good = dis.readBoolean() ; 
			
			boolean exist = false ; 
			
			for(int i = 0 ; i < tc.enemyTanks.size(); i++){
				Tank t = tc.enemyTanks.get(i);
				if(t.id == id){
					exist = true;
					break;
				}
			}
			
			if(exist == false){
				
				TankNewMsg tnMsg = new TankNewMsg(tc.myTank);
				tc.nc.send(tnMsg);
				
				Tank t = new Tank(x,y,good,dir,tc);
				t.id = id ; 
				tc.enemyTanks.add(t);
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		

		
		
		
		
	} 
	
	
	
	
	

}
