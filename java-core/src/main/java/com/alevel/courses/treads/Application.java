package com.alevel.courses.treads;


import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class Application {

//    Пользователь вводит строки в консоль. Введенный текст накапливается в переменной input.
//    Параллельный поток раз в секунду проверяет значение input, и, если оно изменилось, записывает его в файл output.txt (значение перезаписывается каждый раз).
//    Если пользователь введет “quit” - ввод прекращается, input записывается в файл и выполнение программы завершается.
//    Строка “quit” в файл не пишется.


    public static void main(String[] args) {
        BlockingDeque<String> input = new LinkedBlockingDeque<>();
        AtomicBoolean running = new AtomicBoolean(true);
        new Thread(new ConsoleRepeaterProducer(input, running)).start();
        new Thread(new ConsoleRepeaterConsumer(input, running)).start();
    }
}