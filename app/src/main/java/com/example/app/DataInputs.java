package com.example.app;

public class DataInputs
{
    private float oilTemperature;
    private float oilPressure;
    private float fuelPressure;
    private float coolantTemperature;
    private float engineRPM;
    private int gear;

    public DataInputs(float oilTemperature, float oilPressure, float fuelPressure, float coolantTemperature, float engineRPM, int gear)
    {
        this.oilPressure=oilPressure;
        this.oilTemperature=oilTemperature;
        this.fuelPressure=fuelPressure;
        this.coolantTemperature=coolantTemperature;
        this.engineRPM=engineRPM;
        this.gear=gear;

    }

    public float getOilTemperature()
    {
        return oilTemperature;
    }

    public void setOilTemperature(float oilTemperature)
    {
        this.oilTemperature = oilTemperature;
    }

    public float getOilPressure()
    {
        return oilPressure;
    }

    public void setOilPressure(float oilPressure)
    {
        this.oilPressure = oilPressure;
    }

    public float getFuelPressure()
    {
        return fuelPressure;
    }

    public void setFuelPressure(float fuelPressure)
    {
        this.fuelPressure = fuelPressure;
    }

    public float getCoolantTemperature()
    {
        return coolantTemperature;
    }

    public void setCoolantTemperature(float coolantTemperature)
    {
        this.coolantTemperature = coolantTemperature;
    }

    public float getEngineRPM()
    {
        return engineRPM;
    }

    public void setEngineRPM(float engineRPM)
    {
        this.engineRPM = engineRPM;
    }

    public int getGear()
    {
        return gear;
    }

    public void setGear(int gear)
    {
        this.gear = gear;
    }


}
