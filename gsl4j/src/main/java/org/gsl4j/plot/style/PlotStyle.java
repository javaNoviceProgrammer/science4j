package org.gsl4j.plot.style;

public enum PlotStyle {

	seabornTicks("seaborn-ticks") ,
	ggplot("ggplot") ,
	darkBackground("dark_background") ,
	bmh("bmh") ,
	seabornPoster("seaborn-poster") ,
	seabornNotebook("seaborn-notebook") ,
	fast("fast") ,
	seaborn("seaborn") ,
	classic("classic") ,
	solarizeLight2("Solarize_Light2") ,
	seabornDark("seaborn-dark") ,
	seabornPastel("seaborn-pastel"),
	seabornMuted("seaborn-muted") ,
	classicTest("_classic_test") ,
	seabornPaper("seaborn-paper") ,
	seabornColorblind("seaborn-colorblind") ,
	seabornBright("seaborn-bright") ,
	seabornTalk("seaborn-talk") ,
	seabornDarkPalette("seaborn-dark-palette") ,
	tableauColorblind10("tableau-colorblind10") ,
	seabornDarkgrid("seaborn-darkgrid") ,
	seabornWhitegrid("seaborn-whitegrid") ,
	fiveThirtyeight("fivethirtyeight") ,
	grayscale("grayscale") ,
	seabornWhite("seaborn-white") ,
	seabornDeep("seaborn-deep") ;


	private String value ;

	private PlotStyle(String value) {
		this.value = value ;
	}

	@Override
	public String toString() {
		return value ;
	}

}
