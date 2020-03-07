package com.commands;

import com.Main;
import com.vehicle.*;

/**
 * Класс, объект которого осуществляет добавление нового элемента в коллекцию, если его
 * значение превышает значение наибольшего элемента в коллекции
 */
public class add_if_maxCommand extends Command {
    /**
     * Осуществляет добавление нового элемента в коллекцию, если его
     * значение превышает значение наибольшего элемента в коллекции
     * @param o Игнорируется
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Сообщение об успешности выполнения команды
     */
    @Override
    @Creative
    public String execute(Object o, SetOfVehicles set) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace[2].getMethodName().equals("main")) {
            Vehicle v = Main.createNewVehicle();
            if (set.add_if_max(v.fullyInitialize())) {
                return "Выполнено";
            }
            return "Неудача";
        } else {
            Vehicle newVehicle = execute_scriptCommand.createVehicleFromFile();
            if (newVehicle != null) {
                if (set.add_if_max(newVehicle.fullyInitialize())) {
                    return "Выполнено";
                }
                return "Неудача";
            }
            return "Неудача";
        }
    }
}
