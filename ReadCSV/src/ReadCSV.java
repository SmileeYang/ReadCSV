import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;

public class ReadCSV {
	
	static final String sw320dp = "sw320dp";
	static final String sw360dp = "sw360dp";
	static final String sw600dp = "sw600dp";
	static final String sw720dp = "sw720dp";
	static final String sw800dp = "sw800dp";
	
	public static void main(String[] args) {
		ReadCSV obj = new ReadCSV();
		obj.run();
	}

	public void run() {
		String csvFile = "./dimen.csv"; // name, px, unit
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<DimenValue> dimenList = new ArrayList<DimenValue>();
		String savingPath = null;
		try {
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				DimenValue dv = new DimenValue();
				// use comma as separator
				String[] dimen = line.split(cvsSplitBy);
				dv.setDimenName(dimen[0]);
				dv.setPxDimenValue(dimen[1]);
				dv.setUnit(dimen[2]);
				dimenList.add(dv);
			}
			if (!dimenList.isEmpty()) {
				System.out.print("Convert success!\nPlease enter a path that you want to save these file: ");
				File checkPath = null;
				do {
					savingPath = readKeyIn();
					checkPath = new File(savingPath);
					if (checkPath.exists()) {
						bufferedFileWriter(dimenList, savingPath, 5);
					} else if (!checkPath.exists()) {
						System.out.print("Sorry, this path is not a correct path, please try again: ");
					}
				}while (!checkPath.exists());
			} else {
				System.out.println("Sorry, the convert process failed, please check your csv file.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Convert process done!");
	}
	
	public static String readKeyIn() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String keyIn = null; 
		try {
			keyIn = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return keyIn;
	}
	
	public static void bufferedFileWriter(ArrayList<DimenValue> dimenList, String savingPath, int savingType) {
		String[] types = new String[] {sw320dp, sw360dp, sw600dp, sw720dp, sw800dp};
		Double[] formulaForPX = new Double[] { 0.67, 0.75, 1.25, 1.5, 1.67 }; // sw320dp, sw360dp, sw600dp, sw720dp, sw800dp
		String mFilePath = null; 
		Writer fw = null;
		BufferedWriter bw = null;
		String theFront, theDimen, theLast;
		Float originalValue, newValue;
		if (savingType != 0) {
			for (int i = 0; i < savingType; i++) {
				mFilePath = savingPath+"/values-"+types[i]+"/";
				File dir = new File(mFilePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				try {
					fw = new FileWriter(mFilePath + "dimens.xml", false);
					bw = new BufferedWriter(fw);
					theFront = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n";
					bw.write(theFront);
					bw.newLine();
					for (DimenValue dv : dimenList) {
						originalValue = Float.valueOf(dv.getPxDimenValue());
						newValue = (float) (originalValue * formulaForPX[i]);
						theDimen = "<dimen name=\"" + dv.getDimenName() + "\">" + newValue + dv.getUnit() + "</dimen>";
						bw.write(theDimen);
						bw.newLine();
					}
					theLast = "</resources>";
					bw.write(theLast);
					bw.newLine();
					bw.close();
				} catch (IOException e) {
					System.err.println("Error writing the file : ");
					e.printStackTrace();
				} finally {
					if (bw != null && fw != null) {
						try {
							bw.close();
							fw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
	}
	
}