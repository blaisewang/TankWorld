package tancky;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TankNewMsg implements Msg {

    private Tank tank;

    private TankClient tankClient;

    TankNewMsg(Tank tank) {
        this.tank = tank;
    }

    TankNewMsg(TankClient tankClient) {
        this.tankClient = tankClient;
    }

    public void send(DatagramSocket datagramSocket, String IP, int udpPort) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeInt(TANK_NEW_MSG);
            dataOutputStream.writeInt(tank.id);
            dataOutputStream.writeInt(tank.tankX);
            dataOutputStream.writeInt(tank.tankY);
            dataOutputStream.writeInt(tank.direction.ordinal());
            dataOutputStream.writeBoolean(tank.isGood());
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes = byteArrayOutputStream.toByteArray();

        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, new InetSocketAddress(IP, udpPort));
        try {
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void parse(DataInputStream dataInputStream) {
        try {
            int id = dataInputStream.readInt();
            if (tankClient.tank.id == id) {
                return;
            }

            int x = dataInputStream.readInt();
            int y = dataInputStream.readInt();
            Direction dir = Direction.values()[dataInputStream.readInt()];
            boolean good = dataInputStream.readBoolean();
            boolean exist = false;

            for (int i = 0; i < tankClient.enemyTanks.size(); i++) {
                Tank t = tankClient.enemyTanks.get(i);
                if (t.id == id) {
                    exist = true;
                    break;
                }
            }

            if (!exist) {
                TankNewMsg tankNewMsg = new TankNewMsg(tankClient.tank);
                tankClient.netClient.send(tankNewMsg);

                Tank tank = new Tank(x, y, good, dir, tankClient);
                tank.id = id;
                tankClient.enemyTanks.add(tank);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
