package com.vin.spgrouptest.data;

import com.vin.spgrouptest.CommonConstants;

import java.util.HashMap;
import java.util.Map;

public class PsiMapReading{

    private Map<String, Double> psiReadingMap = new HashMap<>();

    public PsiMapReading(Map<String, Double> psiReadingMap){
        if(psiReadingMap == null){
            throw new IllegalArgumentException("Cannot Init with null psiReadingMap");
        }
        this.psiReadingMap.clear();
        this.psiReadingMap.putAll(psiReadingMap);
    }

    public double getPsi24Hourly(){
        return psiReadingMap.get(CommonConstants.PSI_24HR_KEY);
    }

    public double getO3SubIndex(){
        return psiReadingMap.get(CommonConstants.O3_INDEX_KEY);
    }

    public double getCoSubIndex(){
        return psiReadingMap.get(CommonConstants.CO_INDEX_KEY);
    }

    public double getSo2SubIndex(){
        return psiReadingMap.get(CommonConstants.SO2_INDEX_KEY);
    }

    public double getPm10SubIndex(){
        return psiReadingMap.get(CommonConstants.PM10_INDEX_KEY);
    }

    public double getPm25SubIndex(){
        return psiReadingMap.get(CommonConstants.PM25_INDEX_KEY);
    }

    public double getNo2OneHour(){
        return psiReadingMap.get(CommonConstants.NO2_1HR_KEY);
    }

    public double getO3EightHour(){
        return psiReadingMap.get(CommonConstants.O3_8HR_KEY);
    }

    public double getCoEightHour(){
        return psiReadingMap.get(CommonConstants.CO_8HR_KEY);
    }

    public double getSo2TwentyFourHour(){
        return psiReadingMap.get(CommonConstants.SO2_24HR_KEY);
    }

    public double getPm10TwentyFourHour(){
        return psiReadingMap.get(CommonConstants.PM10_24HR_KEY);
    }

    public double getPm25TwentyFourHour(){
        return psiReadingMap.get(CommonConstants.PM25_24HR_KEY);
    }

}
