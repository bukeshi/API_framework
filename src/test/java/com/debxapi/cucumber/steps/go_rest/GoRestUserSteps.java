package com.debxapi.cucumber.steps.go_rest;

import com.debxapi.pojos.go_rest_pojos.User;
import com.debxapi.pojos.go_rest_pojos.UserResponseList;
import com.debxapi.pojos.go_rest_pojos.UserResponseObject;
import com.debxapi.utilities.ObjectConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.Arrays;
import java.util.List;

public class GoRestUserSteps {
    private Response response;
    private int userID;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://gorest.co.in/public-api";
    }

    @When("^all users are requested$")
    public void all_users_are_requested() {
        RequestSpecification rs = RestAssured.given();
        rs.accept(ContentType.JSON);

        response = rs.get("/users");
    }

    @Then("^status code (\\d+) is returned$")
    public void status_code_is_returned(int expectedStatusCode) {

        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(expectedStatusCode));
    }

    @Then("^(\\d+) users are returned$")
    public void users_are_returned(int expectedNumberOfUsers) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserResponseList userList = ObjectConverter.convertJsonObjectToJavaObject(response.getBody().asString(), UserResponseList.class);
        MatcherAssert.assertThat(userList.getUserList().size(), Matchers.equalTo(expectedNumberOfUsers));
    }

    @When("^following user is created$")
    public void following_user_is_created(List<User> userRequested) {
        RequestSpecification rs = RestAssured.given();
        rs.headers("Authorization", "Bearer 25036c6992464cd93b455d4d88827efb15d1b28858d01ad03ad7b8c3c2260b3f");
        rs.contentType(ContentType.JSON);
        rs.accept(ContentType.JSON);
        rs.body(userRequested.get(0));

        response = rs.post("/users");
        response.prettyPrint();
        userID = response.getBody().jsonPath().get("data.id");
    }

    @When("^following userID (\\d+) is requested$")
    public void following_userID_is_requested(int existingUserID) {
        RequestSpecification rs = RestAssured.given();
        rs.accept(ContentType.JSON);

        response = rs.pathParam("id", existingUserID).get("/users/{id}");
    }

    @Then("^following user response is returned$")
    public void following_user_response_is_returned(List<User> expectedUserResponse) throws JsonProcessingException {
        UserResponseObject actualUser = ObjectConverter.convertJsonObjectToJavaObject(response.body().asString(), UserResponseObject.class);

        MatcherAssert.assertThat(actualUser.getUser().getName(), Matchers.equalTo(expectedUserResponse.get(0).getName()));
        MatcherAssert.assertThat(actualUser.getUser().getEmail(), Matchers.equalTo(expectedUserResponse.get(0).getEmail()));
        MatcherAssert.assertThat(actualUser.getUser().getGender(), Matchers.equalTo(expectedUserResponse.get(0).getGender()));
        MatcherAssert.assertThat(actualUser.getUser().getStatus(), Matchers.equalTo(expectedUserResponse.get(0).getStatus()));
        response.prettyPrint();
    }

    @When("^the user is deleted$")
    public void the_user_is_deleted() throws Throwable {
        RequestSpecification rs = RestAssured.given();
        rs.headers("Authorization","Bearer 25036c6992464cd93b455d4d88827efb15d1b28858d01ad03ad7b8c3c2260b3f");
        rs.accept(ContentType.JSON);

        response = rs
                .pathParam("id",userID)
                .delete("/users/{id}");
    }

    @When("^the user is requested$")
    public void the_user_is_requested() throws Throwable {
        RequestSpecification rs = RestAssured.given();
        rs.accept(ContentType.JSON);

        response = rs.pathParam("id",userID).get("/users/{id}");
    }
}
