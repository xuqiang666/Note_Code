package com.y.io;

import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelTest {

    @Test
    public void client() throws Exception {
        int i = 1;
        while (i <= 2) {
            Socket socket = new Socket("127.0.0.1", 520);
            //向服务器端第一次发送字符串
            OutputStream netOut = socket.getOutputStream();
            InputStream io = socket.getInputStream();
            String msg = i == 1 ? "客户端：我知道我是任性太任性，伤透了你的心。我是追梦的人，追一生的缘分。" :
                    "客户端：我愿意嫁给你，你却不能答应我。";
            System.out.println(msg);
            netOut.write(msg.getBytes());
            netOut.flush();
            byte[] bytes = new byte[i == 1 ? 104 : 64];
            io.read(bytes);
            String response = new String(bytes);
            System.out.println(response);
            netOut.close();
            io.close();
            socket.close();
            i++;
        }
    }

    @Test
    public void server() throws Exception {
        ServerSocket serverSocket = new ServerSocket(520);
        int i = 1;
        while (i <= 2) {
            String msg = i == 1 ? "服务端：我知道你是任性太任性，伤透了我的心。同是追梦的人，难舍难分。" :
                    "服务端：你愿意嫁给你，我却不能向你承诺。";
            Socket socket = serverSocket.accept();
            InputStream io = socket.getInputStream();
            byte[] bytes = new byte[i == 1 ? 112 : 64];
            io.read(bytes);
            System.out.println(new String(bytes));
            OutputStream os = socket.getOutputStream();
            System.out.println(msg);
            byte[] outBytes = msg.getBytes();
            os.write(outBytes);
            os.flush();
            os.close();
            io.close();
            i++;
        }
    }

    @Test
    public void getChannel() throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 520);
        for (int i = 1; i <= 2; i++) {
            SocketChannel socketChannel = SocketChannel.open(inetSocketAddress);
            String msg = i == 1 ? "客户端：我知道我是任性太任性，伤透了你的心。我是追梦的人，追一生的缘分。" :
                    "客户端：我愿意嫁给你，你却不能答应我。";

            ByteBuffer writeBuffer = ByteBuffer.allocate(20000);

            writeBuffer.put(msg.getBytes());
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
            writeBuffer.clear();

            ByteBuffer readBuffer = ByteBuffer.allocate(20000);
            readBuffer.flip();
            socketChannel.read(readBuffer);

            socketChannel.close();
        }
    }
}
