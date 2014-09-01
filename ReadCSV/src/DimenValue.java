

public class DimenValue {
	private String dimenName, pxDimenValue, unit;

	public DimenValue() {
	}

	public DimenValue(String dimenName, String pxDimenValue, String unit) {
		this.dimenName = dimenName;
		this.pxDimenValue = pxDimenValue;
		this.unit = unit;
	}

	public void setDimenName(String dimenName) {
		this.dimenName = dimenName;
	}

	public String getDimenName() {
		return dimenName;
	}

	public void setPxDimenValue(String pxDimenValue) {
		this.pxDimenValue = pxDimenValue;
	}

	public String getPxDimenValue() {
		return pxDimenValue;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}

}
