package com.framework_Page.Utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    private static final String BASE_URL = "https://thinking-tester-contact-list.herokuapp.com";
    private String token;

    // ── Auth ──────────────────────────────────────────
    public Response login(String email, String password) {
        Response response = RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}")
                .when()
                .post("/users/login");

        if (response.statusCode() == 200) {
            this.token = response.jsonPath().getString("token");
        }
        return response;
    }

    public String getToken() {
        return token;
    }

    // ── Reusable authenticated request spec ───────────
    public RequestSpecification authRequest() {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token);
    }

    // ── Contacts ──────────────────────────────────────
    public Response getContacts() {
        return authRequest().when().get("/contacts");
    }

    public Response addContact(String body) {
        return authRequest().body(body).when().post("/contacts");
    }

    public Response getContactById(String id) {
        return authRequest().when().get("/contacts/" + id);
    }

    public Response updateContact(String id, String body) {
        return authRequest().body(body).when().put("/contacts/" + id);
    }

    public Response patchContact(String id, String body) {
        return authRequest().body(body).when().patch("/contacts/" + id);
    }

    public Response deleteContact(String id) {
        return authRequest().when().delete("/contacts/" + id);

    }


}
