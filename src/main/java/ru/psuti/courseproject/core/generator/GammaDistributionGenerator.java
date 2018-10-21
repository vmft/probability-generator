package ru.psuti.courseproject.core.generator;

import org.apache.commons.math3.distribution.GammaDistribution;
import ru.psuti.courseproject.core.pojo.CalculatedDataObject;

import java.util.*;

public class GammaDistributionGenerator implements Generator {
    private static final double SHAPE = 2.0; // Форма (k)
    private static final double SCALE = 0.5; // Размер (theta)
    private static final double SIGMA = Math.sqrt(SHAPE * Math.pow(SCALE, 2));
    private List<Double> generatedRandomValues; // Список сгенерированных случайных величин
    private List<CalculatedDataObject> calculatedData; // Список объектов рассчитанных величин
    private GammaDistribution gammaDistribution; // Закон распределения

    public GammaDistributionGenerator() {
        generatedRandomValues = new ArrayList<>();
        calculatedData = new ArrayList<>();
        gammaDistribution = new GammaDistribution(SHAPE, SCALE);
    }

    public List<Double> getGeneratedRandomValues(int sampleSize) {
        for (int i = 0; i < sampleSize; i++) {
            generatedRandomValues.add(Double.valueOf(
                    String.format(Locale.US, "%.3f", gammaDistribution.sample())
            ));
        }
        generatedRandomValues.sort(Double::compareTo);
        return generatedRandomValues;
    }


    public List<CalculatedDataObject> getCalculatedData() {
        int generatedValuesCount = generatedRandomValues.size(); // Количество сгенерированных случайных величин

        double a1Theo = SHAPE * SCALE; // Математическое ожидание (a1)
        double a1Stat = getStatMoment(1); // Сумма частного случайной величины к общему количеству случайных величин. (a1)


        double m2Theo = SHAPE * Math.pow(SCALE, 2);
        double m2Stat = getStatCentralMoment(2);

        double m3Theo = 0.0;
        double m3Stat = getStatCentralMoment(3);

        double m4Theo = 0.0;
        double m4Stat = getStatCentralMoment(4);

        double AsTheo = 2.0 / Math.sqrt(SHAPE);
        double AsStat = m3Stat / Math.pow(SIGMA, 3);

        double EkTheo = 6.0 / SHAPE;
        double EkStat = (m4Stat / Math.pow(SIGMA, 4)) - 3;

        double chi2 = getChi2();

        calculatedData.add(new CalculatedDataObject("a1", a1Theo, a1Stat));
        calculatedData.add(new CalculatedDataObject("m2", m2Theo, m2Stat));
        calculatedData.add(new CalculatedDataObject("m3", m3Theo, m3Stat));
        calculatedData.add(new CalculatedDataObject("m4", m4Theo, m4Stat));
        calculatedData.add(new CalculatedDataObject("As", AsTheo, AsStat));
        calculatedData.add(new CalculatedDataObject("Ek", EkTheo, EkStat));
        calculatedData.add(new CalculatedDataObject("chi2", chi2, 0.0));

        return calculatedData;
    }

    private double getStatMoment(int order) {
        double moment = 0.0;
        int generatedValuesCount = generatedRandomValues.size();

        for (double d : generatedRandomValues) {
            moment += Math.pow(d, order);
        }
        moment /= generatedValuesCount;

        return moment;
    }

    private double getStatCentralMoment(int order) {
        double centralMoment = 0.0;
        int generatedValuesCount = generatedRandomValues.size();

        for (double d : generatedRandomValues) {
            centralMoment += Math.pow(d - (SHAPE * SCALE), order);
        }
        centralMoment /= generatedValuesCount;

        return centralMoment;
    }

    private double getChi2() {
        double chi2 = 0.0;
        return chi2;
    }
}
