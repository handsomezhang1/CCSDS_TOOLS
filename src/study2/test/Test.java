package study2.test;


import java.io.IOException;
import java.sql.SQLException;

import study2.utils.SqliteUtil;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		SqliteUtil sqlite = new SqliteUtil();

		String sql = "";

		// 创建表
		//sql = "CREATE TABLE COMPANY " + "(ID INT PRIMARY KEY     NOT NULL," + " NAME           TEXT    NOT NULL, "
		//		+ " AGE            INT     NOT NULL, " + " ADDRESS        CHAR(50), " + " SALARY         REAL)";

		//sqlite.createTables("hahaha", sql);

		// 插入数据
		//sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";

		//sqlite.insert("hahaha", sql);

		// 修改数据
		//sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";

		// sqlite.update("hahaha", sql);

		// 查询数据
		sql = "SELECT * FROM testTable;";
		System.out.println(sqlite.select("database", sql, null).get(1)); 

	}
}

