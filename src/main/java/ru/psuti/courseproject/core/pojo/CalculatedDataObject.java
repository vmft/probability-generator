package ru.psuti.courseproject.core.pojo;

public class CalculatedDataObject {
    private String paramName; // Имя параметра
    private double paramValueTheo; // Теоретическое значение
    private double paramValueStat; // Статистическое значение

    public CalculatedDataObject(String paramName, double paramValueTheo, double paramValueStat) {
        this.paramName = paramName;
        this.paramValueTheo = paramValueTheo;
        this.paramValueStat = paramValueStat;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public double getParamValueTheo() {
        return paramValueTheo;
    }

    public void setParamValueTheo(double paramValueTheo) {
        this.paramValueTheo = paramValueTheo;
    }

    public double getParamValueStat() {
        return paramValueStat;
    }

    public void setParamValueStat(double paramValueStat) {
        this.paramValueStat = paramValueStat;
    }
}
