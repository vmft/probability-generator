package ru.psuti.courseproject.core.generator;

import ru.psuti.courseproject.core.pojo.CalculatedDataObject;

import java.util.*;

public class NormalDistributionGenerator implements Generator {

    private List<Double> generatedRandomValues;
    private List<CalculatedDataObject> calculatedData;
    private Random random = new Random();

    public NormalDistributionGenerator() {
        generatedRandomValues = new ArrayList<>();
        calculatedData = new ArrayList<>();
    }

    @Override
    public List<Double> getGeneratedRandomValues(int sampleSize) {
        for (int i = 0; i < sampleSize; i++) {
            generatedRandomValues.add(random.nextGaussian());
        }
        generatedRandomValues.sort(Double::compareTo);
        System.out.println(generatedRandomValues.toString());
        return generatedRandomValues;
    }

    @Override
    public List<CalculatedDataObject> getCalculatedData() {
        return calculatedData;
    }
}
