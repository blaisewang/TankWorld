package tancky;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class MissileNewMsg implements Msg {
    private Missile missile;
    private TankClient tankClient;

    MissileNewMsg(Missile missile) {
        this.missile = missile;
    }

    MissileNewMsg(TankClient tankClient) {
        this.tankClient = tankClient;
    }

    public void send(DatagramSocket datagramSocket, String IP, int udpPort) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(byteArrayOutputStream);
        try {
            dos.writeInt(MISSILE_NEW_MSG);
            dos.writeInt(missile.tankID);
            dos.writeInt(missile.id);
            dos.writeInt(missile.x);
            dos.writeInt(missile.y);
            dos.writeInt(this.missile.direction.ordinal());
            dos.writeBoolean(missile.isGood());
            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] byteArray = byteArrayOutputStream.toByteArray();

        DatagramPacket datagramPacket = new DatagramPacket(byteArray, byteArray.length, new InetSocketAddress(IP, udpPort));
        try {
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(DataInputStream dataInputStream) {
        try {
            int tankId = dataInputStream.readInt();
            if (tankId == tankClient.tank.id) {
                return;
            }
            int readInt = dataInputStream.readInt();

            int x = dataInputStream.readInt();
            int y = dataInputStream.readInt();
            Direction direction = Direction.values()[dataInputStream.readInt()];
            boolean isGood = dataInputStream.readBoolean();
            Missile missile = new Missile(tankId, x, y, isGood, direction, tankClient);
            missile.id = readInt;
            tankClient.missiles.add(missile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
