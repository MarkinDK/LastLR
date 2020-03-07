package com.commands;

import com.Main;
import com.vehicle.SetOfVehicles;
import com.vehicle.Vehicle;

/**
 * Класс, осуществляющий обновление элемента по вводимому id,
 * передаваемому в качестве первого параметра. В случае, если элемент с таким id содержится в коллекции, удаляет его
 * и затем добавляет заданный. Если элемента с таким id  нет, добавляет заданный элемент в коллекцию.
 */
public class updateCommand extends Command {
    /**
     * Обновление элемента по вводимому id
     * @param o Строка или объект типа com.vehicle.Vehicle
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Результат выполнения команды
     */
    @Override
    @Creative
    public String execute(Object o, SetOfVehicles set) {
        String arg = (String) o;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace[2].getMethodName().equals("main")) {
            if (arg != null && arg.length() != 0) {
                try {
                    long id = Long.parseLong(arg);
                    if (id <= 0)
                        return "id должен быть положительным";
                    if (set.contains(id)) {
                        set.remove_by_id(id);
                        Vehicle newVehicle;
                        newVehicle = Main.createNewVehicle();
                        newVehicle.fullyInitialize().setId(String.valueOf(id));
                        set.add(newVehicle);
                        return "Выполнено";
                    } else return "Нет элемента с таким id";
                } catch (NumberFormatException ignored) {
                    return "Неверный id";
                }
            }
            return "Введите заново";
        } else {
            if (arg != null && arg.length() != 0) {
                try {
                    long id = Long.parseLong(arg);
                    if (id <= 0)
                        return "id должен быть положительным";
                    if (set.contains(id)) {
                        set.remove_by_id(id);
                        Vehicle newVehicle = execute_scriptCommand.createVehicleFromFile();
                        if (newVehicle != null) {
                            newVehicle.fullyInitialize().setId(String.valueOf(id));
                            set.add(newVehicle);
                            return "Выполнено";
                        }
                    } else return "Нет элемента с таким id";
                } catch (NumberFormatException ignored) {
                    return "Неверный id";
                }
            }
            return "Неверные данные";
        }
    }
}
