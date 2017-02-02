package tancky;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class NetClient {
	
	private int udpPort ; 
	
	TankClient tc ; 
	
	
	
	String IP ; 
	
	public int getUdpPort() {
		return udpPort;
	}


	public void setUdpPort(int udpPort) {
		this.udpPort = udpPort;
	}


	DatagramSocket ds ;
	
	//此处存在问题， 后面改正 
	public NetClient( TankClient tc ) {
		this.tc = tc ; 
		
	}


	public void connect (String IP , int port){
		
		
		this.IP = IP ; 
		
		
		try {
			ds = new DatagramSocket(udpPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		Socket s = null; 
		try {
			s = new Socket (IP , port) ;
System.out.println("The client connects the server ! ");

			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			
			DataInputStream dis = new DataInputStream (s.getInputStream());
			
			
			
			
			dos.writeInt(this.udpPort);
			
			int id = dis.readInt();
			this.tc.myTank.id = id ; 
			
			if(id % 2 == 0){
				tc.myTank.setGood(false);
			}
			else{
				tc.myTank.setGood(true);
			}
			
			dos.flush();
			dos.close();
			dis.close();
			
			
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		
		finally {
			if( s != null){
				try {
					s.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}		
		}	
		
		TankNewMsg msg = new TankNewMsg(tc.myTank);
		send(msg);
		
		new Thread(new UDPRecvThread()).start();
		
	}
	
	
	public void send(Msg msg) {
		msg.send(ds , IP , TankServer.UDP_SERVER);
	}


	private class UDPRecvThread implements Runnable {
		
		byte[] buf = new byte[1024] ;
		
		public void run() {
			while(ds != null){
				DatagramPacket dp = new DatagramPacket (buf , buf.length);
				try {
					ds.receive(dp);
					parse(dp);
					
System.out.println("A packet from server !");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}

		private void parse(DatagramPacket dp) {
			ByteArrayInputStream bais = new ByteArrayInputStream(buf , 0 , dp.getLength());
			DataInputStream dis = new DataInputStream(bais) ; 
			try {
				int msgType = dis.readInt();
				Msg msg = null ;
				switch (msgType){
				
				case Msg.TANK_NEW_MSG :
				
					msg = new TankNewMsg(NetClient.this.tc);
					msg.parse(dis);
					break;
				case Msg.TANK_MOVE_MSG :
					msg = new TankMoveMsg(NetClient.this.tc);
					msg.parse(dis);
					break;
				case Msg.MISSILE_NEW_MSG :
					msg = new MissileNewMsg (NetClient.this.tc);
					msg.parse(dis);
					break;
				case Msg.TANK_DEAD_MSG :
					msg = new TankDeadMsg (NetClient.this.tc);
					msg.parse(dis);
					break;
				case Msg.MISSILE_DEAD_MSG :
					msg = new MissileDeadMsg (NetClient.this.tc);
					msg.parse(dis);
					break;
				}
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			
			
			
			
			
		}
		
		
		
	}
	
	
	
	
	
}
