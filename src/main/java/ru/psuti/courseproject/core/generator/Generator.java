package ru.psuti.courseproject.core.generator;

import ru.psuti.courseproject.core.pojo.CalculatedDataObject;

import java.util.List;

public interface Generator {
    /**
     * Метод для генерации заданного количества случайных величин.
     * @param sampleSize количество случайных величин.
     * @return список случайных величин.
     */
    List<Double> getGeneratedRandomValues(int sampleSize);

    /**
     * Метод для генерации рассчетных значений. В список помещаются объекты CalculatedDataObject,
     * которые хранят название параметра, теоретическое значение и статистическое значение.
     * @return список рассчетных значений.
     */
    List<CalculatedDataObject> getCalculatedData();
}
