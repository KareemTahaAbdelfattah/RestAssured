/** ==============================================**
 ** @Author: Kareem Taha Abd El-Fattah Mohammed
 ** @Category: Route Testing Diploma
 ** @brief: RestASSURED
/** ==============================================**/
package maps;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static mapsPayLoad.mapsPayLoad.updatePlaceRequestBody;
import static org.hamcrest.Matchers.equalTo;

public class MapsRequest
{
    String keyValue = "qaclick123";
    String newAddress = "10 street of Obour city, Egypt";

    @Test
    public void testMapsAPI(){
        File jsonFile = new File("src/test/resources/testData/addPlace.json");
        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        // add place request
        String addPlaceResponse = given()
                .queryParam("key", keyValue)
                .header("Content-Type", "application/json")
                .body(jsonFile)
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .extract().response().asString();

        System.out.println("Add Place Response: " + addPlaceResponse);

        JsonPath jsonPath = new JsonPath(addPlaceResponse);
        String placeid = jsonPath.getString("place_id");
        System.out.println("Place ID: " + placeid);

        // update place request
        String getUpdatePlaceResponse = given()
                .queryParam("key", keyValue)
                .queryParam("place_id", placeid)
                .header("Content-Type", "application/json")
                .body(updatePlaceRequestBody(placeid, newAddress))
                .when().put("maps/api/place/update/json")
                .then().assertThat().statusCode(200)
                .body("msg", equalTo("Address successfully updated"))
                .extract().response().asString();

        System.out.println("Update Place Response: " + getUpdatePlaceResponse);

        // get place to verify
        given()
                .queryParam("key", keyValue)
                .queryParam("place_id", placeid)
                .header("Content-Type", "application/json")
                .when().get("maps/api/place/get/json")
                .then().assertThat().statusCode(200)
                .body("address", equalTo(newAddress))
                .log().body();
    }
}