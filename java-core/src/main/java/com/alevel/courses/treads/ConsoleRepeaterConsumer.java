package com.alevel.courses.treads;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsoleRepeaterConsumer implements Runnable {


    private AtomicBoolean running;

    private final BlockingDeque<String> input;

    public ConsoleRepeaterConsumer(BlockingDeque<String> input, AtomicBoolean running) {
        this.running = running;
        this.input = input;
    }

    @Override
    public void run() {
        try (FileWriter writer = new FileWriter("java-core" + File.separatorChar + "output.txt")) {
            process(writer);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(FileWriter writer) throws InterruptedException, IOException {
        while (running.get()) {
            while (!input.isEmpty()) {
                writer.write(input.takeFirst() + " ");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        while (!input.isEmpty()) {
            writer.write(input.takeFirst() + " ");
        }
        Thread.currentThread().interrupt();
    }
}
