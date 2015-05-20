package at.ac.uibk.fiba.watermark.stamper;

/**
 * Class storing color encoded as RGB.
 * @author totoro
 *
 */
public final class ColorValue {
	
	/**
	 * White
	 */
	public static final ColorValue WHITE = new ColorValue(255,255,255);
	/**
	 * Black
	 */
	public static final ColorValue BLACK = new ColorValue(0,0,0);
	
	/**
	 * red
	 */
	private final int red;
	/**
	 * green
	 */
	private final int green;
	/**
	 * blue
	 */
	private final int blue;
	
	public ColorValue(int red, int green, int blue) {
		super();
		red = correctValue(red);
		green = correctValue(green);
		blue = correctValue(blue);
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	/**
	 * changes value to be between 0 and 255 (incl.)
	 * @param val
	 * @return
	 */
	protected int correctValue(int val) {
		return val < 0 ? 0 :
			val > 255 ? 255 : val;
	}
	
	/**
	 * @return the red
	 */
	public int getRed() {
		return red;
	}
	/**
	 * @return the green
	 */
	public int getGreen() {
		return green;
	}
	/**
	 * @return the blue
	 */
	public int getBlue() {
		return blue;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + blue;
		result = prime * result + green;
		result = prime * result + red;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColorValue other = (ColorValue) obj;
		if (blue != other.blue)
			return false;
		if (green != other.green)
			return false;
		if (red != other.red)
			return false;
		return true;
	}
	
	

}
