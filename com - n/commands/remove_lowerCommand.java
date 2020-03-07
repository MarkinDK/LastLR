package com.commands;

import com.Main;
import com.vehicle.SetOfVehicles;
import com.vehicle.Vehicle;

/**
 * Класс, объект которого удаляет из коллекции все элементы, меньше заданного
 */
public class remove_lowerCommand extends Command {
    /**
     * Удаление из коллекции все элементы, меньше заданного
     *
     * @param o   Игнорируется
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Результат выполнения команды
     */
    @Override
    @Creative
    public String execute(Object o, SetOfVehicles set) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace[2].getMethodName().equals("main")) {
            Vehicle v = Main.createNewVehicle();
            if (set.remove_lower(v.fullyInitialize()))
                return "Элементы удалены";
            return "Превышающих элементов не найдено";
        } else {
            Vehicle newVehicle = execute_scriptCommand.createVehicleFromFile();
            if (newVehicle != null)
                if (set.remove_lower(newVehicle.fullyInitialize()))
                    return "Элементы удалены";
                else
                    return "Превышающих элементов не найдено";
            return "Неудача";
        }
    }
}
