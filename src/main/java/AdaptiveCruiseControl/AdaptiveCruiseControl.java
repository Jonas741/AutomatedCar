/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdaptiveCruiseControl;

import hu.oe.nik.szfmv17t.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv17t.automatedcar.SystemComponent;
import hu.oe.nik.szfmv17t.automatedcar.bus.Signal;
import hu.oe.nik.szfmv17t.automatedcar.bus.SignalCarList;
import hu.oe.nik.szfmv17t.environment.interfaces.IWorldObject;
import hu.oe.nik.szfmv17t.environment.utils.InWay;
import static hu.oe.nik.szfmv17t.environment.utils.InWay.ChooseCars;
import static hu.oe.nik.szfmv17t.environment.utils.InWay.InWaySortCarFront;
import java.awt.geom.Point2D;
import java.util.List;

/**
 *
 * @author Gellert Babel <OE-NIK>
 */
public class AdaptiveCruiseControl extends SystemComponent{

    public Boolean activated;
    public AutomatedCar car;
    
    @Override
    public void loop() {
        
    }

    @Override
    public void receiveSignal(Signal s) {
        if (activated && s != null && s instanceof SignalCarList) {
            try{
                    SignalCarList signal = (SignalCarList) s;
                    List<IWorldObject> cars= ChooseCars(signal.getCarList());
                    if(cars==null || s.getData().doubleValue()==0 || signal.getCarList().isEmpty()){
                        return;
                    }
                    Point2D.Double carCenter=new Point2D.Double(signal.getCar().getCenterX(),signal.getCar().getCenterY());

                    car=(AutomatedCar) signal.getCar();
                    cars= InWaySortCarFront(car,cars);
                    IWorldObject obj= InWay.ChooseClosest(carCenter, cars);
                    System.out.println("Ultrasonic sensor: " + signal.getData() + " x:" + obj.getCenterX()+" y:"+obj.getCenterY()+" "+signal.getCar().getImageName());
            }
            catch(Exception e){
                
            }
                }
    }
    
}
