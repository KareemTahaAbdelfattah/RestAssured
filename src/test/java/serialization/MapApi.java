/** ==============================================**
 ** @Author: Kareem Taha Abd El-Fattah Mohammed
 ** @Category: Route Testing Diploma
 ** @brief: RestASSURED Session 2
/** ==============================================**/
package serialization;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.maps.Location;
import pojo.maps.PlaceDetails;

public class MapApi {

    PlaceDetails placeDetails;
    Location location;

    @BeforeMethod
    public void setUp()
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        placeDetails = new PlaceDetails();
        location = new Location();
    }

    @Test
    public void testMapApi()
    {
        // Add Place
        placeDetails.setAccuracy(50);
        placeDetails.setAddress("29, side layout, cohen 09");
        placeDetails.setLanguage("French-IN");
        placeDetails.setName("Frontline house");
        placeDetails.setPhoneNumber("(+91) 983 893 3937");
        placeDetails.setWebsite("http://google.com");
        location.setLatitude(-38.383494);
        location.setLongitude(33.427362);
        placeDetails.setLocation(location);
        placeDetails.setTypes(java.util.Arrays.asList("shoe park", "shop"));

        String addPlace = RestAssured.given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(placeDetails)
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        System.out.println("Add Place Response: " + addPlace);
    }
}