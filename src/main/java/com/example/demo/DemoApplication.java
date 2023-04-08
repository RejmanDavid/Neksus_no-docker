package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	@GetMapping("/db")
	public String database() {
		String name = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@ora1.uhk.cz:1521:orcl","DBrejmada1","DBrejmada122");
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery("select * from employee");
			while (res.next()){
				name = res.getString("first_name");
				break;// vypise prvni, todo vypsat seznam
			}
		} catch (ClassNotFoundException | SQLException e) {
			return String.format("Hello %s!", e);//vypise na strance error
			//throw new RuntimeException(e);
		}

		return String.format("Hello %s!", name);
	}

}
