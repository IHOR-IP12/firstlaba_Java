package ua.lviv.iot.algo.part1.lab2;

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

    public Saw(String brand, double power) {
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

    public Chainsaw(String brand, double power) {
        this.fuelTankCapacity = fuelTankCapacity;
        this.fuelLevel = fuelLevel;
    }
    public Chainsaw(String brand, double power, double engineWorkDuration) {
        super(brand, power);
        this.engineWorkDuration = engineWorkDuration;
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

    public ElectricSaw(String brand, double power, double engineWorkDuration) {
        super(brand, power);
        this.motorType = motorType;
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public double getRemainingWorkTime() {
        return batteryCapacity / (power / engineWorkDuration);
    }
}

class SawManager<C extends Saw> {
    protected List<Saw> sawList;

    public SawManager() {
        sawList = new ArrayList<>();
    }

    public void addSaw(Saw saw) {
        sawList.add(saw);
    }

    public List<Saw> findAllWithPowerGreaterThan(double power) {
        List<Saw> collect = sawList.stream()
                .filter(s -> s.getPower() > power)
                .collect(Collectors.toList());
        return collect;
    }

    public List<Chainsaw> findAllChainsaws() {
        List<Chainsaw> chainsaws = new ArrayList<>();
        for (Saw saw : sawList) {
            if (saw instanceof Chainsaw) {
                chainsaws.add((Chainsaw) saw);
            }
        }
        return chainsaws;
    }

    public List<Saw> findAllWithBrand(String brand) {
        List<Saw> collect = sawList.stream()
                .filter(s -> s.getBrand().equals(brand))
                .collect(Collectors.toList());
        return collect;
    }

    public List<Saw> findAllWithRemainingWorkTimeGreaterThan(double remainingWorkTime) {
        List<Saw> collect = sawList.stream()
                .filter(s -> s.getRemainingWorkTime() > remainingWorkTime)
                .collect(Collectors.toList());
        return collect;
    }


    static class ChainsawManager extends SawManager<Chainsaw> {

        @Override
        public List<Saw> findAllWithBrand(String brand) {
            return sawList.stream()
                    .filter(s -> s.getBrand()
                            .equals(brand))
                    .collect(Collectors.toList());
        }

        @Override
        public List<Saw> findAllWithRemainingWorkTimeGreaterThan(double remainingWorkTime) {
            return sawList.stream()
                    .filter(s -> s.getRemainingWorkTime() > remainingWorkTime)
                    .collect(Collectors.toList());
        }

        public Iterable<? extends Object> getSawList() {
            return sawList;
        }
    }


    public class Main {
        public void main(String[] args) {
            List<ElectricSaw> allElectricSaws = new ArrayList<ElectricSaw>();
            ChainsawManager chainsawManager = new ChainsawManager();
            chainsawManager.addSaw(new Chainsaw("Stihl", 2000));
            chainsawManager.addSaw(new Chainsaw("Husqvarna", 1800));
            chainsawManager.addSaw(new Chainsaw("Makita", 2200));
            chainsawManager.addSaw(new Chainsaw("Echo", 1600));
            chainsawManager.addSaw(new Chainsaw("Black & Decker", 1200));
            for (ElectricSaw electricSaw : allElectricSaws) {
                ElectricSawManager electricSawManager = new ElectricSawManager();
                electricSawManager.addSaw(new ElectricSaw("Bosch", 2000, 600));
                electricSawManager.addSaw(new ElectricSaw("Dewalt", 1800, 550));
                electricSawManager.addSaw(new ElectricSaw("Makita", 2200, 700));


                System.out.println("All Chainsaws:");
                List<Chainsaw> allChainsaws = (List<Chainsaw>) chainsawManager.getSawList();
                for (Chainsaw saw : allChainsaws) {
                    System.out.println(saw.toString());
                }


                System.out.println("\nAll Chainsaws with power greater than 1800:");
                List<Saw> chainsawsWithPowerGreaterThan1800 = chainsawManager.findAllWithPowerGreaterThan(1800);
                for (Saw saw : chainsawsWithPowerGreaterThan1800) {
                    System.out.println(saw.toString());
                }


                System.out.println("\nAll Electric Saws:");
                allElectricSaws = electricSawManager.getSawList();
                for (ElectricSaw saw : allElectricSaws) System.out.println(saw.toString());

            }
        }
    }
}