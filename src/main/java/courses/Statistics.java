package main.java.courses;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Statistics
 {
    public static void main(String[] args) {
        // Добавляем несколько записей
        Statistics.addEntry("/home", 200, "Windows");
        Statistics.addEntry("/about", 404, "Linux");
        Statistics.addEntry("/contact", 200, "Windows");
        Statistics.addEntry("/home", 200, "MacOS");
        Statistics.addEntry("/home", 500, "Android");
        Statistics.addEntry("/login", 200, "Windows");
        Statistics.addEntry("/dashboard", 200, "Linux");
        Statistics.addEntry("/about", 200, "Linux");

        // Показываем все существующие страницы с кодом 200
        System.out.println("Существующие страницы с ответом 200:");
        for (String page : Statistics.getExistingPages()) {
            System.out.println(page);
        }

        // Получаем статистику по операционным системам
        Map<String, Double> osStats = Statistics.getOsStatistics();

        System.out.println("\nСтатистика по операционным системам (доля):");
        for (Map.Entry<String, Double> entry : osStats.entrySet()) {
            System.out.printf("%s: %.2f%%\n", entry.getKey(), entry.getValue() * 100);
        }
    }

    // Хранит адреса страниц с кодом 200
    private static HashSet<String> existingPages = new HashSet<>();

    // Хранит количество появлений каждой операционной системы
    private static HashMap<String, Integer> osCounts = new HashMap<>();

    // Общее количество записей с ОС
    private static int totalOsCount = 0;

    /**
     * Метод добавления записи (например, из лог-файла).
     * 
     * @param pageAddress Адрес страницы
     * @param responseCode Код ответа сервера (например, 200)
     * @param osName Название операционной системы пользователя
     */
    public static void addEntry(String pageAddress, int responseCode, String osName) {
        // Добавляем адрес страницы, если код ответа 200
        if (responseCode == 200) {
            existingPages.add(pageAddress);
        }

        // Обрабатываем статистику ОС
        if (osName != null && !osName.isEmpty()) {
            osCounts.put(osName, osCounts.getOrDefault(osName, 0) + 1);
            totalOsCount++;
        }
    }

    /**
     * Возвращает множество всех уникальных существующих страниц (адресов).
     * 
     * @return Set<String> адресов страниц.
     */
    public static Set<String> getExistingPages() {
        // Возвращаем копию, чтобы защитить внутреннее состояние
        return new HashSet<>(existingPages);
    }

    /**
     * Возвращает статистику долей операционных систем.
     * Ключ — название ОС, значение — доля в интервале [0,1].
     * 
     * @return Map<String, Double> с долями операционных систем.
     */
    public static Map<String, Double> getOsStatistics() {
        Map<String, Double> osStats = new HashMap<>();
        if (totalOsCount == 0) return osStats; // Защита от деления на ноль

        for (Map.Entry<String, Integer> entry : osCounts.entrySet()) {
            double share = (double) entry.getValue() / totalOsCount;
            osStats.put(entry.getKey(), share);
        }
        return osStats;
    }

}
