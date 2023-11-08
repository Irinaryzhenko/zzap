package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.when;


public class BaseTest {
    @Test
    public void checkStatusCode() {
        when().get("https://zzap.vw-brest.by/")
                .then().log().status()
                .assertThat().statusCode(200);
    }

    @Test
    public void checkLoginWithAnyPasswordAnyLogin() {
        Response responseBody = RestAssured
                .given()
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                .formParam("login", "dsd@fdf.gg1")
                .formParam("pass", "sdsdsdsds")
                .when()
                .post("https://zzap.vw-brest.by/");
        String response = responseBody.getBody().asString();
        Assertions.assertTrue(response.contains("Неверный логин/пароль"));
        Assertions.assertEquals(200, responseBody.getStatusCode());

    }

    @Test
    public void checkLoginWithEmptyPasswordAnyLogin() {
        Response responseBody = RestAssured
                .given()
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                .formParam("login", "dsd@fdf.gg1")
                .formParam("pass", "")
                .when()
                .post("https://zzap.vw-brest.by/");
        String response = responseBody.getBody().asString();
        Assertions.assertTrue(response.contains("Неверный логин/пароль"));
        Assertions.assertEquals(200, responseBody.getStatusCode());


//    @Test
//    public void searchQuery() {
//        Response response = RestAssured
//                .given().header("User-Agent", "PostmanRuntime/7.34.0")
//                .when().get("https://zzap.vw-brest.by/search/?pcode=osram2825");
//        String responseBody = response.getBody().asPrettyString();
//        Assertions.assertTrue(responseBody.contains("osram2825"));
//    }
    }
        @Test
        public void searchQuery () {
            String searchItem = "osram2825";
            Response response = RestAssured
                    .given().header("User-Agent", "PostmanRuntime/7.34.0")
                    .queryParam("pcode", searchItem)
                    .when().get("https://zzap.vw-brest.by/search/");
            String responseBody = response.getBody().asPrettyString();
            Assertions.assertTrue(responseBody.contains(searchItem), searchItem + " not found");
            Assertions.assertEquals(200, response.getStatusCode());
        }
    }

