/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.oe.nik.szfmv17t.automatedcar.bus;

import hu.oe.nik.szfmv17t.environment.interfaces.IWorldObject;
import java.util.List;

/**
 *
 * @author Gellert Babel <OE-NIK>
 */
public class SignalCarList extends Signal {
    
    private List<IWorldObject> CarList;
    private IWorldObject car;

    public IWorldObject getCar() {
        return car;
    }

    public List<IWorldObject> getCarList() {
        return CarList;
    }
    public SignalCarList(int id, Number data,IWorldObject Car,List<IWorldObject> CarList) {
        super(id, data);
        this.CarList=CarList;
        this.car=Car;
    }
    
}
