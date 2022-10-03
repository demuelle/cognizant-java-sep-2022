package com.lg;

public class TV {

    private boolean powerOn;
    private int channel;

    public TV() {
        channel = 1;
        powerOn = false;
    }

    public void on(){
        powerOn = true;
    }

    public void off() {
        powerOn = false;
    }

    public void increaseChannel() {
        channel++;
    }
    public boolean isPowerOn() {
        return powerOn;
    }

    public void setPowerOn(boolean powerOn) {
        this.powerOn = powerOn;
    }


    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }
}
