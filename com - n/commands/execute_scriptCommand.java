package com.commands;

import com.vehicle.Coordinates;
import com.vehicle.SetOfVehicles;
import com.vehicle.Vehicle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Класс, осуществляющий выполнение команд из файла
 * и предоставляющий метод для создания новых объектов типа com.vehicle.Vehicle
 * на основе данных из файла
 */
public class execute_scriptCommand extends Command {

    private static BufferedReader reader = null;
    private static HashMap<String, BufferedReader> files = new HashMap<>();
    private String filename;

    /**
     * Создаёт новый объект типа com.vehicle.Vehicle на основе данных из файла
     *
     * @return Новый объект типа com.vehicle.Vehicle либо null в случае, если данные для ввода
     * указаны неверно.
     */
    public static Vehicle createVehicleFromFile() {
        Vehicle v = new Vehicle();
        try {
            boolean addOrNot;
            String s;
            addOrNot = v.setName(reader.readLine());
            Coordinates coordinates = new Coordinates();
            addOrNot = addOrNot && coordinates.setX(reader.readLine());
            s = reader.readLine();
            addOrNot = addOrNot && coordinates.setY(s);
            if (addOrNot) v.setCoordinates(coordinates.toString());
            addOrNot = addOrNot && v.setEnginePower(reader.readLine());
            addOrNot = addOrNot && v.setFuelConsumption(reader.readLine());
            addOrNot = addOrNot && v.setType(reader.readLine());
            addOrNot = addOrNot && v.setFuelType(reader.readLine());
            if (addOrNot)
                return v;
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    /**
     * Осуществляет выполнение команд из файла
     *
     * @param o   Имя файла с командами для выполнения
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Результат выполнения команд или сообщение об ошибке
     */
    @Override
    public String execute(Object o, SetOfVehicles set) {
        filename = (String) o;
        String current = filename;
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        try {
            BufferedReader currentReader;
            if (files.containsKey(filename))
                currentReader = files.get(filename);
            else {
                currentReader = new BufferedReader(new FileReader(filename));
                files.put(filename, currentReader);
            }
            reader = currentReader;
            String line;
            try {
                while ((line = currentReader.readLine()) != null && (!(line.equals("exit")))) {
                    if (line.equals(""))
                        continue;
                    String argument = null;
                    String[] data = line.split(" ", 2);
                    if (data.length != 1)
                        argument = data[1];
                    try {
                        Command c = (Command) Class.forName(data[0] + "com.commands.Command").newInstance();
                        if (c.getClass().getDeclaredMethod("execute", Object.class, set.getClass()).isAnnotationPresent(Creative.class)) {
                            builder.append(c.execute(argument, set));
                            builder.append("\n");
                        } else {
                            builder.append(c.execute(argument, set));
                            builder.append("\n");
                        }
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | SecurityException e) {
                    }
                }
            } catch (IOException e) {
                return builder.toString();
            }
            currentReader.close();
            files.remove(current);
            return builder.toString();
        } catch (IOException | NoClassDefFoundError ex) {
            ex.printStackTrace();
            return "Ошибка";
        }
    }
}