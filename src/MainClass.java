
public class MainClass {
	public static void main(String[] args) throws Exception {
		DBInterface.init();
		TableSetting.createDB();
		TableSetting.createGrants();
		TableSetting.createTable();
		TableSetting.createData();
//		new SettingFrame();
	}
}
