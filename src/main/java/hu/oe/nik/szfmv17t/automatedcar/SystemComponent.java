package hu.oe.nik.szfmv17t.automatedcar;

import hu.oe.nik.szfmv17t.automatedcar.bus.VirtualFunctionBus;

/**
 *
 * This class represents common features for system components
 * By extending this class, the component is registered towards
 * the virtual function bus automatically on instantiation.
 *
 * Students must not modify this class!
 *
 */

public abstract class SystemComponent implements ISystemComponent {
	// Register components automatically during instantiation
	protected SystemComponent() {
		VirtualFunctionBus.registerComponent(this);
	}
}