package org.zhl.io.nio;

import lombok.SneakyThrows;

import java.io.IOException;
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
public class NioServer {

    @SneakyThrows
    public static void main(final String[] args) {
        System.out.println("服务启动");
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
            final Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

            while (selectionKeyIterator.hasNext()) {
                final SelectionKey key = selectionKeyIterator.next();
                if (!key.isValid()) {
                    continue;
                } else if (key.isAcceptable()) {
                    parserAcceptableEvent(selector, key);
                } else if (key.isReadable()) {
                    parserReadableEvent(selector, key);
                } else if (key.isConnectable()) {
                    parserConnectableEvent(selector, key);
                } else if (key.isWritable()) {
                    parserWritableEvent(selector, key);
                }
                selectionKeyIterator.remove();
            }
        }

    }

    @SneakyThrows
    private static void parserWritableEvent(final Selector selector, final SelectionKey key) {
        final SocketChannel channel = (SocketChannel)key.channel();
        final ByteBuffer attachment = (ByteBuffer)key.attachment();
        attachment.put(Charset.defaultCharset().encode("We Done!"));
        channel.write(attachment);
    }

    /**
     * 链接事件处理方法
     *
     * @param selector
     * @param key
     * @throws IOException
     */
    private static void parserConnectableEvent(final Selector selector, final SelectionKey key) throws IOException {
        System.out.println("进入Connectable!");
        final SocketChannel channel = (SocketChannel)key.channel();
        if (channel.finishConnect()) {
        }
    }

    /**
     * 可读事件处理方法
     *
     * @param key
     * @throws IOException
     */
    private static void parserReadableEvent(final Selector selector, final SelectionKey key) throws IOException {
        System.out.println("进入Readable！");
        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        final SocketChannel channel = (SocketChannel)key.channel();
        final int read = channel.read(buffer);
        if (read == -1) {
            key.cancel();
            channel.close();
        } else {
            buffer.flip();
            key.attach(buffer);
            channel.register(selector, SelectionKey.OP_WRITE);
        }
    }

    /**
     * 接受事件处理办法
     *
     * @param selector
     * @param key
     * @throws IOException
     */
    private static void parserAcceptableEvent(final Selector selector, final SelectionKey key) throws IOException {
        System.out.println("进入Acceptable");
        final ServerSocketChannel channel = (ServerSocketChannel)key.channel();
        final SocketChannel clientChannel = channel.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
    }

}
