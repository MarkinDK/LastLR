package com.commands;

import com.vehicle.SetOfVehicles;

/**
 * Класс, возвращающий строковое представление коллекции
 */
public class showCommand extends Command {
    /**
     * Метод, возвращающий строковое представление коллекции
     * @param o Игнорируется
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Строковое представление коллекции
     */
    @Override
    public String execute(Object o, SetOfVehicles set) {
        return  set.toString();
    }
}
