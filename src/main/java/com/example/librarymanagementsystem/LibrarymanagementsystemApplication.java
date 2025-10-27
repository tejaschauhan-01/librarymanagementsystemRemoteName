package com.example.librarymanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LibrarymanagementsystemApplication {

	public static void main(String[] args) {
        List ls=new ArrayList<>();
        ls.add("e");
        System.out.println(ls);
		SpringApplication.run(LibrarymanagementsystemApplication.class, args);
	}
}
