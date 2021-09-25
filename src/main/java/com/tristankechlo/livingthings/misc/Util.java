package com.tristankechlo.livingthings.misc;

public class Util {

	public static byte clamp(byte value, byte min, byte max) {
		if (value < min) {
			return min;
		} else {
			return value > max ? max : value;
		}
	}

}
