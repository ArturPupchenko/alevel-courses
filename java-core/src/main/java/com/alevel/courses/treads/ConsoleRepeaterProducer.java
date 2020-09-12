package com.alevel.courses.treads;

import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsoleRepeaterProducer implements Runnable {

    private AtomicBoolean running;

    private final BlockingDeque<String> input;

    public ConsoleRepeaterProducer(BlockingDeque<String> input, AtomicBoolean running) {
        this.running = running;
        this.input = input;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void process() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String currentString = scanner.nextLine();
        while (!currentString.equals("quit")) {
            input.addLast(currentString);
            currentString = scanner.nextLine();
        }
        running.set(false);
        Thread.currentThread().interrupt();
    }
}
