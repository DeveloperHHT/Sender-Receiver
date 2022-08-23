import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import javax.sound.sampled.Port;
import java.net.SocketException;

public class ServerClient{

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            try (// Step 1:Create the UDP socket object for carrying the message
                 DatagramSocket ds = new DatagramSocket(1239)) {
                InetAddress ip = InetAddress.getLocalHost();

                byte buf[] = null;
                byte[] receive = new byte[65535];
                DatagramPacket DpReceive = null;


                while(true)
                {
                    String inp = sc.nextLine();

                    // convert the String input into the byte array.
                    buf = inp.getBytes();

                    // Step 2 : Create the datagramPacket for sending
                    // the message.
                    DatagramPacket DpSend =
                            new DatagramPacket(buf, buf.length, ip, 1239);

                    // Step 3 : invoke the send call to actually send
                    // the data.
                    ds.send(DpSend);

                    // break the loop if user enters "exit"
                    if (inp.equals("exit"))
                        break;

                    // Step 2 : create a DatgramPacket to receive the data.
                    DpReceive = new DatagramPacket(receive, receive.length);

                    // Step 3 : revieve the data in byte buffer.
                    ds.receive(DpReceive);
                    System.out.println("Client:-" + data(receive));

                    // Exit the server if the client sends "bye"
                    if (data(receive).toString().equals("exit"))
                    {
                        System.out.println("Client sent exit.....EXITING");
                        break;
                    }

                    // Clear the buffer after every message.
                    receive = new byte[65535];
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }

}