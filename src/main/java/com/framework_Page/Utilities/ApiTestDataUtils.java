// src/main/java/com/framework_Page/Utilities/TestDataUtils.java
package com.framework_Page.Utilities;

import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.Map;

public class ApiTestDataUtils {

    private static final Faker faker = new Faker();

    // ── Dynamic Login Credentials
    public static Map<String, String> generateLoginCredentials() {
        Map<String, String> loginCredentials = new HashMap<>();
        loginCredentials.put("email", faker.internet().emailAddress());
        loginCredentials.put("password", faker.internet().password(8, 12, true, true));
        return loginCredentials;
    }


    // ── Dynamic Contact Data
    public static Map<String, String> generateContactData() {
        Map<String, String> contact = new HashMap<>();

        contact.put("firstName",     faker.name().firstName());
        contact.put("lastName",      faker.name().lastName());
        contact.put("email",         faker.internet().emailAddress());
        contact.put("phone",         faker.numerify("##########"));   // 10 digit number
        contact.put("birthdate",     faker.date().birthday(18, 60)
                .toInstant()
                .toString()
                .substring(0, 10));          // yyyy-MM-dd
        contact.put("street1",       faker.address().streetAddress());
        contact.put("city",          faker.address().city());
        contact.put("stateProvince", faker.address().stateAbbr());
        contact.put("postalCode",    faker.address().zipCode().substring(0, 5));
        contact.put("country",       "US");

        return contact;
    }
}