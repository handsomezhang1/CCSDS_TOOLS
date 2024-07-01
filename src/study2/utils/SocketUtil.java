package study2.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketUtil {

    private String host;
    private int port;

    public SocketUtil(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void sendString(String message) throws IOException {
    	System.out.println(host);
    	System.out.println(port);
        try (Socket socket = new Socket(host, port);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            writer.write(message);
            writer.flush();
        }
    }

    public String receiveString() throws IOException {
        try (Socket socket = new Socket(host, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }
    }
    
    /**
     * 检查给定的IP地址和端口号的网络连接是否通畅。
     * 
     * @param ipAddress IP地址，如 "1.1.1.1"
     * @param port 端口号
     * @return 如果网络通畅返回1，否则返回0
     */
    public static int checkConnectivity(String ipAddress, int port) {
        try (Socket socket = new Socket()) {
            // 设置连接超时时间为5秒
            int timeout = 5000; 
            socket.connect(new InetSocketAddress(ipAddress, port), timeout);
            // 如果代码执行到这里，说明连接成功
            return 1;
        } catch (IOException e) {
            // 发生异常，说明连接失败
            return 0;
        }
    }
    
    public static void main(String[] args) {
        String ip = "192.168.17.1"; // 请替换为实际的IP地址
        int port = 80; // 请替换为实际的端口号
        int result = checkConnectivity(ip, port);
        if (result == 1) {
            System.out.println("网络连接通畅");
        } else {
            System.out.println("无法连接到指定的网络地址");
        }
    }
}
/***使用此工具类发送和接收数据时，您需要创建一个实例，并提供正确的服务器主机地址和端口号。然后，您可以调用sendString来发送数据，以及receiveString来接收数据。
 * SocketUtil socketUtil = new SocketUtil("127.0.0.1", 12345);
socketUtil.sendString("Hello, server!");
String response = socketUtil.receiveString();
System.out.println("Received from server: " + response);

 * 
 * /
 */