package com.main;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SkyScanner {

    public static void main(String[] args) {
        try {
            FlightsContainer flightsContainer = convertFromJsonToNormal("flights.json",FlightsContainer.class); // получаем обьект после десериализации
            System.out.println("Deserializing object toString():");
            System.out.println("-------------------------------------------------");
            System.out.println(flightsContainer.toString());
            System.out.println("-------------------------------------------------");
            List<Flight> flights = flightsContainer.getFlights();
            System.out.println("List of flights:");
            flights.stream().
                    forEach(e -> System.out.println
                            ("fromCity = " + e.getFromCity() + ", " + "toCity = " + e.getToCity()+ ", "  + "price = " + e.getPrice()));
            System.out.println("-------------------------------------------------");
        } catch (IOException e) {
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
     * @throws IOException
     */
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        T readObj = mapper.readValue(new FileReader(new File(fileName)),clazz);

        return readObj;
    }
}
