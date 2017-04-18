package tancky;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TankDeadMsg implements Msg {

    private int ID;
    private TankClient tankClient;

    TankDeadMsg(int ID) {
        this.ID = ID;
    }

    TankDeadMsg(TankClient tankClient) {
        this.tankClient = tankClient;
    }

    @Override
    public void send(DatagramSocket datagramSocket, String IP, int udpPort) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(byteArrayOutputStream);
        try {
            dos.writeInt(Msg.TANK_DEAD_MSG);
            dos.writeInt(ID);
            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] buffer = byteArrayOutputStream.toByteArray();

        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, new InetSocketAddress(IP, udpPort));
        try {
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parse(DataInputStream dataInputStream) {
        try {
            int ID = dataInputStream.readInt();

            if (tankClient.tank.id == ID) {
                return;
            }

            for (int i = 0; i < tankClient.enemyTanks.size(); i++) {
                Tank t = tankClient.enemyTanks.get(i);
                if (t.id == ID) {
                    t.setLive();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
