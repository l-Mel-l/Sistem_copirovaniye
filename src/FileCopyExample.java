import java.io.*;

public class FileCopyExample {
    public static void main(String[] args) {
        String sourceFilePath1 = "C:\\Users\\79623\\IdeaProjects\\Sistem_copirovaniye\\Из чего копировать.txt";
        String sourceFilePath2 = "C:\\Users\\79623\\IdeaProjects\\Sistem_copirovaniye\\Из чего копировать 2";
        String destinationFilePath1 = "C:\\Users\\79623\\IdeaProjects\\Sistem_copirovaniye\\Куда копировать.txt";
        String destinationFilePath2 = "C:\\Users\\79623\\IdeaProjects\\Sistem_copirovaniye\\Куда копировать 2";

        // Последовательное копирование файлов
        long startTime = System.currentTimeMillis();
        posCopy(sourceFilePath1, destinationFilePath1);
        posCopy(sourceFilePath2, destinationFilePath2);
        long endTime = System.currentTimeMillis();
        long sequentialTime = endTime - startTime;

        // Параллельное копирование файлов
        startTime = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> parallelCopy(sourceFilePath1, destinationFilePath1));
        Thread thread2 = new Thread(() -> parallelCopy(sourceFilePath2, destinationFilePath2));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        long parallelTime = endTime - startTime;

        System.out.println("Последовательное копирование: " + sequentialTime + "ms");
        System.out.println("Параллельное копирование: " + parallelTime + "ms");
    }

    // Метод для последовательного копирования файла
    private static void posCopy(String sourceFilePath, String destinationFilePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для параллельного копирования файла
    private static void parallelCopy(String sourceFilePath, String destinationFilePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}