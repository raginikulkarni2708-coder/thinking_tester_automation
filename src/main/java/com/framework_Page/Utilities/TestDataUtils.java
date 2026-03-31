package com.framework_Page.Utilities;

import com.framework_Page.Pages.SignupPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

import static com.framework_Page.BaseClass.driver;

public class TestDataUtils {

    static Random random = new Random();

    public static String generateFirstName() {
        String[] names = {"Rita", "Amit", "Neha", "Rahul", "Priya"};
        return names[random.nextInt(names.length)];
    }

    // Last Name
    public static String generateLastName() {
        String[] names = {"Sharma", "Marco", "Khan", "Cano", "Verma"};
        return names[random.nextInt(names.length)];
    }



    public static String generateEmail() {
        return "testuser_" + UUID.randomUUID().toString() + "@gmail.com";
    }

    public static String generatePassword() {
        return "P@" + java.util.UUID.randomUUID().toString().substring(0,8);
    }

    // Phone Number (10 digit)
    public static String generatePhoneNumber() {
        return "9" + (100000000 + random.nextInt(900000000));
    }

    // DOB (random date)
    public static String generateDOB() {

        int minDay = (int) LocalDate.of(1990, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2005, 12, 31).toEpochDay();

        long randomDay = minDay + random.nextInt(maxDay - minDay);

        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        return randomDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    }
}




