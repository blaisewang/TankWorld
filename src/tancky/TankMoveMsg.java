package tancky;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TankMoveMsg implements Msg {
    private int id;
    private int x;
    private int y;
    private Direction direction;
    private Direction ptDirection;
    private TankClient tankClient;

    TankMoveMsg(int id, int x, int y, Direction direction, Direction ptDirection) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.ptDirection = ptDirection;
    }

    TankMoveMsg(TankClient tankClient) {
        this.tankClient = tankClient;
    }

    public void send(DatagramSocket datagramSocket, String IP, int udpPort) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(byteArrayOutputStream);
        try {
            dos.writeInt(Msg.TANK_MOVE_MSG);
            dos.writeInt(this.id);
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(this.direction.ordinal());
            dos.writeInt(this.ptDirection.ordinal());
            dos.flush();
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

    @Override
    public void parse(DataInputStream dataInputStream) {
        try {
            int id = dataInputStream.readInt();

            if (tankClient.tank.id == id) {
                return;
            }

            int x = dataInputStream.readInt();
            int y = dataInputStream.readInt();
            Direction dir = Direction.values()[dataInputStream.readInt()];
            Direction ptDir = Direction.values()[dataInputStream.readInt()];

            for (int i = 0; i < tankClient.enemyTanks.size(); i++) {
                Tank t = tankClient.enemyTanks.get(i);
                if (t.id == id) {
                    t.tankX = x;
                    t.tankY = y;
                    t.direction = dir;
                    t.barrelDirection = ptDir;
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
