package com.cryptomkt.api;

import com.cryptomkt.api.utils.Subscriber;
import com.cryptomkt.api.utils.SyncJson;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;

public class SubscriberTest {


    // Tests that the Subscriber properly terminates execution once the Thread it's running on is interrupted.
    @Test
    public void testRun() throws InterruptedException {

        ClientMock register = new ClientMock();

        // Creates several incarnations of the client. If the leak manifests and created threads are left alive waiting, at the end there will be one for every iteration of the loop.
        for (int i = 0; i < 7; i++) {
            System.out.println("Re-registering listener on socket");
            register.restart();
            Thread.sleep(100L);
        }

        register.close();
        Thread.sleep(100L);

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        int numberOfThreads = threadGroup.activeCount();
        Thread[] threads = new Thread[numberOfThreads];
        threadGroup.enumerate(threads);
        List<String> subscriberThreads = Arrays.stream(threads)
                .map(Thread::getName)
                .filter(x -> x.startsWith("subscriber"))
                .collect(Collectors.toList());

        System.out.println("Subscriber threads alive and waiting after closing: " + subscriberThreads);

        assertTrue(subscriberThreads.isEmpty());
    }

    /**
     * This will act as consumer, it represents the client side.
     * It holds the callback that will register to consume the json when the event arrives.
     */
    private static class ClientMock {

        private SocketImplMock socket;

        public void restart() {
            if (socket != null) {
                socket.close();
            }
            socket = new SocketImplMock();

            socket.onBalance(json -> json.keys().forEachRemaining(key -> {
                try {
                    String strKey = (String) key;
                    JSONObject balanceObject = json.getJSONObject(strKey);
                    System.out.println("Event consumed: " + balanceObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }));
        }

        public void close() {
            System.out.println("About to close socket");
            socket.close();
        }
    }

    /**
     * This will act as producer with an scheduled thread writing on the balancePub every 1 second.
     */
    private static class SocketImplMock implements Closeable {

        private static final AtomicInteger seq = new AtomicInteger(1);

        final SyncJson balancePub;

        private final ScheduledExecutorService scheduledExecutorService;
        private final List<Subscriber> subscribers;

        public SocketImplMock() {
            balancePub = new SyncJson();

            scheduledExecutorService = Executors.newScheduledThreadPool(8);
            subscribers = new ArrayList<>();

            scheduledExecutorService.scheduleAtFixedRate(() -> {
                try {
                    System.out.println("Event produced");
                    synchronized (balancePub) {
                        balancePub.setData("{ \"field\": { \"inner\": \"value\"} }");
                        balancePub.notifyAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 1, 1, TimeUnit.SECONDS);
        }

        public void onBalance(Consumer<JSONObject> consumer) {
            Subscriber subscriber = new Subscriber(consumer, balancePub);
            subscriber.setName("subscriber-" + seq.getAndIncrement());
            this.subscribers.add(subscriber);
            subscriber.start();
        }

        @Override
        public void close() {
            this.subscribers.forEach(Thread::interrupt);
            this.scheduledExecutorService.shutdown();
        }
    }
}
