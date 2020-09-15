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
        try (FileWriter writer = new FileWriter("java-core" + File.separatorChar + "output.txt",false)) {
            process(writer);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(FileWriter writer) throws InterruptedException, IOException {
        String output = "";
while (running.get()) {
            while (!input.isEmpty()) {
                output+= input.takeFirst() + " ";
                writer.write(output);
            }
            TimeUnit.SECONDS.sleep(1);
        }
        while (!input.isEmpty()) {
            output+= input.takeFirst() + " ";
            writer.write(output);
        }
        Thread.currentThread().interrupt();
    }
}
