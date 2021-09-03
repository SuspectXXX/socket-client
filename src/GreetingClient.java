import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class GreetingClient {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 7000;

        Socket socket = null;
        //创建套接字socket连接到指定端口号
        try {
            socket = new Socket(host, port);

            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            Scanner sc = new Scanner(System.in);

            boolean flag = true;

            while(flag) {
                System.out.print("名字：");
                String name = sc.nextLine();
                System.out.print("家乡：");
                String city = sc.nextLine();

                dataOutputStream.writeUTF(name + "--" + city);
                dataOutputStream.flush();

                String description = dataInputStream.readUTF();

                System.out.println(description);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
