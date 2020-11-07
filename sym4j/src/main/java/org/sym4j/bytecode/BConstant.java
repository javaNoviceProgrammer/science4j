package org.sym4j.bytecode;

public class BConstant implements BytecodeFunc {
	double value;
	public BConstant(double v) {
		this.value = v;
	}

	@Override
	public double apply(double... args) {
		return value;
	}

}
