package com;

import com.commands.Command;
import com.vehicle.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс, в методе main которого инициируется выполнение программы
 */
public class Main {
    /**
     * Осуществляет выполнение программы
     *
     * @param args Строковый массив, содержащий имя файла, вводимое в командной строке
     */
    public static void main(String[] args) {

        SetOfVehicles set = new SetOfVehicles();
        try {
            set.initialize("objects.xml");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Файла не найдено");
        }
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in))) {
            String line;
            while (!(line = reader.readLine()).equals("exit")) {
                if (line == null || line.equals(""))
                    continue;
                if (line.equalsIgnoreCase("exit"))
                    System.exit(0);
                String argument = null;
                String[] data = line.split(" ", 2);
                if (data.length != 1)
                    argument = data[1];
                try {
                    Command c = (Command) Class.forName(data[0] + "com.commands.Command")
                            .newInstance();
                    System.out.println(c.execute(argument, set));
                } catch (ClassNotFoundException | NoClassDefFoundError | IllegalAccessException | InstantiationException ex) {
                    System.out.println("Нeт такой команды");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Метод для создания нового объекта com.vehicle.Vehicle путём осуществления диалога с пользователем
     *
     * @return Созданныф объект com.vehicle.Vehicle
     */
    public static Vehicle createNewVehicle() {
        Vehicle v = new Vehicle();
        boolean flag;
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            do {
                System.out.println("Введите имя вашего корыта");
                flag = v.setName(reader.readLine());
            }
            while (!flag);
            Coordinates coordinates = new Coordinates();
            do {
                System.out.println("Введите координату X больше -17");
                flag = coordinates.setX(reader.readLine());
            }
            while (!flag);
            do {
                System.out.println("Введите координату Y");
                flag = coordinates.setY(reader.readLine());
            }
            while (!flag);
            v.setCoordinates(coordinates.toString());
            do {
                System.out.println("Введите мощность двигателя");
                flag = v.setEnginePower(reader.readLine());
            }
            while (!flag);
            do {
                System.out.println("Введите потребление топлива");
                flag = v.setFuelConsumption(reader.readLine());
            }
            while (!flag);
            do {
                System.out.println("Введите тип аппарата:\n" + VehicleType.vehicleTypeToString());
                flag = v.setType(reader.readLine());
            }
            while (!flag);
            do {
                System.out.println("Введите тип топлива\n" + FuelType.fuelTypeEoString());
                flag = v.setFuelType(reader.readLine());
            }
            while (!flag);
        } catch (IOException ignored) {
        }
        return v;
    }
}
