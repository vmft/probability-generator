package ru.psuti.courseproject.core.pojo;

public class CalculatedDataObject {
    private String paramName; // Имя параметра
    private Double paramValueTheo; // Теоретическое значение
    private Double paramValueStat; // Статистическое значение

    public CalculatedDataObject(String paramName, Double paramValueTheo, Double paramValueStat) {
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

    public Double getParamValueTheo() {
        return paramValueTheo;
    }

    public void setParamValueTheo(Double paramValueTheo) {
        this.paramValueTheo = paramValueTheo;
    }

    public Double getParamValueStat() {
        return paramValueStat;
    }

    public void setParamValueStat(Double paramValueStat) {
        this.paramValueStat = paramValueStat;
    }
}
