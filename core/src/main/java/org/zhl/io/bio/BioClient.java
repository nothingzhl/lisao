package org.zhl.io.bio;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author zhanghanlin
 */
public class BioClient {

    @SneakyThrows
    public static void main(final String[] args) {

        final Socket clientSocket = new Socket("127.0.0.1", 8888);

        final OutputStream outputStream = clientSocket.getOutputStream();
        outputStream.write("Hello World".getBytes(StandardCharsets.UTF_8));

        final byte[] cache = new byte[1024];
        final InputStream inputStream = clientSocket.getInputStream();

        inputStream.read(cache);
        System.out.println(new String(cache, StandardCharsets.UTF_8));

    }

}
