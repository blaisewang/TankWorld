package tancky;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class TankServer {
	
	
	private static int  tankID = 100 ;  //����̹��ID��
	
	
	public static final int TCP_SERVER  = 46464 ;  //����TCP���ӵĶ˿ں�
	
	public static final int UDP_SERVER = 6666 ;
	
	List <Client> clients = new ArrayList<Client> (); 
	
	
	
	
	private void launch (){
		
		
		
		//�˴�UDP�߳�һ��ʼд�����߳��У� �������ڲ���������main������д��ʱ������new��һ�����󣬵���
		//��������
		new Thread(new UDPThread()).start();
		
		
		
		try {
			ServerSocket ss = new ServerSocket (TCP_SERVER);
			while(true){
				Socket s = ss.accept() ; 
				
//������Ϣ			
System.out.println("A client is connect ! Address - " + s.getInetAddress() + ":" + s.getPort());
				
				DataInputStream dis = new DataInputStream(s.getInputStream());
				
				String sIP = s.getInetAddress().getHostAddress();
				int sUdpPort = dis.readInt();
				
				Client c = new Client (sIP , sUdpPort );
				
				clients.add(c);
				
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				
				dos.writeInt(tankID ++);
				
				dos.flush();
				dos.close();
				dis.close();
				s.close();
				
				for(int i = 0 ; i < this.clients.size() ; i ++){
					System.out.println(this.clients.get(i));
					System.out.println(clients.size());
				}
				
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		
		
	}

	
	
	public static void main(String[] args) {
		
		
		TankServer ts = new TankServer () ; 
		ts.launch();
	}

	
	
	
	private class Client {
		String ip ; 
		int udpPort ;
		
		
		public Client(String iP, int udpPort) {
			ip = iP;
			this.udpPort = udpPort;
		}


		public String toString() {
			
			return "IP :" + this.ip + " \n Port :" + this.udpPort + "\n";
	
		}		
		
	}
	
	
	
	private class UDPThread implements Runnable{
		
		byte[]  buf = new byte[1024] ;

		
		public void run() {
			try {
				DatagramSocket ds = new DatagramSocket(UDP_SERVER);
				while(ds != null){
					DatagramPacket dp = new DatagramPacket (buf , buf.length);
					ds.receive(dp);
System.out.println("A Packet hava receive ! " + clients.size());	
					

					for(int i = 0 ; i < clients.size() ; i ++){
System.out.println("A packet has send to Client ! ");

						Client c = clients.get(i);
						dp.setSocketAddress(new InetSocketAddress(c.ip , c.udpPort));
						ds.send(dp);
					}
					
				}
				
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			
			
			
			
		}
		
		
		
		
		
	}
}



