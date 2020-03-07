package com.commands;

import com.Main;
import com.vehicle.SetOfVehicles;
import com.vehicle.Vehicle;

/**
 * Класс, объект которого удаляет из коллекции все элементы, превышающие заданный
 */
public class remove_greaterCommand extends Command {

    @Override
    @Creative
    public String execute(Object o, SetOfVehicles set) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace[2].getMethodName().equals("main")) {
            Vehicle v = Main.createNewVehicle();
            if (set.remove_greater(v.fullyInitialize()))
                return "Элементы удалены";
            return "Превышающих элементов не найдено";
        } else {
            Vehicle newVehicle = execute_scriptCommand.createVehicleFromFile();
            if (newVehicle != null)
                if (set.remove_greater(newVehicle.fullyInitialize()))
                    return "Элементы удалены";
                else
                    return "Превышающих элементов не найдено";
            return "Неудача";
        }
    }
}
