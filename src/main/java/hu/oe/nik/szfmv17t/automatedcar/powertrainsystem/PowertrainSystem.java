package hu.oe.nik.szfmv17t.automatedcar.powertrainsystem;

import hu.oe.nik.szfmv17t.automatedcar.SystemComponent;
import hu.oe.nik.szfmv17t.automatedcar.bus.Signal;
import hu.oe.nik.szfmv17t.automatedcar.bus.SignalCarList;
import hu.oe.nik.szfmv17t.automatedcar.hmi.AutoGearStates;
import hu.oe.nik.szfmv17t.automatedcar.hmi.AutomaticParkingStates;
import hu.oe.nik.szfmv17t.automatedcar.hmi.DirectionIndicator;
import hu.oe.nik.szfmv17t.automatedcar.hmi.DirectionIndicatorStates;
import hu.oe.nik.szfmv17t.environment.interfaces.IWorldObject;
import hu.oe.nik.szfmv17t.environment.utils.InWay;
import static hu.oe.nik.szfmv17t.environment.utils.InWay.ChooseCars;
import hu.oe.nik.szfmv17t.physics.SpeedControl;
import hu.oe.nik.szfmv17t.physics.SteeringControl;
import java.awt.geom.Point2D;
import java.util.List;

public class PowertrainSystem extends SystemComponent {

    // signal id table for PowertrainSystem
    public static final int DEMO = 0;
    public static final int SMI_BrakePedal = 10;
    public static final int SMI_Gaspedal = 11;
    public static final int SMI_Gear = 12;
    public static final int SMI_SteeringWheel = 13;
    public static final int SMI_Indication = 14;
    public static final int Parking_State = 15;
    public static final int ULTRASONIC_SENSOR_ID = 16;
    public static final int RADAR_SENSOR_ID = 17;
    public static final int CAMERA_SENSOR_ID = 18;
    public static final int Modelling = 20;
    public static final int Physics = 30;
    public static final int Physics_Speed = 31;
    public static final int Physics_Gear = 32;
    public static final int Visualisation = 40;

    // physics
    private SpeedControl speedControl;
    private SteeringControl steeringControl;

    // Output signals
    // Only these are available trough getters
    private int wheelState = 0;

    public PowertrainSystem(double height, double width, double carWeight) {
        super();
        this.speedControl = new SpeedControl(carWeight);
        this.steeringControl = new SteeringControl();
    }

    @Override
    public void loop() {
        // TODO write this
    }

    @Override
    public void receiveSignal(Signal s) {

        switch (s.getId()) {
            // Handle demo signal
            case SMI_BrakePedal:
                int brakePedal = (int) s.getData();
                this.speedControl.setBrakePedal(brakePedal);
                break;

            case SMI_Gaspedal:
                int gasPedal = (int) s.getData();
                this.speedControl.setGasPedal(gasPedal);
                break;

            case SMI_Gear:
                AutoGearStates gear = AutoGearStates.values()[(int) s.getData()];
                this.speedControl.setAutoGearState(gear);
                break;

            case SMI_SteeringWheel:
                this.wheelState = (int) s.getData();
                break;

            case SMI_Indication:
                DirectionIndicatorStates indicator = DirectionIndicatorStates.values()[(int) s.getData()];
                break;

            case Parking_State:
                AutomaticParkingStates parkingState = AutomaticParkingStates.values()[(int) s.getData()];
                break;

            case ULTRASONIC_SENSOR_ID:
                // System.out.println("Ultrasonic sensor: " + s.getData());
                break;

            case CAMERA_SENSOR_ID:
                break;
            default:
                if (s instanceof SignalCarList) {

                    SignalCarList signal = (SignalCarList) s;
                    Point2D.Double carCenter=new Point2D.Double(signal.getCar().getCenterX(),signal.getCar().getCenterY());
                    List<IWorldObject> cars= ChooseCars(signal.getCarList());
                    IWorldObject obj= InWay.ChooseClosest(carCenter, cars);
                    System.out.println("Ultrasonic sensor: " + signal.getData() + " x:" + obj.getCenterX()+" y:"+obj.getCenterY()+" "+signal.getCar().getImageName());
                }
        }
    }

    public double getSteeringAngle(double carVelocity) {
        return steeringControl.calculateAngle(carVelocity, this.wheelState);
    }

    public double getVelocity() {
        return speedControl.calculateVelocity();
    }

}
