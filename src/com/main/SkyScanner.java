package com.main;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SkyScanner {

    public static void main(String[] args) {
        try {
            FlightsContainer flightsContainer = convertFromJsonToNormal("flights.json",FlightsContainer.class); // получаем обьект после десериализации
            System.out.println("-------------------------------------------------");
            List<Flight> flights = flightsContainer.getFlights();
            List<Flight> sortedFlights = new ArrayList<>();

            flights.stream()
                    .filter(i -> i.getFromCity().equals("Москва") && i.getToCity().equals("Хабаровск")) //фильтрация по направлению Москва -> Хабаровск
                    .sorted() //Сортировка по цене билета
                    .forEach(sortedFlights::add);// Запись в новый отсортированный List

            System.out.println("Минимальная стоимость перелета:");
            System.out.println(sortedFlights.get(0).getPrice());
            System.out.println("Максимальная стоимость перелета:");
            System.out.println(sortedFlights.get(sortedFlights.size() - 1).getPrice());
            double avaragePrice = sortedFlights.stream().
                    mapToInt(Flight::getPrice).average().
                    orElseThrow(() -> new IllegalStateException("There no flights to get average"));
            System.out.println("Средняя стоимость перелета:");
            System.out.println(avaragePrice);
            System.out.println("-------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для десериализации JSON- файла в Object типа <T>
     * @param fileName Имя файла (JSON) для десериализации
     * @param clazz Имя класса для десериализации из JSON-формата
     * @param <T> Тип класса - обьекта, который будет возвращен
     * @author Evgeniy Shabalin
     * @return <T> object
     * @throws IOException when file not exist
     */
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new FileReader(fileName),clazz);
    }
}
