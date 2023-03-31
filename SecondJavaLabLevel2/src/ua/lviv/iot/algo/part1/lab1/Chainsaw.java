package ua.lviv.iot.algo.part1.lab1;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        fuelLevel = (fuelNeeded > fuelLevel) ? 0 : fuelLevel - fuelNeeded;
        isWorking = (fuelLevel == 0) ? false : true;
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

abstract class SawManager<T extends Saw> {
    protected List<T> sawList;

    public SawManager() {
        sawList = new ArrayList<>();
    }

    public void addSaw(T saw) {
        sawList.add(saw);
    }

    public List<T> findAllWithPowerGreaterThan(double power) {
        return sawList.stream().filter(s -> s.getPower() > power).collect(Collectors.toList());
    }

    public List<T> findAllChainsaws() {
        return sawList.stream().filter(s -> s instanceof Chainsaw).collect(Collectors.toList());
    }

    public abstract List<T> findAllWithBrand(String brand);

    public abstract List<T> findAllWithRemainingWorkTimeGreaterThan(double remainingWorkTime);
}

class ChainsawManager extends SawManager<Chainsaw> {

    @Override
    public List<Chainsaw> findAllWithBrand(String brand) {
        return sawList.stream().filter(s -> s.getBrand().equals(brand)).collect(Collectors.toList());
    }

    @Override
    public List<Chainsaw> findAllWithRemainingWorkTimeGreaterThan(double remainingWorkTime) {
        return sawList.stream().filter(s -> s.getRemainingWorkTime() > remainingWorkTime).collect(Collectors.toList());
    }

    public Iterable<? extends Object> getSawList() {
        return sawList;
    }
}


class ElectricSawManager extends SawManager<ElectricSaw> {

    @Override
    public List<ElectricSaw> findAllWithBrand(String brand) {
        return sawList.stream().filter(s -> s.getBrand().equals(brand)).collect(Collectors.toList());
    }

    @Override
    public List<ElectricSaw> findAllWithRemainingWorkTimeGreaterThan(double remainingWorkTime) {
        return sawList.stream().filter(s -> s.getRemainingWorkTime() > remainingWorkTime).collect(Collectors.toList());
    }

    public Iterable<Object> getSawList() {
        return null;
    }
}
class Main {
    public static void main(String[] args) {
        ChainsawManager chainsawManager = new ChainsawManager();
        chainsawManager.addSaw(new Chainsaw("Stihl", 2000, 500, 500, 250));
        chainsawManager.addSaw(new Chainsaw("Husqvarna", 1800, 400, 400, 150));
        chainsawManager.addSaw(new Chainsaw("Makita", 2200, 550, 600, 300));
        chainsawManager.addSaw(new Chainsaw("Echo", 1600, 350, 300, 100));
        chainsawManager.addSaw(new Chainsaw("Black & Decker", 1200, 250, 200, 50));

        ElectricSawManager electricSawManager = new ElectricSawManager();
        electricSawManager.addSaw(new ElectricSaw("Bosch", 2000, 600, "Brushless", 1000));
        electricSawManager.addSaw(new ElectricSaw("Dewalt", 1800, 550, "Brushless", 800));
        electricSawManager.addSaw(new ElectricSaw("Makita", 2200, 700, "Brushed", 1200));
        electricSawManager.addSaw(new ElectricSaw("Ryobi", 1500, 400, "Brushless", 600));
        electricSawManager.addSaw(new ElectricSaw("Milwaukee", 1700, 500, "Brushed", 900));

        System.out.println("All chainsaws:");
        for (Object o : chainsawManager.getSawList()) {
            System.out.println(o);
        }

        System.out.println("\nAll electric saws:");
        electricSawManager.getSawList().forEach(System.out::println);

        System.out.println("\nChainsaws with power greater than 1800W:");
        chainsawManager.findAllWithPowerGreaterThan(1800).forEach(System.out::println);

        System.out.println("\nElectric saws with remaining work time greater than 5 hours:");
        electricSawManager.findAllWithRemainingWorkTimeGreaterThan(5).forEach(System.out::println);

        System.out.println("\nChainsaws made by Makita:");
        chainsawManager.findAllWithBrand("Makita").forEach(System.out::println);

        System.out.println("\nElectric saws made by Bosch:");
        electricSawManager.findAllWithBrand("Bosch").forEach(System.out::println);
    }
}
