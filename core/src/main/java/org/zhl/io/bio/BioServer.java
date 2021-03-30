package org.zhl.io.bio;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author zhanghanlin
 */
public class BioServer {

    @SneakyThrows
    public static void main(final String[] args) {

        System.out.println("服务启动");
        final ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 8888), 50);
        Socket socket;

        final byte[] dataCache = new byte[1024];

        while ((socket = serverSocket.accept()) != null) {
            final InputStream inputStream = socket.getInputStream();
            inputStream.read(dataCache);
            System.out.println("接受到：" + new String(dataCache, StandardCharsets.UTF_8));
            final OutputStream outputStream = socket.getOutputStream();
            outputStream.write(dataCache);
            socket.close();
        }
        System.out.println("服务停止");
    }
}
