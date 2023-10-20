import java.io.*;

public class FileCopyExample {
    public static void main(String[] args) {
        String NachFilePath1 = "C:\\Users\\79623\\IdeaProjects\\Sistem_copirovaniye\\Из чего копировать.txt";
        String NachFilePath2 = "C:\\Users\\79623\\IdeaProjects\\Sistem_copirovaniye\\Из чего копировать 2";
        String KonFilePath1 = "C:\\Users\\79623\\IdeaProjects\\Sistem_copirovaniye\\Куда копировать.txt";
        String KonFilePath2 = "C:\\Users\\79623\\IdeaProjects\\Sistem_copirovaniye\\Куда копировать 2";

        // Последовательное копирование файлов
        long startTime = System.currentTimeMillis();
        posCopy(NachFilePath1, KonFilePath1);
        posCopy(NachFilePath2, KonFilePath2);
        long endTime = System.currentTimeMillis();
        long sequentialTime = endTime - startTime;

        // Параллельное копирование файлов
        startTime = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> parallelCopy(NachFilePath1, KonFilePath1));
        Thread thread2 = new Thread(() -> parallelCopy(NachFilePath2, KonFilePath2));
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
    private static void posCopy(String NachFilePath, String KonFilePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(NachFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(KonFilePath));
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
    private static void parallelCopy(String NachFilePath, String KonFilePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(NachFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(KonFilePath));
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