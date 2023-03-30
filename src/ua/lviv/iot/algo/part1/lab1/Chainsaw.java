package ua.lviv.iot.algo.part1.lab1;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
abstract class Saw {
    protected String brand;
    protected double power;
    protected double engineWorkDuration; // тривалість роботи двигуна в годинах

    protected boolean isWorking = false; // прапорець, який вказує про стан пилки, по замовчуванню - false

    public Saw(String brand, double power, double engineWorkDuration) {
    }

    public abstract double getRemainingWorkTime();
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
class Chainsaw extends Saw {
    private double fuelTankCapacity;
    private double fuelLevel;

    public Chainsaw(String brand, double power, double engineWorkDuration, double fuelTankCapacity, double fuelLevel) {
        super(brand, power, engineWorkDuration);
        this.fuelTankCapacity = fuelTankCapacity;
        this.fuelLevel = fuelLevel;
    }

    public void start() {
        isWorking = true;
    }

    public void stop() {
        isWorking = false;
    }

    public void cutWood(double length) {
        double fuelNeeded = length / 10; // витрачається 100 мл на 1 м
        if (fuelNeeded > fuelLevel) {
            fuelLevel = 0;
            isWorking = false;
        } else {
            fuelLevel -= fuelNeeded;
        }
    }

    @Override
    public double getRemainingWorkTime() {
        return fuelLevel / (fuelTankCapacity / engineWorkDuration);
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
class ElectricSaw extends Saw {
    private String motorType;
    private double batteryCapacity;

    public ElectricSaw(String brand, double power, double engineWorkDuration, String motorType, double batteryCapacity) {
        super(brand, power, engineWorkDuration);
        this.motorType = motorType;
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public double getRemainingWorkTime() {
        return batteryCapacity / (power / engineWorkDuration);
    }
}

class SawManager {
    public static void main(String[] args) {
        Saw[] saws = new Saw[3];
        saws[0] = new Chainsaw("Stihl", 2000, 2, 3, 1.5);
        saws[1] = new ElectricSaw("Makita", 1800, 1.5, "brushless", 1000);
        saws[2] = new Chainsaw("Husqvarna", 2200, 3, 4, 3);

        for (Saw saw : saws) {
            System.out.println(saw.toString());
            System.out.println("Remaining work time: " + saw.getRemainingWorkTime() + " hours");
        }
    }
}
