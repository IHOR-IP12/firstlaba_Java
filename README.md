# firstlaba_Java
Create a Chainsaw class that will have the following fields:
brand: chainsaw brand;
power: chainsaw power (in watts);
fuelTankCapacity: fuel tank capacity (in liters);
fuelLevel: current fuel level (in liters).
isWorking: a flag that indicates the state of the chainsaw, by default - false

The Chainsaw class must have the following public methods:
start(): A method that starts the chainsaw by setting the isWorking flag to true
stop() method that stops the chainsaw by setting the isWorking flag to false
cutWood(length: double): a method that cuts a tree of a given length (in meters) using a chainsaw
(at the same time, fuel is consumed at the rate of 100ml per 1m). If the fuel level becomes 0, then the value of the variable isWorking becomes false
