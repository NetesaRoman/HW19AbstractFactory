package models.api;

/*
 *
 * @author Roman Netesa
 *
 */
public abstract class Cable {

    public Cable(double resistance, double voltage, double diameter){
        this.diameter = diameter;
        this.resistance = resistance;
        this.voltage = voltage;
    }

    private double resistance;
    private double voltage;
    private double diameter;

    public double getResistance() {
        return resistance;
    }

    public double getVoltage() {
        return voltage;
    }

    public double getDiameter() {
        return diameter;
    }
}
