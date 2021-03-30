package org.zhl.io.bio;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanghanlin
 */
public class BioThreadPoolServer {

    @SneakyThrows
    public static void main(final String[] args) {
        final ExecutorService executorService =
            new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        System.out.println("服务启动");

        final ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 8888), 50);
        Socket socket;

        while ((socket = serverSocket.accept()) != null) {
            final Socket cSocket = socket;
            executorService.submit(() -> {
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
            });
        }
        System.out.println("服务停止");
    }
}