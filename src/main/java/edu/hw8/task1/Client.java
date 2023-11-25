package edu.hw8.task1;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import lombok.SneakyThrows;

public class Client {

    private static final int PORT = 8080;
    private static final int BUFFER_SIZE = 1024;
    private static final String HOST = "localhost";
    private static final List<String> CLIENT_MESSAGES = List.of("личности", "оскорбления", "глупый", "интеллект");

    @SneakyThrows
    public void connect(String messageToSend) {
        InetSocketAddress hostAddress = new InetSocketAddress(HOST, PORT);
        try (SocketChannel client = SocketChannel.open(hostAddress)) {
            ByteBuffer buffer = ByteBuffer.wrap(messageToSend.getBytes(StandardCharsets.UTF_8));
            while (buffer.hasRemaining()) {
                client.write(buffer);
            }
            buffer.flip();
            buffer = ByteBuffer.allocate(BUFFER_SIZE);
            int bytesRead;
            while ((bytesRead = client.read(buffer)) != -1) {
                if (bytesRead == 0) {
                    continue;
                }
                System.out.println(new String(buffer.array(), StandardCharsets.UTF_8));
                break;
            }
        }
    }

    private static String getRandomMessage() {
        Random random = ThreadLocalRandom.current();
        return CLIENT_MESSAGES.get(random.nextInt(CLIENT_MESSAGES.size()));
    }
}
