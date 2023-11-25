package edu.hw8.task1;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.Semaphore;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RequestWorker implements Runnable {

    private static final int BUFFER_SIZE = 1024;

    private static final Map<String, String> RESPONSES = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );

    private final SocketChannel client;
    private final Semaphore semaphore;

    public RequestWorker(SocketChannel client, Semaphore semaphore) {
        this.client = client;
        this.semaphore = semaphore;
    }

    @Override
    @SneakyThrows
    public void run() {
        try (Selector selector = Selector.open()) {
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            while (client.isOpen()) {
                selector.select();
                for (SelectionKey key : selector.keys()) {
                    if (key.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                        int bytesRead = client.read(buffer);
                        if (bytesRead > 0) {
                            String request = new String(buffer.array(), StandardCharsets.UTF_8);
                            System.out.println("Ваня: " + request);
                            ByteBuffer response =
                                ByteBuffer.wrap(("Сервер: " + RESPONSES.get(request.trim())).getBytes(StandardCharsets.UTF_8));
                            while (response.hasRemaining()) {
                                client.write(response);
                            }
                            buffer.flip();
                            client.close();
                        }
                    }
                }
            }
            semaphore.release();
        }
    }
}
