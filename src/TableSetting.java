import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;

public class TableSetting {
	public static void createDB() throws Exception {
		ResultSet rs= DBInterface.stmt.executeQuery("show databases like '2022����'");
		
		if(rs.next()) {
			DBInterface.stmt.execute("drop database `2022����`");
		}
		DBInterface.stmt.execute("create database `2022����`");
		DBInterface.stmt.execute("use mysql");
		
		System.out.println("create DB");
	}
	
	public static void createGrants() throws Exception {
		ResultSet rs=DBInterface.stmt.executeQuery("select * from user where user='user'");
		
		if(rs.next()) {
			DBInterface.stmt.execute("drop user 'user'@'localhost'");
		}
		DBInterface.stmt.execute("create user 'user'@'localhost' identified by '1234'");
		DBInterface.stmt.execute("grant select,insert,update,delete on `2022����`.* to 'user'@'localhost'");
		
		System.out.println("Grants");
	}
	
	public static void createTable() throws Exception{
		DBInterface.stmt.execute("CREATE TABLE `2022����`.`grade` (  `gr_no` INT NOT NULL AUTO_INCREMENT,  `gr_name` VARCHAR(10) NOT NULL,  `gr_criteria` INT NOT NULL,  PRIMARY KEY (`gr_no`));");
		DBInterface.stmt.execute("CREATE TABLE `2022����`.`user` (  `u_no` INT NOT NULL AUTO_INCREMENT,  `u_id` VARCHAR(20) NOT NULL,  `u_pw` VARCHAR(30) NOT NULL,  `u_name` VARCHAR(30) NOT NULL,  `u_birth` DATE NOT NULL,  `u_gender` INT NOT NULL,  `gr_no` INT NOT NULL,  PRIMARY KEY (`u_no`, `u_id`),  INDEX `user_k_idx` (`gr_no` ASC) VISIBLE,  CONSTRAINT `user_k`    FOREIGN KEY (`gr_no`)    REFERENCES `2022����`.`grade` (`gr_no`)    ON DELETE NO ACTION    ON UPDATE NO ACTION);");
		DBInterface.stmt.execute("CREATE TABLE `2022����`.`area` (  `a_no` INT NOT NULL AUTO_INCREMENT,  `a_name` VARCHAR(15) NULL,  PRIMARY KEY (`a_no`));");
		DBInterface.stmt.execute("CREATE TABLE `2022����`.`theater` (\r\n"
				+ "  `t_no` INT NOT NULL AUTO_INCREMENT,\r\n"
				+ "  `t_name` VARCHAR(30) NOT NULL,\r\n"
				+ "  `a_no` INT NOT NULL,\r\n"
				+ "  `m_no` VARCHAR(200) NOT NULL,\r\n"
				+ "  PRIMARY KEY (`t_no`),\r\n"
				+ "  INDEX `t_k_idx` (`a_no` ASC) VISIBLE,\r\n"
				+ "  CONSTRAINT `t_k`\r\n"
				+ "    FOREIGN KEY (`a_no`)\r\n"
				+ "    REFERENCES `2022����`.`area` (`a_no`)\r\n"
				+ "    ON DELETE NO ACTION\r\n"
				+ "    ON UPDATE NO ACTION);\r\n"
				+ "");
		DBInterface.stmt.execute("CREATE TABLE `2022����`.`genre` (\r\n"
				+ "  `g_no` INT NOT NULL AUTO_INCREMENT,\r\n"
				+ "  `g_name` VARCHAR(10) NOT NULL,\r\n"
				+ "  PRIMARY KEY (`g_no`));\r\n"
				+ "");
		DBInterface.stmt.execute("CREATE TABLE `2022����`.`movie` (\r\n"
				+ "  `m_no` INT NOT NULL AUTO_INCREMENT,\r\n"
				+ "  `m_title` VARCHAR(50) NOT NULL,\r\n"
				+ "  `m_synopsis` TEXT NOT NULL,\r\n"
				+ "  `g_no` VARCHAR(100) NOT NULL,\r\n"
				+ "  `m_time` INT NOT NULL,\r\n"
				+ "  `m_open` INT NOT NULL,\r\n"
				+ "  `m_director` VARCHAR(20) NOT NULL,\r\n"
				+ "  PRIMARY KEY (`m_no`));\r\n"
				+ "");
		DBInterface.stmt.execute("CREATE TABLE `2022����`.`comment` (\r\n"
				+ "  `c_no` INT NOT NULL,\r\n"
				+ "  `u_no` INT NOT NULL,\r\n"
				+ "  `m_no` INT NOT NULL,\r\n"
				+ "  `c_text` TEXT NOT NULL,\r\n"
				+ "  `c_rate` INT NOT NULL,\r\n"
				+ "  PRIMARY KEY (`c_no`),\r\n"
				+ "  INDEX `c_k1_idx` (`u_no` ASC) VISIBLE,\r\n"
				+ "  INDEX `c_k2_idx` (`m_no` ASC) VISIBLE,\r\n"
				+ "  CONSTRAINT `c_k1`\r\n"
				+ "    FOREIGN KEY (`u_no`)\r\n"
				+ "    REFERENCES `2022����`.`user` (`u_no`)\r\n"
				+ "    ON DELETE NO ACTION\r\n"
				+ "    ON UPDATE NO ACTION,\r\n"
				+ "  CONSTRAINT `c_k2`\r\n"
				+ "    FOREIGN KEY (`m_no`)\r\n"
				+ "    REFERENCES `2022����`.`movie` (`m_no`)\r\n"
				+ "    ON DELETE NO ACTION\r\n"
				+ "    ON UPDATE NO ACTION);\r\n"
				+ "");
		DBInterface.stmt.execute("CREATE TABLE `2022����`.`reservation` (\r\n"
				+ "  `r_no` INT NOT NULL AUTO_INCREMENT,\r\n"
				+ "  `u_no` INT NOT NULL,\r\n"
				+ "  `m_no` INT NOT NULL,\r\n"
				+ "  `t_no` INT NOT NULL,\r\n"
				+ "  `r_date` DATE NOT NULL,\r\n"
				+ "  `r_time` VARCHAR(10) NOT NULL,\r\n"
				+ "  `r_seat` VARCHAR(200) NOT NULL,\r\n"
				+ "  `r_price` INT NOT NULL,\r\n"
				+ "  PRIMARY KEY (`r_no`),\r\n"
				+ "  INDEX `k1_idx` (`u_no` ASC) VISIBLE,\r\n"
				+ "  INDEX `k2_idx` (`m_no` ASC) VISIBLE,\r\n"
				+ "  INDEX `k3_idx` (`t_no` ASC) VISIBLE,\r\n"
				+ "  CONSTRAINT `k1`\r\n"
				+ "    FOREIGN KEY (`u_no`)\r\n"
				+ "    REFERENCES `2022����`.`user` (`u_no`)\r\n"
				+ "    ON DELETE NO ACTION\r\n"
				+ "    ON UPDATE NO ACTION,\r\n"
				+ "  CONSTRAINT `k2`\r\n"
				+ "    FOREIGN KEY (`m_no`)\r\n"
				+ "    REFERENCES `2022����`.`movie` (`m_no`)\r\n"
				+ "    ON DELETE NO ACTION\r\n"
				+ "    ON UPDATE NO ACTION,\r\n"
				+ "  CONSTRAINT `k3`\r\n"
				+ "    FOREIGN KEY (`t_no`)\r\n"
				+ "    REFERENCES `2022����`.`theater` (`t_no`)\r\n"
				+ "    ON DELETE NO ACTION\r\n"
				+ "    ON UPDATE NO ACTION);\r\n"
				+ "");
		
		System.out.println("Tables");
	}
	
	public static void createData() throws Exception{
		DBInterface.stmt.execute("use `2022����`");
		
		DataInsert("grade");
		DataInsert("user");
		DataInsert("area");
		DataInsert("theater");
		DataInsert("genre");
		DataInsert("movie");
		DataInsert("comment");
		DataInsert("reservation");
		
		System.out.println("Data");
	}
	
	public static void DataInsert(String n) throws Exception{
		String path = System.getProperty("user.dir") + "/datafile/" + n + ".csv";
		
		ResultSet rs = DBInterface.stmt.executeQuery("SHOW VARIABLES LIKE 'secure_file_priv'");
		if (rs.next()) {
			File in = new File(path);
			File out = new File(rs.getString(2) + n + ".csv");
			Files.copy(in.toPath(), out.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			path = out.getPath().replace("\\", "/");
			DBInterface.stmt.execute("load data infile '" + path + "' into table " + n + " fields terminated by ',' ignore 1 lines");
		}
	}
}
