package com.commands;

import com.vehicle.SetOfVehicles;

/**
 * Класс, объект которого очищает колекцию
 */
public class clearCommand extends Command {
    /**
     * Выводит уникальные значения поля type, оба параметра игнорируются
     * @param o Игнорируется
     * @param set Коллекция объектов типа com.vehicle.Vehicle
     * @return Строка "Выполнено"
     */
    @Override
    public String execute(Object o, SetOfVehicles set) {
        set.clear();
        return "Выполнено";
    }
}
