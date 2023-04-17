package ua.lviv.iot.algo.part1.lab2;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    protected String getHeaders() {
        return null;
    }

    public String toCSV() {
        return null;
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
class Chainsaw extends Saw {
    private double fuelTankCapacity;
    private double fuelLevel;

    public Chainsaw(String brand, double power, double engineWorkDuration) {
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

    @Override
    public String getHeaders() {
        return super.getHeaders() + ",fuelTankCapacity,fuelLevel";
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + fuelTankCapacity + "," + fuelLevel;
    }

    public char getName() {
        return 0;
    }

    public double getBarLength() {
        return 0;
    }

    public double getWeight() {
        return 0;
    }
}


class ChainsawWriter {

    private List<Chainsaw> chainsaws;
    private String manufacturer;

    public ChainsawWriter(List<Chainsaw> chainsaws, String acme) {

    }

    // Конструктори, геттери, сеттери і т.д.

    public void writeModelsToFile() {

        char CSV_SEPARATOR = 0;

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("chainsaw_models.csv"));

            // Записуємо заголовок у файл
            fileWriter.append("Model");
            fileWriter.append(CSV_SEPARATOR);
            fileWriter.append("Bar Length");
            fileWriter.append(CSV_SEPARATOR);
            fileWriter.append("Weight");
            fileWriter.append(CSV_SEPARATOR);

            // Записуємо кожен ланцюговий пил у файл
            for (Chainsaw model : chainsaws) {
                fileWriter.append(model.getName());
                fileWriter.append(CSV_SEPARATOR);
                fileWriter.append(Double.toString(model.getBarLength()));
                fileWriter.append(CSV_SEPARATOR);
                fileWriter.append(Double.toString(model.getWeight()));
                fileWriter.append(CSV_SEPARATOR);
            }

            System.out.println("Дані про ланцюгові пили були успішно записані у файл.");
        } catch (IOException e) {
            System.err.println("Сталась помилка при записі даних про ланцюгові пили у файл: " + e.getMessage());
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.err.println("Сталась помилка при закритті файлу: " + e.getMessage());
            }

        }
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
        List<T> collect = sawList.stream().filter(s -> s.getPower() > power).collect(Collectors.toList());
        return collect;
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
        List<Chainsaw> collect = sawList.stream().filter(s -> s.getBrand().equals(brand)).collect(Collectors.toList());
        return collect;
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

    public List<ElectricSaw> getSawList() {
        return null;
    }
}
public class Main {
    public static void main(String[] args) {
        List<ElectricSaw> allElectricSaws = new ArrayList<ElectricSaw>();
        ChainsawManager chainsawManager = new ChainsawManager();
        chainsawManager.addSaw(new Chainsaw("Stihl", 2000, 500));
        chainsawManager.addSaw(new Chainsaw("Husqvarna", 1800, 400));
        chainsawManager.addSaw(new Chainsaw("Makita", 2200, 550));
        chainsawManager.addSaw(new Chainsaw("Echo", 1600, 350));
        chainsawManager.addSaw(new Chainsaw("Black & Decker", 1200, 250));
        for (ElectricSaw electricSaw : allElectricSaws) {
            ElectricSawManager electricSawManager = new ElectricSawManager();
            electricSawManager.addSaw(new ElectricSaw("Bosch", 2000, 600, "Brushless", 1000));
            electricSawManager.addSaw(new ElectricSaw("Dewalt", 1800, 550, "Brushless", 800));
            electricSawManager.addSaw(new ElectricSaw("Makita", 2200, 700, "Brushed", 1200));


            System.out.println("All Chainsaws:");
            List<Chainsaw> allChainsaws = (List<Chainsaw>) chainsawManager.getSawList();
            for (Chainsaw saw : allChainsaws) {
                System.out.println(saw.toString());
            }


            System.out.println("\nAll Chainsaws with power greater than 1800:");
            List<Chainsaw> chainsawsWithPowerGreaterThan1800 = chainsawManager.findAllWithPowerGreaterThan(1800);
            for (Chainsaw saw : chainsawsWithPowerGreaterThan1800) {
                System.out.println(saw.toString());
            }


            System.out.println("\nAll Electric Saws:");
            allElectricSaws = electricSawManager.getSawList();
            for (ElectricSaw saw : allElectricSaws) System.out.println(saw.toString());


            System.out.println("\nAll Electric Saws with remaining work time greater than 2 hours:");
            List<ElectricSaw> electricSawsWithRemainingWorkTimeGreaterThan2 = electricSawManager.findAllWithRemainingWorkTimeGreaterThan(2);
            for (ElectricSaw saw : electricSawsWithRemainingWorkTimeGreaterThan2) {
                System.out.println(saw.toString());
            }
        }
    }
}

//good morning