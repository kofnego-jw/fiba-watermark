package at.ac.uibk.fiba.watermark.stamper;

/**
 * DataObject class for watermark setting
 * @author totoro
 *
 */
public class WatermarkSetting {
	
	/**
	 * Stamping methods
	 * @author totoro
	 *
	 */
	public enum StampingMode {
		/**
		 * ADD: Adds the watermark at the place to the picture.
		 */
		ADD,
		/**
		 * SUBSTRACT: Substracts the watermark from the picture
		 */
		SUBSTRACT
	}

	/**
	 * DEFAULT_SETTING: posX = 0.66, poxY=0.85, sizeX=0.10, sizeY=0.10F, 
	 * opacity=0.20, backgroundColor=WHITE
	 * stampingMode = ADD
	 */
	public static final WatermarkSetting DEFAULT_SETTING = 
			new WatermarkSetting(0.66F, 0.85F, 0.22F, 0.10F, 0.20F, ColorValue.WHITE, StampingMode.ADD);
	
	/**
	 * Position at the X-axis, 0 is left, 1 is right
	 */
	private final float posX;
	/**
	 * Position at Y-axis, 0 is top, 1 is bottom.
	 */
	private final float poxY;
	/**
	 * Size of the watermark on the original. 1 = Watermark is as wide as picture allows, 
	 * 0.5 = watermark is half as wide as the original.
	 */
	private final float sizeX;
	/**
	 * Size of the watermark on the original. 1 = Watermark is as high as picture allows, 
	 * 0.5 = watermark is half as high as the original.
	 */
	private final float sizeY;
	/**
	 * How visible is the watermark: 0 = not visible, 1 = only watermark is visible.
	 */
	private final float opacity;
	
	/**
	 * backgroundColor: the background color of the watermark. If the watermark has this 
	 * value, then it will be ignored. If this is null, then no background is considered.
	 */
	private final ColorValue backgroundColor;
	/**
	 * Stamping method: ADD or SUBSTRACT
	 */
	private final StampingMode stampingMode;
	
	public WatermarkSetting(float posX, float poxY, float sizeX, float sizeY, float opacity,
			ColorValue backgroundColor, StampingMode stampingMode) {
		super();
		this.posX = posX;
		this.poxY = poxY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.opacity = opacity;
		this.backgroundColor = backgroundColor;
		this.stampingMode = stampingMode;
	}
	/**
	 * @return the posX
	 */
	public float getPosX() {
		return posX;
	}
	/**
	 * @return the poxY
	 */
	public float getPoxY() {
		return poxY;
	}
	/**
	 * @return the sizeX
	 */
	public float getSizeX() {
		return sizeX;
	}
	/**
	 * @return the sizeY
	 */
	public float getSizeY() {
		return sizeY;
	}
	/**
	 * @return the stampingMode
	 */
	public StampingMode getStampingMode() {
		return stampingMode;
	}
	/**
	 * @return the opacity
	 */
	public float getOpacity() {
		return opacity;
	}
	/**
	 * @return the backgroundColor
	 */
	public ColorValue getBackgroundColor() {
		return backgroundColor;
	}
	
	
	

}
