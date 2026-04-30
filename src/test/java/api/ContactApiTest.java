// ContactApiTest.java
package api;

import com.framework_Page.Utilities.ApiTestDataUtils;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.github.javafaker.Faker;



public class ContactApiTest {

    private String token;
    private final Gson gson = new Gson();
    private Map<String, String> userCredentials;
    private List<String> createdContactIds = new ArrayList<>();
    private static final Faker faker = new Faker();




    @BeforeClass
    public void loginSetup(){

      /* Step 1 — Generate dynamic credentials
        userCredentials = ApiTestDataUtils.generateLoginCredentials();

        System.out.println("Email:"  + userCredentials.get("email"));
        System.out.println("Password:"  + userCredentials.get("password"));

        // Step 2 — Register the new user first
        Response registerRes = RestAssured
                .given()
                .baseUri("https://thinking-tester-contact-list.herokuapp.com")
                .contentType("application/json")
                .body(gson.toJson(userCredentials))
                .when()
                .post("/users");

        System.out.println("Register Status : " + registerRes.statusCode());
*/
    userCredentials = ApiTestDataUtils.generateLoginCredentials();

    Map<String, String> loginBody = new HashMap<>();
        loginBody.put("email", "raginikulkarni2708@gmail.com");
        loginBody.put("password", "Ragini@123");

    Response loginRes = RestAssured
            .given()
            .baseUri("https://thinking-tester-contact-list.herokuapp.com")
            .contentType("application/json")
            .body(gson.toJson(loginBody))

            .when()
            .post("/users/login");

    token = loginRes.jsonPath().getString("token");
        System.out.println("Token : " + token);
}

    @DataProvider(name= "contactDataProvider")
    public Object[][] getContactData() {
        return new Object[][]{
                {ApiTestDataUtils.generateContactData()},
                {ApiTestDataUtils.generateContactData()},
                {ApiTestDataUtils.generateContactData()},
        };
    }

    @DataProvider(name= "updatedContactDataProvider")
    public Object[][] getUpdatedContactData() {
        return new Object[][]{
                {ApiTestDataUtils.generateContactData()},
        };
    }





    @Test(dataProvider = "contactDataProvider", priority = 1)
    public void testContactAdded(Map<String, String> contactData) {

        // ── Fetch dynamic contact data from Utilities ──
       //  contactData = ApiTestDataUtils.generateContactData();

        System.out.println("Testing with : " + contactData.get("firstName")
                + " " + contactData.get("lastName")
                + " | " + contactData.get("email"));


        Response res = RestAssured

                .given()
                .baseUri("https://thinking-tester-contact-list.herokuapp.com")
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(gson.toJson(contactData))
                .when()
                .post("/contacts");

        System.out.println("Status Code : " + res.statusCode());
        System.out.println("Response    : " + res.getBody().asString());

        String id= res.jsonPath().getString("_id");
        createdContactIds.add(id);
        System.out.println("Created Contact Id : " + id);
        System.out.println("Created Contact Ids so far : " + createdContactIds);


        // Assertions — verify what was sent is what got saved
        Assert.assertEquals(res.statusCode(), 201);
        Assert.assertEquals(res.jsonPath().getString("firstName"),contactData.get("firstName") );
        Assert.assertEquals(res.jsonPath().getString("lastName"),contactData.get("lastName") );
        Assert.assertEquals(res.jsonPath().getString("email"),contactData.get("email") );


   }

   @Test(priority = 2)
    public void testContactList() {
        System.out.println("Testing with : " + ApiTestDataUtils.generateContactData());

        Response res= RestAssured

                .given()
                .baseUri("https://thinking-tester-contact-list.herokuapp.com")
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/contacts");

        System.out.println("Status Code : " + res.statusCode());
        System.out.println("Response    : " + res.getBody().asString());

    }

    @Test(dataProvider = "updatedContactDataProvider" ,priority = 3 , dependsOnMethods = "testContactAdded" )
    public void updateContactPUT(Map<String, String> updatedData) {

        String ContactToUpdate = createdContactIds.get(0);


            System.out.println("Updating With : " + updatedData.get("firstName") + " " + updatedData.get("lastName") + " | " + updatedData.get("email"));


            Response res = RestAssured

                    .given()
                    .baseUri("https://thinking-tester-contact-list.herokuapp.com")
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + token)
                    .body(gson.toJson(updatedData))
                    .when()
                    .put("/contacts/" + ContactToUpdate);

            System.out.println("PUT Status Code : " + res.statusCode());
            System.out.println("PUT response  : " + res.getBody().asString());

            Assert.assertEquals(res.statusCode(), 200);
            Assert.assertEquals(res.jsonPath().getString("firstName"), updatedData.get("firstName"));
            Assert.assertEquals(res.jsonPath().getString("lastName"), updatedData.get("lastName"));
            Assert.assertEquals(res.jsonPath().getString("email"), updatedData.get("email"));
            Assert.assertEquals(res.jsonPath().getString("_id"), ContactToUpdate);

    }

    @Test(dataProvider = "updatedContactDataProvider")
    public void updateContactPatch(Map<String, String> updatedData) {





        System.out.println("Testing with" + updatedData.get("firstName") +" " + updatedData.get("lastName") + " | " + updatedData.get("email"));

        Response resAdd = RestAssured

                .given()
                .baseUri("https://thinking-tester-contact-list.herokuapp.com")
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(gson.toJson(updatedData))
                .when()
                .post("/contacts");

        System.out.println("POST Status Code : " + resAdd.statusCode());




        String contactToPatch = createdContactIds.get(0);

        Map<String, String> patchData = new HashMap<>();
        patchData.put("firstName", faker.name().firstName());
        patchData.put("phone", faker.numerify("##########"));

        System.out.println("Updating with : " + patchData);

        Response res = RestAssured

                .given()
                .baseUri("https://thinking-tester-contact-list.herokuapp.com")
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(gson.toJson(patchData))
                .when()
                .patch("/contacts/" + contactToPatch);

        System.out.println("Patch Status Code : " + res.statusCode());
        System.out.println("Patch response  : " + res.getBody().asString());

        Assert.assertEquals(res.statusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("firstName"), patchData.get("firstName"));
        Assert.assertEquals(res.jsonPath().getString("phone"), patchData.get("phone"));

    }









    }







