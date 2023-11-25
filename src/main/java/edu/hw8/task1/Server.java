package edu.hw8.task1;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Server {

    private static final int PORT = 8080;
    private static final String HOST = "localhost";
    private static final int NUMBER_OF_THREADS = 8;
    private static final int MAX_CONNECTIONS = 3;

    private final ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private final Semaphore semaphore = new Semaphore(MAX_CONNECTIONS, true);

    @SneakyThrows
    public void start() {
        try (Selector selector = Selector.open();
            ServerSocketChannel channel = ServerSocketChannel.open()) {
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(HOST, PORT));
            channel.register(selector, SelectionKey.OP_ACCEPT);
            while (channel.isOpen()) {
                selector.select();
                for (SelectionKey key : selector.keys()) {
                    if (semaphore.tryAcquire()) {
                        if (key.isAcceptable()) {
                            startExecution(channel.accept());
                        }
                        selector.selectedKeys().remove(key);
                    }
                }
            }
        }
    }

    private void startExecution(SocketChannel client) {
        executor.execute(new RequestWorker(client, semaphore));
    }
}
