package org.zhl.io.bio;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 无限制版 线程 Bio
 *
 * @author zhanghanlin
 */
public class BioThreadServer {

    @SneakyThrows
    public static void main(final String[] args) {

        System.out.println("服务启动");

        final ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 8888), 50);
        Socket socket;

        while ((socket = serverSocket.accept()) != null) {
            final Socket cSocket = socket;
            new Thread(() -> {
                try {
                    final byte[] dataCache = new byte[1024];
                    final InputStream inputStream = cSocket.getInputStream();
                    inputStream.read(dataCache);
                    System.out.println("接受到：" + new String(dataCache, StandardCharsets.UTF_8));
                    final OutputStream outputStream = cSocket.getOutputStream();
                    outputStream.write(dataCache);
                    cSocket.close();
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }).start();

        }
        System.out.println("服务停止");
    }

}
