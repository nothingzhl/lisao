package org.zhl.io.aio;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

/**
 * @program: lisao
 * @description: echo client
 * @author: zhanghanlin
 * @create: 2021-05-04 00:04
 **/
public class AioClient {

    private static final String LOCALHOST = "localhost";
    private static final int DEFAULT_PORT = 8888;
    private static AsynchronousSocketChannel asynSocketClientChannel;

    @SneakyThrows
    public static void main(String[] args) {
        asynSocketClientChannel = AsynchronousSocketChannel.open();
        final Future<Void> result = asynSocketClientChannel.connect(new InetSocketAddress(LOCALHOST, DEFAULT_PORT));

        result.get();
        String input = "hello world";

        final ByteBuffer byteBuffer = ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8));

        final Future<Integer> wResutl = asynSocketClientChannel.write(byteBuffer);

        wResutl.get();
        byteBuffer.flip();
        final Future<Integer> read = asynSocketClientChannel.read(byteBuffer);

        read.get();
        final String s = "form server:=>>" + new String(byteBuffer.array());
        System.out.println(s);
        byteBuffer.clear();
    }
}

