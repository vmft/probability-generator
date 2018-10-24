package ru.psuti.courseproject.core.generator;

import ru.psuti.courseproject.core.pojo.CalculatedDataObject;

import java.util.*;

public class NormalDistributionGenerator implements Generator {

    private List<Double> generatedRandomValues;  // Список сгенерированных случайных величин
    private List<CalculatedDataObject> calculatedData;  // Список объектов рассчитанных величин
    private Random random = new Random();

    public NormalDistributionGenerator() {
        generatedRandomValues = new ArrayList<>();
        calculatedData = new ArrayList<>();
    }

    @Override
    public List<Double> getGeneratedRandomValues(int sampleSize) {
        /* Хак для нечетных размеров выборки. Добавляется одно дополнительное значение, т.к. в цикле добавление попарное. */
        if (sampleSize % 2 != 0) {
            generatedRandomValues.add(getPairRandomValues(0, 1).get(0));
        }
        for (int i = 0; i < sampleSize / 2; i++) {
            //generatedRandomValues.add(random.nextGaussian());
            generatedRandomValues.addAll(getPairRandomValues(0, 1));
        }
        generatedRandomValues.sort(Double::compareTo);
        //System.out.println(generatedRandomValues.toString());
        return generatedRandomValues;
    }

    @Override
    public List<CalculatedDataObject> getCalculatedData() {
        return calculatedData;
    }

    /**
     * Метод для получения пары случайных значений по методу преобразования Бокса-Мюллера.
     * @param Mx математическое ожидание (стандартно 0).
     * @param sigma дисперсия (стандартно 1).
     * @return массив из двух элементов.
     */
    private ArrayList<Double> getPairRandomValues(double Mx, double sigma) {
        double firstRandomValue, secondRandomValue, sum;
        ArrayList<Double> resultRandomValues = new ArrayList<>();

        do {
            firstRandomValue = random.nextDouble() * 2 - 1; // Случайное число, равномерное распределение, [-1; 1]
            secondRandomValue = random.nextDouble() * 2 - 1;
            sum = Math.pow(firstRandomValue, 2) + Math.pow(secondRandomValue, 2);
        } while (sum >= 1 || sum == 0);  // Должно выполняться условие 0 < firstRandomValue^2 + secondRandomValue^2 <= 1

        /* Преобразование Бокса-Мюллера */
        resultRandomValues.add(firstRandomValue * Math.sqrt(-2 * Math.log(sum) / sum));
        resultRandomValues.add(secondRandomValue * Math.sqrt(-2 * Math.log(sum) / sum));

        return resultRandomValues;
    }
}