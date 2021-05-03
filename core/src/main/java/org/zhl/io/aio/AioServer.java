package org.zhl.io.aio;

import com.google.common.collect.Maps;
import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Map;
import java.util.Objects;

/**
 * @program: lisao
 * @description: echo server
 * @author: zhanghanlin
 * @create: 2021-05-03 23:17
 **/
public class AioServer {

    private static final String LOCALHOST = "localhost";
    private static final int DEFAULT_PORT = 8888;
    private static AsynchronousServerSocketChannel asynSocketChannel;

    @SneakyThrows
    public static void main(String[] args) {
        // AsynchronousChannelGroup.withCachedThreadPool(Executors.newSingleThreadExecutor(), 10);
        // bind listen port
        asynSocketChannel = AsynchronousServerSocketChannel.open();
        asynSocketChannel.bind(new InetSocketAddress(LOCALHOST, DEFAULT_PORT));
        System.out.println("启动服务器，监听端口：" + DEFAULT_PORT);
        while (true) {
            asynSocketChannel.accept(null, new AcceptHandler());
            // 利用read 来阻塞循环
            System.in.read();
        }
    }

    /**
     * 处理函数
     */
    static class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {

        @Override
        public void completed(AsynchronousSocketChannel result, Object attachment) {
            // 判读Server是否关闭,no:继续监听下一个请求
            if (asynSocketChannel.isOpen()) {
                asynSocketChannel.accept(null, this);
            }
            AsynchronousSocketChannel asyncSocketChannel = result;
            if (Objects.nonNull(asynSocketChannel) && asyncSocketChannel.isOpen()) {
                final ClientHandler clientHandler = new ClientHandler(asyncSocketChannel);
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                Map<String, Object> attachmentMap = Maps.newHashMap();
                attachmentMap.put("buffer_type", "read");
                attachmentMap.put("buffer", buffer);
                // 结果回调
                asyncSocketChannel.read(buffer, attachmentMap, clientHandler);
            }
        }

        @Override
        public void failed(Throwable exc, Object attachment) {

        }
    }

    /**
     * 结果处理类
     */
    static class ClientHandler implements CompletionHandler<Integer, Object> {

        private AsynchronousSocketChannel socketChannel;

        public ClientHandler(AsynchronousSocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void completed(Integer result, Object attachment) {
            Map<String, Object> attachmentMap = ((Map<String, Object>)attachment);

            final String type = (String)attachmentMap.getOrDefault("buffer_type", "read");

            // 分别处理读/写
            if (Objects.equals(type, "read")) {
                final ByteBuffer buffer = (ByteBuffer)attachmentMap.get("buffer");
                buffer.flip();
                attachmentMap.put("buffer_type", "write");
                socketChannel.write(buffer, attachment, this);
            } else if (Objects.equals(type, "write")) {
                final ByteBuffer buffer = ByteBuffer.allocate(1024);
                attachmentMap.put("buffer_type", "read");
                attachmentMap.put("buffer", buffer);
                socketChannel.read(buffer, attachmentMap, this);
            }

        }

        @Override
        public void failed(Throwable exc, Object attachment) {

        }
    }

}
