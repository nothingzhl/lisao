package org.zhl.io.nio;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhanghanlin
 */
@Slf4j
public class NioServer {

    @SneakyThrows
    public static void main(final String[] args) {
        log.info("服务启动");
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8888), 50);
        serverSocketChannel.configureBlocking(false);

        // 开启Selector
        final Selector selector = Selector.open();
        //注册监听链接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();

            final Set<SelectionKey> selectionKeys = selector.selectedKeys();

            final Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {

                final SelectionKey key = iterator.next();

                try {
                    handle(selector, key);
                } catch (final Exception e) {
                    e.printStackTrace();
                }

                iterator.remove();

            }

        }

    }

    private static void handle(final Selector selector, final SelectionKey key) throws Exception {

        if (!key.isValid()) {
            key.cancel();
        }

        if (key.isAcceptable()) {
            final ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
            final SocketChannel accept = serverSocketChannel.accept();
            accept.configureBlocking(false);
            accept.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            final SocketChannel socketChannel = (SocketChannel)key.channel();
            final ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            readBuffer.clear();
            socketChannel.read(readBuffer);
          log.info(new String(readBuffer.array()));
            key.interestOps(SelectionKey.OP_WRITE);
        } else if (key.isWritable()) {
            final SocketChannel socketChannel = (SocketChannel)key.channel();
            final ByteBuffer buffer = ByteBuffer.allocate(128);
            buffer.rewind();
            if (buffer.isReadOnly()) {
                throw new RuntimeException("有问题");
            }
            buffer.put(Charset.defaultCharset().encode("we done!").array());
            log.info("echo =====>" + new String(buffer.array()));
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
            key.interestOps(SelectionKey.OP_READ);
        } else if (key.isConnectable()) {
            final SocketChannel channel = (SocketChannel)key.channel();
            channel.register(selector, SelectionKey.OP_READ);
        }

    }

}
