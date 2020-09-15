package com.alevel.courses.threads;

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
        try {
            process();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void process() throws IOException, InterruptedException {
        String output = null;
        while (running.get()) {

            while (!input.isEmpty()) {
                output += input.takeFirst() + " ";
                try (FileWriter writer = new FileWriter("java-core" + File.separatorChar + "output.txt", false)) {
                    writer.write(output);
                }
                TimeUnit.SECONDS.sleep(1);
            }

            while (!input.isEmpty()) {
                output += input.takeFirst() + " ";
                try (FileWriter writer = new FileWriter("java-core" + File.separatorChar + "output.txt", false)) {
                    writer.write(output);
                }
            }
            Thread.currentThread().interrupt();
        }
    }
}
