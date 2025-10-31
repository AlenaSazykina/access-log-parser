package main.java.courses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import access.log;

public class LogAnalyzer {
    public static void main(String[] args) {
        String filename = "access.log"; // замените на название вашего файла или передавайте через args
        int totalRequests = 0;
        int googlebotCount = 0;
        int yandexbotCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                totalRequests++;
                // Разделяем всю строку по кавычкам, так как User-Agent окружен кавычками
                String[] parts = line.split("\"");
                if (parts.length < 6) continue; // пропускаем malformed строки

                // Пользовательский агент находится внутри шестого элемента (индекс 5 в массиве after split)
                String userAgentPart = parts[5];

                // Найдем User-Agent внутри этого фрагмента
                String userAgent = extractUserAgent(userAgentPart);
                if (userAgent == null) continue;

                // Обрабатываем User-Agent
                String program = extractProgramFromUserAgent(userAgent);

                if (program.equalsIgnoreCase("Googlebot")) {
                    googlebotCount++;
                } else if (program.equalsIgnoreCase("YandexBot")) {
                    yandexbotCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Вычисляем доли
        double googlebotShare = totalRequests > 0 ? (double) googlebotCount / totalRequests : 0;
        double yandexbotShare = totalRequests > 0 ? (double) yandexbotCount / totalRequests : 0;

        System.out.println("Общее число запросов: " + totalRequests);
        System.out.println("Запросов от Googlebot: " + googlebotCount);
        System.out.println("Запросов от YandexBot: " + yandexbotCount);
        System.out.printf("Доля Googlebot: %.2f%%\n", googlebotShare * 100);
        System.out.printf("Доля YandexBot: %.2f%%\n", yandexbotShare * 100);
    }

    private static String extractUserAgent(String userAgentPart) {

        return userAgentPart.trim();
    }

    private static String extractProgramFromUserAgent(String userAgent) {
    if (userAgent == null || userAgent.isEmpty()) {
        return "";
    }

    int startIdx = userAgent.indexOf('(');
    int endIdx = userAgent.indexOf(')');
    if (startIdx == -1 || endIdx == -1 || endIdx <= startIdx) {
        return "";
    }

    String insideBrackets = userAgent.substring(startIdx + 1, endIdx).trim();

    // Разделить по точке с запятой
    String[] parts = insideBrackets.split(";");
    if (parts.length < 2) {
        return "";
    }

    String secondPart = parts[1].trim();

    // Взять часть до слэша
    int slashIdx = secondPart.indexOf('/');
    String program = (slashIdx != -1) ? secondPart.substring(0, slashIdx) : secondPart;

    return program;
}
}
