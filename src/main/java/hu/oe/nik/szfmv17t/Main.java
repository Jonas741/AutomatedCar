package hu.oe.nik.szfmv17t;

import hu.oe.nik.szfmv17t.environment.domain.Car;
import hu.oe.nik.szfmv17t.environment.utils.Vector2d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hu.oe.nik.szfmv17t.automatedcar.AutomatedCar;
import hu.oe.nik.szfmv17t.automatedcar.hmi.HMI;
import hu.oe.nik.szfmv17t.environment.domain.World;
import hu.oe.nik.szfmv17t.visualisation.CourseDisplay;
import hu.oe.nik.szfmv17t.visualisation.HmiJPanel;

import java.util.ArrayList;
import java.util.List;

public class Main {

	private static final Logger logger = LogManager.getLogger();
	public static final int CYCLE_PERIOD = 200;

	public static void main(String[] args) {
		CourseDisplay vis = new CourseDisplay();

		// create the world
		World w = new World("src/main/resources/test_world.xml");
		// create an automated car NEW signature

		AutomatedCar car = new AutomatedCar(480,800,108,240,0d,0,"car_1_white.png",200d,0d);
		List<Vector2d> testPath = new ArrayList<Vector2d>();
		testPath.add(new Vector2d(500,500));
		testPath.add(new Vector2d(1000,500));
		testPath.add(new Vector2d(1000,1000));
		testPath.add(new Vector2d(0, 1000));
		Car testCar = new Car(100,100,108,240,0d,0,"car_1_white.png",200d,15d, 1.5707963268d, testPath);

		// create an automated car

		//create HMI - Human machine interface
		HMI hmi = new HMI();
		HmiJPanel.setHmi(hmi);


		// add car to the world
		w.addObjectToWorld(car);
		w.addObjectToWorld(testCar);

		// init visualisation module with the world
		vis.init(w);
		Thread drawThread=new Thread(vis);
		drawThread.start();
		while(true) {
			try {
				car.drive();
				hmi.setCarspeed(car.getSpeed());
				//vis.refreshFrame();
				w.updateWorld();
				Thread.sleep(CYCLE_PERIOD);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
}