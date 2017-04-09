package hu.oe.nik.szfmv17t.automatedcar;


import hu.oe.nik.szfmv17t.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv17t.automatedcar.powertrainsystem.PowertrainSystem;
import hu.oe.nik.szfmv17t.environment.domain.CollidableBase;
import hu.oe.nik.szfmv17t.environment.utils.Resizer;
import hu.oe.nik.szfmv17t.environment.utils.Vector2d;

public class AutomatedCar extends CollidableBase {

    private PowertrainSystem powertrainSystem;
    private Resizer resizer;

    public AutomatedCar(double positionX, double positionY, double width, double height, double axisAngle, int zIndex, String imageFilePath, double mass, double speed) {
        super(positionX, positionY, width, height, axisAngle, zIndex, imageFilePath, mass, speed, axisAngle + 1.5707963268d);


        // Compose our car from brand new system components
        // The car has to know its PowertrainSystem, to get its coordinates


        powertrainSystem = new PowertrainSystem(height, width, mass);
        resizer = Resizer.getResizer();
        // The rest of the components use the VirtualFunctionBus to communicate,
        // they do not communicate with the car itself

        // place a driver into the car for demonstrating the signal sending mechanism
        new Driver();
    }

    /*@Override
    public void setDirectionAngleInRadian(double angle) {
        this.position.setDirectionAngleInRadian(angle + 1.5707963268d);
    }*/

    public void drive() {
        // call components
        VirtualFunctionBus.loop();
        // Update the position and orientation of the car
        this.speed = this.powertrainSystem.getVelocity();
        if (this.speed != 0) {
            this.setDirectionAngle(calculateDirectionangle(powertrainSystem.getSteeringAngle()));
            if (this.speed > 0) {

                // Az irányszög és a tengellyel bezárt szög között 90 fokos eltérés van!!!
                this.setAxisAngle(this.getDirectionAngle() - 1.5707963268d);
            } else {
                this.setAxisAngle(-(this.getDirectionAngle() - 1.5707963268d));
            }
        }
        System.out.println("Speed: " + speed + "m/s");
    }

    @Override
    public void updateWorldObject() {
        Vector2d direction = new Vector2d(Math.cos(this.getDirectionAngle()), Math.sin(this.getDirectionAngle()));
        if (this.speed > 0) {
            position.setReferencePointX(position.getReferencePointX() + direction.getX() * getSpeed());
            position.setReferencePointY(position.getReferencePointY() - direction.getY() * getSpeed());
        }
        else {
            position.setReferencePointX(position.getReferencePointX() - direction.getX() * getSpeed());
            position.setReferencePointY(position.getReferencePointY() - direction.getY() * getSpeed());
        }
    }

    private double calculateDirectionangle(double directionAngle) {
        double currentAngle = getDirectionAngle() + directionAngle;
        if (currentAngle < 6.2831853072d)
            return currentAngle;
        else
            return 0.0d;
    }
}
