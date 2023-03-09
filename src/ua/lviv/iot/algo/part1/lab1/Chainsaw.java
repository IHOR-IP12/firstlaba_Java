package ua.lviv.iot.algo.part1.lab1;

import com.sun.net.httpserver.Filter;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Chainsaw {
    private String brand;
    private double power;
    private double fuelTankCapacity;
    private double fuelLevel;
    private boolean isWorking;


    public static Chainsaw getInstance() {
        if (instance == null) {
            instance = new Chainsaw();
        }
        return instance;
    }

    private static Chainsaw instance;

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

    public static void main(String[] args) {
        Chainsaw[] chainsaws = new Chainsaw[4];
        chainsaws[0] = new Chainsaw(); // конструктор за замовчуванням
        chainsaws[1] = new Chainsaw("Stihl", 2000, 3, 2, true); // конструктор з параметрами

        chainsaws[2] = Chainsaw.getInstance(); // перший виклик getInstance()
        chainsaws[3] = Chainsaw.getInstance(); // другий виклик getInstance()


        for(int i = 0; i < chainsaws.length; i++) {


            System.out.println(chainsaws[i].toString());
        }
    }

}
