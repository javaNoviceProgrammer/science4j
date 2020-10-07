package org.gsl4j;

import org.gsl4j.util.NativeLibraryLoader;

/**
 * This class describes macros for the values of physical constants, such as
 * the speed of light, c, and gravitational constant, G. The values are
 * available in different unit systems, including the standard MKSA system
 * (meters, kilograms, seconds, amperes) and the CGSM system (centimeters,
 * grams, seconds, gauss), which is commonly used in Astronomy. <br>
 * The definitions of constants in the MKSA system are available in the file
 * {@code gsl_const_mksa.h}. The constants in the CGSM system are defined in
 * {@code gsl_const_cgsm.h}. Dimensionless constants, such as the fine structure
 * constant, which are pure numbers are defined in {@code gsl_const_num.h}. <br> The full
 * list of constants is described briefly below. Consult the header files
 * themselves for the values of the constants used in the library.
 *
 * @author Meisam
 *
 */
public class PhysicalConstants {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private PhysicalConstants() {

	}

	////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////

	/**
	 * The electromagnetic fine structure constant \alpha.
	 */
	public static final double GSL_CONST_NUM_FINE_STRUCTURE = (7.297352533e-3) ; /* 1 */

	/**
	 * Avogadro’s number, N_a.
	 */
	public static final double GSL_CONST_NUM_AVOGADRO = (6.02214199e23) ; /* 1 / mol */

	/**
	 * 10^{24}
	 */
	public static final double GSL_CONST_NUM_YOTTA = (1e24) ; /* 1 */

	/**
	 * 10^{21}
	 */
	public static final double GSL_CONST_NUM_ZETTA = (1e21) ; /* 1 */

	/**
	 * 10^{18}
	 */
	public static final double GSL_CONST_NUM_EXA = (1e18) ; /* 1 */

	/**
	 * 10^{15}
	 */
	public static final double GSL_CONST_NUM_PETA = (1e15) ; /* 1 */

	/**
	 * 10^{12}
	 */
	public static final double GSL_CONST_NUM_TERA = (1e12) ; /* 1 */

	/**
	 * 10^9
	 */
	public static final double GSL_CONST_NUM_GIGA = (1e9) ; /* 1 */

	/**
	 * 10^6
	 */
	public static final double GSL_CONST_NUM_MEGA = (1e6) ; /* 1 */

	/**
	 * 10^3
	 */
	public static final double GSL_CONST_NUM_KILO = (1e3) ; /* 1 */

	/**
	 * 10^{-3}
	 */
	public static final double GSL_CONST_NUM_MILLI = (1e-3) ; /* 1 */

	/**
	 * 10^{-6}
	 */
	public static final double GSL_CONST_NUM_MICRO = (1e-6) ; /* 1 */

	/**
	 * 10^{-9}
	 */
	public static final double GSL_CONST_NUM_NANO = (1e-9) ; /* 1 */

	/**
	 * 10^{-12}
	 */
	public static final double GSL_CONST_NUM_PICO = (1e-12) ; /* 1 */

	/**
	 * 10^{-15}
	 */
	public static final double GSL_CONST_NUM_FEMTO = (1e-15) ; /* 1 */

	/**
	 * 10^{-18}
	 */
	public static final double GSL_CONST_NUM_ATTO = (1e-18) ; /* 1 */

	/**
	 * 10^{-21}
	 */
	public static final double GSL_CONST_NUM_ZEPTO = (1e-21) ; /* 1 */

	/**
	 * 10^{-24}
	 */
	public static final double GSL_CONST_NUM_YOCTO = (1e-24) ; /* 1 */

	////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////

	/**
	 * The speed of light in vacuum, c.
	 */
	public static final double GSL_CONST_MKSA_SPEED_OF_LIGHT = (2.99792458e8) ; /* m / s */

	/**
	 * The gravitational constant, G.
	 */
	public static final double GSL_CONST_MKSA_GRAVITATIONAL_CONSTANT = (6.673e-11) ; /* m^3 / kg s^2 */

	/**
	 * Planck’s constant, h.
	 */
	public static final double GSL_CONST_MKSA_PLANCKS_CONSTANT_H = (6.62606896e-34) ; /* kg m^2 / s */

	/**
	 * Planck’s constant divided by 2\pi, \hbar.
	 */
	public static final double GSL_CONST_MKSA_PLANCKS_CONSTANT_HBAR = (1.05457162825e-34) ; /* kg m^2 / s */

	/**
	 * The length of 1 astronomical unit (mean earth-sun distance), au.
	 */
	public static final double GSL_CONST_MKSA_ASTRONOMICAL_UNIT = (1.49597870691e11) ; /* m */

	/**
	 * The distance of 1 light-year, ly.
	 */
	public static final double GSL_CONST_MKSA_LIGHT_YEAR = (9.46053620707e15) ; /* m */

	/**
	 * The distance of 1 parsec, pc.
	 */
	public static final double GSL_CONST_MKSA_PARSEC = (3.08567758135e16) ; /* m */

	/**
	 * The standard gravitational acceleration on Earth, g.
	 */
	public static final double GSL_CONST_MKSA_GRAV_ACCEL = (9.80665e0) ; /* m / s^2 */

	/**
	 * The energy of 1 electron volt, eV.
	 */
	public static final double GSL_CONST_MKSA_ELECTRON_VOLT = (1.602176487e-19) ; /* kg m^2 / s^2 */

	/**
	 * The mass of the electron, m_e.
	 */
	public static final double GSL_CONST_MKSA_MASS_ELECTRON = (9.10938188e-31) ; /* kg */

	/**
	 * The mass of the muon, m_\mu.
	 */
	public static final double GSL_CONST_MKSA_MASS_MUON = (1.88353109e-28) ; /* kg */

	/**
	 * The mass of the proton, m_p.
	 */
	public static final double GSL_CONST_MKSA_MASS_PROTON = (1.67262158e-27) ; /* kg */

	/**
	 * The mass of the neutron, m_n.
	 */
	public static final double GSL_CONST_MKSA_MASS_NEUTRON = (1.67492716e-27) ; /* kg */

	/**
	 * The Rydberg constant, Ry, in units of energy. This is related to the Rydberg inverse wavelength R_\infty by Ry = h c R_\infty.
	 */
	public static final double GSL_CONST_MKSA_RYDBERG = (2.17987196968e-18) ; /* kg m^2 / s^2 */

	/**
	 * The Boltzmann constant, k.
	 */
	public static final double GSL_CONST_MKSA_BOLTZMANN = (1.3806504e-23) ; /* kg m^2 / K s^2 */

	/**
	 * The molar gas constant, R_0.
	 */
	public static final double GSL_CONST_MKSA_MOLAR_GAS = (8.314472e0) ; /* kg m^2 / K mol s^2 */

	/**
	 * The standard gas volume, V_0.
	 */
	public static final double GSL_CONST_MKSA_STANDARD_GAS_VOLUME = (2.2710981e-2) ; /* m^3 / mol */

	/**
	 * The number of seconds in 1 minute.
	 */
	public static final double GSL_CONST_MKSA_MINUTE = (6e1) ; /* s */

	/**
	 * The number of seconds in 1 hour.
	 */
	public static final double GSL_CONST_MKSA_HOUR = (3.6e3) ; /* s */

	/**
	 * The number of seconds in 1 day.
	 */
	public static final double GSL_CONST_MKSA_DAY = (8.64e4) ; /* s */

	/**
	 * The number of seconds in 1 week.
	 */
	public static final double GSL_CONST_MKSA_WEEK = (6.048e5) ; /* s */

	/**
	 * The length of 1 inch.
	 */
	public static final double GSL_CONST_MKSA_INCH = (2.54e-2) ; /* m */

	/**
	 * The length of 1 foot.
	 */
	public static final double GSL_CONST_MKSA_FOOT = (3.048e-1) ; /* m */

	/**
	 * The length of 1 yard.
	 */
	public static final double GSL_CONST_MKSA_YARD = (9.144e-1) ; /* m */

	/**
	 * The length of 1 mile.
	 */
	public static final double GSL_CONST_MKSA_MILE = (1.609344e3) ; /* m */

	/**
	 * The length of 1 nautical mile.
	 */
	public static final double GSL_CONST_MKSA_NAUTICAL_MILE = (1.852e3) ; /* m */

	/**
	 * The length of 1 fathom.
	 */
	public static final double GSL_CONST_MKSA_FATHOM = (1.8288e0) ; /* m */

	/**
	 * The length of 1 mil (1/1000th of an inch).
	 */
	public static final double GSL_CONST_MKSA_MIL = (2.54e-5) ; /* m */

	/**
	 * The length of 1 printer’s point (1/72 inch).
	 */
	public static final double GSL_CONST_MKSA_POINT = (3.52777777778e-4) ; /* m */

	/**
	 * The length of 1 TeX point (1/72.27 inch).
	 */
	public static final double GSL_CONST_MKSA_TEXPOINT = (3.51459803515e-4) ; /* m */
	public static final double GSL_CONST_MKSA_MICRON = (1e-6) ; /* m */
	public static final double GSL_CONST_MKSA_ANGSTROM = (1e-10) ; /* m */
	public static final double GSL_CONST_MKSA_HECTARE = (1e4) ; /* m^2 */
	public static final double GSL_CONST_MKSA_ACRE = (4.04685642241e3) ; /* m^2 */
	public static final double GSL_CONST_MKSA_BARN = (1e-28) ; /* m^2 */
	public static final double GSL_CONST_MKSA_LITER = (1e-3) ; /* m^3 */
	public static final double GSL_CONST_MKSA_US_GALLON = (3.78541178402e-3) ; /* m^3 */
	public static final double GSL_CONST_MKSA_QUART = (9.46352946004e-4) ; /* m^3 */
	public static final double GSL_CONST_MKSA_PINT = (4.73176473002e-4) ; /* m^3 */
	public static final double GSL_CONST_MKSA_CUP = (2.36588236501e-4) ; /* m^3 */
	public static final double GSL_CONST_MKSA_FLUID_OUNCE = (2.95735295626e-5) ; /* m^3 */
	public static final double GSL_CONST_MKSA_TABLESPOON = (1.47867647813e-5) ; /* m^3 */
	public static final double GSL_CONST_MKSA_TEASPOON = (4.92892159375e-6) ; /* m^3 */
	public static final double GSL_CONST_MKSA_CANADIAN_GALLON = (4.54609e-3) ; /* m^3 */
	public static final double GSL_CONST_MKSA_UK_GALLON = (4.546092e-3) ; /* m^3 */
	public static final double GSL_CONST_MKSA_MILES_PER_HOUR = (4.4704e-1) ; /* m / s */
	public static final double GSL_CONST_MKSA_KILOMETERS_PER_HOUR = (2.77777777778e-1) ; /* m / s */
	public static final double GSL_CONST_MKSA_KNOT = (5.14444444444e-1) ; /* m / s */
	public static final double GSL_CONST_MKSA_POUND_MASS = (4.5359237e-1) ; /* kg */
	public static final double GSL_CONST_MKSA_OUNCE_MASS = (2.8349523125e-2) ; /* kg */
	public static final double GSL_CONST_MKSA_TON = (9.0718474e2) ; /* kg */
	public static final double GSL_CONST_MKSA_METRIC_TON = (1e3) ; /* kg */
	public static final double GSL_CONST_MKSA_UK_TON = (1.0160469088e3) ; /* kg */
	public static final double GSL_CONST_MKSA_TROY_OUNCE = (3.1103475e-2) ; /* kg */
	public static final double GSL_CONST_MKSA_CARAT = (2e-4) ; /* kg */
	public static final double GSL_CONST_MKSA_UNIFIED_ATOMIC_MASS = (1.660538782e-27) ; /* kg */
	public static final double GSL_CONST_MKSA_GRAM_FORCE = (9.80665e-3) ; /* kg m / s^2 */
	public static final double GSL_CONST_MKSA_POUND_FORCE = (4.44822161526e0) ; /* kg m / s^2 */
	public static final double GSL_CONST_MKSA_KILOPOUND_FORCE = (4.44822161526e3) ; /* kg m / s^2 */
	public static final double GSL_CONST_MKSA_POUNDAL = (1.38255e-1) ; /* kg m / s^2 */
	public static final double GSL_CONST_MKSA_CALORIE = (4.1868e0) ; /* kg m^2 / s^2 */
	public static final double GSL_CONST_MKSA_BTU = (1.05505585262e3) ; /* kg m^2 / s^2 */
	public static final double GSL_CONST_MKSA_THERM = (1.05506e8) ; /* kg m^2 / s^2 */
	public static final double GSL_CONST_MKSA_HORSEPOWER = (7.457e2) ; /* kg m^2 / s^3 */
	public static final double GSL_CONST_MKSA_BAR = (1e5) ; /* kg / m s^2 */
	public static final double GSL_CONST_MKSA_STD_ATMOSPHERE = (1.01325e5) ; /* kg / m s^2 */
	public static final double GSL_CONST_MKSA_TORR = (1.33322368421e2) ; /* kg / m s^2 */
	public static final double GSL_CONST_MKSA_METER_OF_MERCURY = (1.33322368421e5) ; /* kg / m s^2 */
	public static final double GSL_CONST_MKSA_INCH_OF_MERCURY = (3.38638815789e3) ; /* kg / m s^2 */
	public static final double GSL_CONST_MKSA_INCH_OF_WATER = (2.490889e2) ; /* kg / m s^2 */
	public static final double GSL_CONST_MKSA_PSI = (6.89475729317e3) ; /* kg / m s^2 */
	public static final double GSL_CONST_MKSA_POISE = (1e-1) ; /* kg m^-1 s^-1 */
	public static final double GSL_CONST_MKSA_STOKES = (1e-4) ; /* m^2 / s */
	public static final double GSL_CONST_MKSA_STILB = (1e4) ; /* cd / m^2 */
	public static final double GSL_CONST_MKSA_LUMEN = (1e0) ; /* cd sr */
	public static final double GSL_CONST_MKSA_LUX = (1e0) ; /* cd sr / m^2 */
	public static final double GSL_CONST_MKSA_PHOT = (1e4) ; /* cd sr / m^2 */
	public static final double GSL_CONST_MKSA_FOOTCANDLE = (1.076e1) ; /* cd sr / m^2 */
	public static final double GSL_CONST_MKSA_LAMBERT = (1e4) ; /* cd sr / m^2 */
	public static final double GSL_CONST_MKSA_FOOTLAMBERT = (1.07639104e1) ; /* cd sr / m^2 */
	public static final double GSL_CONST_MKSA_CURIE = (3.7e10) ; /* 1 / s */
	public static final double GSL_CONST_MKSA_ROENTGEN = (2.58e-4) ; /* A s / kg */
	public static final double GSL_CONST_MKSA_RAD = (1e-2) ; /* m^2 / s^2 */
	public static final double GSL_CONST_MKSA_SOLAR_MASS = (1.98892e30) ; /* kg */
	public static final double GSL_CONST_MKSA_BOHR_RADIUS = (5.291772083e-11) ; /* m */
	public static final double GSL_CONST_MKSA_NEWTON = (1e0) ; /* kg m / s^2 */
	public static final double GSL_CONST_MKSA_DYNE = (1e-5) ; /* kg m / s^2 */
	public static final double GSL_CONST_MKSA_JOULE = (1e0) ; /* kg m^2 / s^2 */
	public static final double GSL_CONST_MKSA_ERG = (1e-7) ; /* kg m^2 / s^2 */
	public static final double GSL_CONST_MKSA_STEFAN_BOLTZMANN_CONSTANT = (5.67040047374e-8) ; /* kg / K^4 s^3 */
	public static final double GSL_CONST_MKSA_THOMSON_CROSS_SECTION = (6.65245893699e-29) ; /* m^2 */
	public static final double GSL_CONST_MKSA_BOHR_MAGNETON = (9.27400899e-24) ; /* A m^2 */
	public static final double GSL_CONST_MKSA_NUCLEAR_MAGNETON = (5.05078317e-27) ; /* A m^2 */
	public static final double GSL_CONST_MKSA_ELECTRON_MAGNETIC_MOMENT = (9.28476362e-24) ; /* A m^2 */
	public static final double GSL_CONST_MKSA_PROTON_MAGNETIC_MOMENT = (1.410606633e-26) ; /* A m^2 */
	public static final double GSL_CONST_MKSA_FARADAY = (9.64853429775e4) ; /* A s / mol */
	public static final double GSL_CONST_MKSA_ELECTRON_CHARGE = (1.602176487e-19) ; /* A s */
	public static final double GSL_CONST_MKSA_VACUUM_PERMITTIVITY = (8.854187817e-12) ; /* A^2 s^4 / kg m^3 */
	public static final double GSL_CONST_MKSA_VACUUM_PERMEABILITY = (1.25663706144e-6) ; /* kg m / A^2 s^2 */
	public static final double GSL_CONST_MKSA_DEBYE = (3.33564095198e-30) ; /* A s^2 / m^2 */
	public static final double GSL_CONST_MKSA_GAUSS = (1e-4) ; /* kg / A s^2 */

	////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////

	public static final double GSL_CONST_CGSM_SPEED_OF_LIGHT = (2.99792458e10) ; /* cm / s */
	public static final double GSL_CONST_CGSM_GRAVITATIONAL_CONSTANT = (6.673e-8) ; /* cm^3 / g s^2 */
	public static final double GSL_CONST_CGSM_PLANCKS_CONSTANT_H = (6.62606896e-27) ; /* g cm^2 / s */
	public static final double GSL_CONST_CGSM_PLANCKS_CONSTANT_HBAR = (1.05457162825e-27) ; /* g cm^2 / s */
	public static final double GSL_CONST_CGSM_ASTRONOMICAL_UNIT = (1.49597870691e13) ; /* cm */
	public static final double GSL_CONST_CGSM_LIGHT_YEAR = (9.46053620707e17) ; /* cm */
	public static final double GSL_CONST_CGSM_PARSEC = (3.08567758135e18) ; /* cm */
	public static final double GSL_CONST_CGSM_GRAV_ACCEL = (9.80665e2) ; /* cm / s^2 */
	public static final double GSL_CONST_CGSM_ELECTRON_VOLT = (1.602176487e-12) ; /* g cm^2 / s^2 */
	public static final double GSL_CONST_CGSM_MASS_ELECTRON = (9.10938188e-28) ; /* g */
	public static final double GSL_CONST_CGSM_MASS_MUON = (1.88353109e-25) ; /* g */
	public static final double GSL_CONST_CGSM_MASS_PROTON = (1.67262158e-24) ; /* g */
	public static final double GSL_CONST_CGSM_MASS_NEUTRON = (1.67492716e-24) ; /* g */
	public static final double GSL_CONST_CGSM_RYDBERG = (2.17987196968e-11) ; /* g cm^2 / s^2 */
	public static final double GSL_CONST_CGSM_BOLTZMANN = (1.3806504e-16) ; /* g cm^2 / K s^2 */
	public static final double GSL_CONST_CGSM_MOLAR_GAS = (8.314472e7) ; /* g cm^2 / K mol s^2 */
	public static final double GSL_CONST_CGSM_STANDARD_GAS_VOLUME = (2.2710981e4) ; /* cm^3 / mol */
	public static final double GSL_CONST_CGSM_MINUTE = (6e1) ; /* s */
	public static final double GSL_CONST_CGSM_HOUR = (3.6e3) ; /* s */
	public static final double GSL_CONST_CGSM_DAY = (8.64e4) ; /* s */
	public static final double GSL_CONST_CGSM_WEEK = (6.048e5) ; /* s */
	public static final double GSL_CONST_CGSM_INCH = (2.54e0) ; /* cm */
	public static final double GSL_CONST_CGSM_FOOT = (3.048e1) ; /* cm */
	public static final double GSL_CONST_CGSM_YARD = (9.144e1) ; /* cm */
	public static final double GSL_CONST_CGSM_MILE = (1.609344e5) ; /* cm */
	public static final double GSL_CONST_CGSM_NAUTICAL_MILE = (1.852e5) ; /* cm */
	public static final double GSL_CONST_CGSM_FATHOM = (1.8288e2) ; /* cm */
	public static final double GSL_CONST_CGSM_MIL = (2.54e-3) ; /* cm */
	public static final double GSL_CONST_CGSM_POINT = (3.52777777778e-2) ; /* cm */
	public static final double GSL_CONST_CGSM_TEXPOINT = (3.51459803515e-2) ; /* cm */
	public static final double GSL_CONST_CGSM_MICRON = (1e-4) ; /* cm */
	public static final double GSL_CONST_CGSM_ANGSTROM = (1e-8) ; /* cm */
	public static final double GSL_CONST_CGSM_HECTARE = (1e8) ; /* cm^2 */
	public static final double GSL_CONST_CGSM_ACRE = (4.04685642241e7) ; /* cm^2 */
	public static final double GSL_CONST_CGSM_BARN = (1e-24) ; /* cm^2 */
	public static final double GSL_CONST_CGSM_LITER = (1e3) ; /* cm^3 */
	public static final double GSL_CONST_CGSM_US_GALLON = (3.78541178402e3) ; /* cm^3 */
	public static final double GSL_CONST_CGSM_QUART = (9.46352946004e2) ; /* cm^3 */
	public static final double GSL_CONST_CGSM_PINT = (4.73176473002e2) ; /* cm^3 */
	public static final double GSL_CONST_CGSM_CUP = (2.36588236501e2) ; /* cm^3 */
	public static final double GSL_CONST_CGSM_FLUID_OUNCE = (2.95735295626e1) ; /* cm^3 */
	public static final double GSL_CONST_CGSM_TABLESPOON = (1.47867647813e1) ; /* cm^3 */
	public static final double GSL_CONST_CGSM_TEASPOON = (4.92892159375e0) ; /* cm^3 */
	public static final double GSL_CONST_CGSM_CANADIAN_GALLON = (4.54609e3) ; /* cm^3 */
	public static final double GSL_CONST_CGSM_UK_GALLON = (4.546092e3) ; /* cm^3 */
	public static final double GSL_CONST_CGSM_MILES_PER_HOUR = (4.4704e1) ; /* cm / s */
	public static final double GSL_CONST_CGSM_KILOMETERS_PER_HOUR = (2.77777777778e1) ; /* cm / s */
	public static final double GSL_CONST_CGSM_KNOT = (5.14444444444e1) ; /* cm / s */
	public static final double GSL_CONST_CGSM_POUND_MASS = (4.5359237e2) ; /* g */
	public static final double GSL_CONST_CGSM_OUNCE_MASS = (2.8349523125e1) ; /* g */
	public static final double GSL_CONST_CGSM_TON = (9.0718474e5) ; /* g */
	public static final double GSL_CONST_CGSM_METRIC_TON = (1e6) ; /* g */
	public static final double GSL_CONST_CGSM_UK_TON = (1.0160469088e6) ; /* g */
	public static final double GSL_CONST_CGSM_TROY_OUNCE = (3.1103475e1) ; /* g */
	public static final double GSL_CONST_CGSM_CARAT = (2e-1) ; /* g */
	public static final double GSL_CONST_CGSM_UNIFIED_ATOMIC_MASS = (1.660538782e-24) ; /* g */
	public static final double GSL_CONST_CGSM_GRAM_FORCE = (9.80665e2) ; /* cm g / s^2 */
	public static final double GSL_CONST_CGSM_POUND_FORCE = (4.44822161526e5) ; /* cm g / s^2 */
	public static final double GSL_CONST_CGSM_KILOPOUND_FORCE = (4.44822161526e8) ; /* cm g / s^2 */
	public static final double GSL_CONST_CGSM_POUNDAL = (1.38255e4) ; /* cm g / s^2 */
	public static final double GSL_CONST_CGSM_CALORIE = (4.1868e7) ; /* g cm^2 / s^2 */
	public static final double GSL_CONST_CGSM_BTU = (1.05505585262e10) ; /* g cm^2 / s^2 */
	public static final double GSL_CONST_CGSM_THERM = (1.05506e15) ; /* g cm^2 / s^2 */
	public static final double GSL_CONST_CGSM_HORSEPOWER = (7.457e9) ; /* g cm^2 / s^3 */
	public static final double GSL_CONST_CGSM_BAR = (1e6) ; /* g / cm s^2 */
	public static final double GSL_CONST_CGSM_STD_ATMOSPHERE = (1.01325e6) ; /* g / cm s^2 */
	public static final double GSL_CONST_CGSM_TORR = (1.33322368421e3) ; /* g / cm s^2 */
	public static final double GSL_CONST_CGSM_METER_OF_MERCURY = (1.33322368421e6) ; /* g / cm s^2 */
	public static final double GSL_CONST_CGSM_INCH_OF_MERCURY = (3.38638815789e4) ; /* g / cm s^2 */
	public static final double GSL_CONST_CGSM_INCH_OF_WATER = (2.490889e3) ; /* g / cm s^2 */
	public static final double GSL_CONST_CGSM_PSI = (6.89475729317e4) ; /* g / cm s^2 */
	public static final double GSL_CONST_CGSM_POISE = (1e0) ; /* g / cm s */
	public static final double GSL_CONST_CGSM_STOKES = (1e0) ; /* cm^2 / s */
	public static final double GSL_CONST_CGSM_STILB = (1e0) ; /* cd / cm^2 */
	public static final double GSL_CONST_CGSM_LUMEN = (1e0) ; /* cd sr */
	public static final double GSL_CONST_CGSM_LUX = (1e-4) ; /* cd sr / cm^2 */
	public static final double GSL_CONST_CGSM_PHOT = (1e0) ; /* cd sr / cm^2 */
	public static final double GSL_CONST_CGSM_FOOTCANDLE = (1.076e-3) ; /* cd sr / cm^2 */
	public static final double GSL_CONST_CGSM_LAMBERT = (1e0) ; /* cd sr / cm^2 */
	public static final double GSL_CONST_CGSM_FOOTLAMBERT = (1.07639104e-3) ; /* cd sr / cm^2 */
	public static final double GSL_CONST_CGSM_CURIE = (3.7e10) ; /* 1 / s */
	public static final double GSL_CONST_CGSM_ROENTGEN = (2.58e-8) ; /* abamp s / g */
	public static final double GSL_CONST_CGSM_RAD = (1e2) ; /* cm^2 / s^2 */
	public static final double GSL_CONST_CGSM_SOLAR_MASS = (1.98892e33) ; /* g */
	public static final double GSL_CONST_CGSM_BOHR_RADIUS = (5.291772083e-9) ; /* cm */
	public static final double GSL_CONST_CGSM_NEWTON = (1e5) ; /* cm g / s^2 */
	public static final double GSL_CONST_CGSM_DYNE = (1e0) ; /* cm g / s^2 */
	public static final double GSL_CONST_CGSM_JOULE = (1e7) ; /* g cm^2 / s^2 */
	public static final double GSL_CONST_CGSM_ERG = (1e0) ; /* g cm^2 / s^2 */
	public static final double GSL_CONST_CGSM_STEFAN_BOLTZMANN_CONSTANT = (5.67040047374e-5) ; /* g / K^4 s^3 */
	public static final double GSL_CONST_CGSM_THOMSON_CROSS_SECTION = (6.65245893699e-25) ; /* cm^2 */
	public static final double GSL_CONST_CGSM_BOHR_MAGNETON = (9.27400899e-21) ; /* abamp cm^2 */
	public static final double GSL_CONST_CGSM_NUCLEAR_MAGNETON = (5.05078317e-24) ; /* abamp cm^2 */
	public static final double GSL_CONST_CGSM_ELECTRON_MAGNETIC_MOMENT = (9.28476362e-21) ; /* abamp cm^2 */
	public static final double GSL_CONST_CGSM_PROTON_MAGNETIC_MOMENT = (1.410606633e-23) ; /* abamp cm^2 */
	public static final double GSL_CONST_CGSM_FARADAY = (9.64853429775e3) ; /* abamp s / mol */
	public static final double GSL_CONST_CGSM_ELECTRON_CHARGE = (1.602176487e-20) ; /* abamp s */


}
