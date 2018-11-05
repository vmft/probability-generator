package ru.psuti.courseproject.core.generator;

import ru.psuti.courseproject.core.pojo.CalculatedDataObject;

import java.util.*;

public class ExponentialDistributionGenerator implements Generator {
   // private ExponentialDistribution exponentialDistribution;
    private List<Double> generatedValues;
    private List<CalculatedDataObject> calculatedDataObjects;
    public static final double LAMBDA=1;
    private static final int CAPACITY=20;
    public ExponentialDistributionGenerator() {
       // exponentialDistribution = new ExponentialDistribution(0.6);
    }

    @Override
    public List<Double> getGeneratedRandomValues(int sampleSize) {

        Random random =new Random();
        double log;
        generatedValues = new ArrayList<>(sampleSize);
        for (int i = 0; i < sampleSize; i++) {
            log=Math.log(1-random.nextDouble())/(-LAMBDA);
            generatedValues.add(log);
            //generatedValues.add(exponentialDistribution.sample());
        }
        generatedValues.sort(Double::compareTo);
        return generatedValues;
    }

    @Override
    public List<CalculatedDataObject> getCalculatedData() {
        calculatedDataObjects=new ArrayList<>(10);
        double mathExpectation=getStatMoment(1);
        double mathExpectationStat=1/LAMBDA;
        double m2Stat=1/Math.pow(LAMBDA,2);
        double m3Stat=2/Math.pow(LAMBDA,3);
        double m4Stat=9/Math.pow(LAMBDA,4);
        double m2= getMoment(2);
        double m3= getMoment(3);
        double m4= getMoment(4);
        double sigma=Math.sqrt(m2);
        double as=m3/Math.pow(sigma,3);
        double ek=(m4/Math.pow(sigma,4))-3;
        calculatedDataObjects.add(new CalculatedDataObject("a1",mathExpectationStat,mathExpectation));
        calculatedDataObjects.add(new CalculatedDataObject("m2 ",m2Stat,m2));
        calculatedDataObjects.add(new CalculatedDataObject("m3 ",m3Stat,m3));
        calculatedDataObjects.add(new CalculatedDataObject("m4 ",m4Stat,m4));
        calculatedDataObjects.add(new CalculatedDataObject("as ",2.0,as));
        calculatedDataObjects.add(new CalculatedDataObject("ek ",6.0,ek));
        calculatedDataObjects.add(new CalculatedDataObject("chi-2",null,getChi2(getP())));
        return calculatedDataObjects;
    }
    private double getMoment(int order){
        double res=0.0;
        double mean=mean();
        for (Double d: generatedValues) {
            res+=Math.pow(d-mean,order);
        }
        res/=generatedValues.size();
        return res;
    }
    private List<Double> getP() {
        List<Double> p = new ArrayList<>(CAPACITY);
        double boundInc = (generatedValues.get(generatedValues.size() - 1) - generatedValues.get(0)) / CAPACITY;
        double lowerBound = generatedValues.get(0);
        double upperBound = lowerBound + boundInc;
        int count = 0;
        for (int i = 0; i < CAPACITY; i++) {
            p.add(Math.pow((Math.E), -(LAMBDA * lowerBound)) - Math.pow((Math.E), -(LAMBDA * upperBound)));
            lowerBound += boundInc;
            upperBound += boundInc;
        }
        return p;
    }
    private double getChi2(List<Double> p) {
        double chi2 = 0.0;
        double boundInc = generatedValues.get(0) + generatedValues.get(generatedValues.size() - 1) / 20;
        double lowerBound = generatedValues.get(0);
        double upperBound = lowerBound + boundInc;
        int index = 0;
        int count = 0;
        double n = generatedValues.size();
        for (double d : generatedValues) {
            if (d >= lowerBound && d < upperBound) {
                    count++;
            } else {
                chi2 += (Math.pow(count - n * p.get(index), 2) / (n * p.get(index)));
                count=0;
                do  {
                    lowerBound += boundInc;
                    upperBound += boundInc;
                    index++;
                    if (d >= lowerBound && d < upperBound) {
                        count++;
                    }
                }while (!(d >= lowerBound && d < upperBound));
             //   chi2 += (Math.pow(count - n * p.get(index), 2) / (n * p.get(index)));
            }
        }
        return chi2;
    }
private double mean(){
        double res=0.0;
    for (Double d :
            generatedValues) {
        res+=d;
    }
    res/=generatedValues.size();
        return res;
}
    private double getStatMoment(int order) {
        double moment = 0.0;
        int generatedValuesCount = generatedValues.size();
        for (double d : generatedValues) {
            moment += Math.pow(d, order);
        }

        moment /= generatedValuesCount;

        return moment;
    }
}
