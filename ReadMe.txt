--------------------------------------------
Read CSV file and convert px into different dp tool.
Using in Android dimens.xml.
Created by Smilee Yang in 2014/08/28.
--------------------------------------------
介紹：

	此Tool是用於讀取dimen.csv的csv格式檔案，其中欄位如下所示：
	若轉換目標為<dimen name="abc">1px</dimen>
	・dimen_name: 用於置放其中的abc
	・dimenValue_px: 用於置放其中的123
	・unit: 用於轉換其中的px位置希望置放的dp/sp/dip
	並將此csv檔中，dimenValue_px此欄的數值，轉換並儲存為以下格式的dimension數值：
	・res/values-sw320dp/dimens.xml: <dimen name="abc">0.67dp</dimen>
	・res/values-sw360dp/dimens.xml: <dimen name="abc">0.75dp</dimen>
	・res/values-sw600dp/dimens.xml: <dimen name="abc">1.25dp</dimen>
	・res/values-sw720dp/dimens.xml: <dimen name="abc">1.50dp</dimen>
	・res/values-sw800dp/dimens.xml: <dimen name="abc">1.67dp</dimen>

使用說明：
	
	ReadCSV資料夾中的dimen.csv檔，可以使用google document的試算表編輯完欄位及數值後，直接下載為csv格式的檔案並命名為dimen，然後取代此資料夾中的dimen.csv檔，接著，直接執行cmd，移至此資料夾的目錄底下後，直接輸入以下命令：
	java -jar ReadCSV.jar

按下enter後，若dimen.csv檔格式沒有問題，便會自動開始進行轉換工作，待轉換完成，會給出如下的提示：
	-Convert success!
	-Please enter a path that you want to save these file:
接著只要輸入想要轉換檔案的目的地，如：
	/Users/smilee_yang/Documents/workspace/SupportDifferentSize/res/
若輸入的位址沒有錯誤，即可將轉換完成的目錄（如：values-sw360dp/）連同dimens.xml存進目的地。
