package org.zhl.netty.protool;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.Objects;

/**
 * @author zhanghanlin
 * @date 2023/6/14
 **/
public class PacketCodeC {

    public static PacketCodeC DEFAULT_INSTANCE = new PacketCodeC(new JSONSerializer());

    private final Serializer serializer;

    public PacketCodeC(Serializer serializer) {
        this.serializer = serializer;
    }

    private static final int MAGIC_NUMBER = 0x12345678;

    public ByteBuf encode(Packet packet){

        // 1.创建ByteBuf对象
        final ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        // 2. 序列化Java对象
        final byte[] bytes = serializer.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(serializer.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public <T> T decode(ByteBuf byteBuf,Class<T> clazz){
        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本
        byteBuf.skipBytes(1);
        // 序列化的算法
        final byte serializeAlgorithm = byteBuf.readByte();

        if (!Objects.equals(serializeAlgorithm,serializer.getSerializerAlgorithm())){
            throw new RuntimeException("算法不匹配");
        }

        // 指令
        final byte command = byteBuf.readByte();
        // 数据长度
        final int length = byteBuf.readInt();
        // 数据
        final byte[] data = new byte[length];
        byteBuf.readBytes(data);

        if (clazz != null && serializer != null) {
            return serializer.deserialize(clazz, data);
        }

        return null;
    }



}
