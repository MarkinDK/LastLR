package com.commands;

import com.Main;
import com.vehicle.SetOfVehicles;
import com.vehicle.Vehicle;

/**
 * Класс, объект которого осуществляет добавление элемента в коллекцию
 */
public class addCommand extends Command {

    /**
     * Осуществляет добавление нового элемента в коллекцию
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
            if (set.add(v.fullyInitialize())) {
                return "Выполнено";
            }
            return "Неудача";
        } else {
            Vehicle newVehicle = execute_scriptCommand.createVehicleFromFile();
            if (newVehicle != null) {
                newVehicle.fullyInitialize();
                set.add(newVehicle);
                return "Выполнено";
            }
            return "Неудача";
        }
    }
}
