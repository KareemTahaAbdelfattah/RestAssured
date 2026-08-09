/** ==============================================**
 ** @Author: Kareem Taha Abd El-Fattah Mohammed
 ** @Category: Route Testing Diploma
 ** @brief: RestASSURED Session 3
/** ==============================================**/
package deserialization;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.instructorCourse.Instructor;

import static io.restassured.RestAssured.given;

public class GetCourses {

    private String accessToken;

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
    }

    @Test
    public void AtokenGenerator(){
        String token = given().formParams(
                "client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com",
                "client_secret", "erZOWM9g3UtwNRj340YYaK_W",
                "grant_type", "client_credentials",
                "scope", "trust"
        ).when().post("oauthapi/oauth2/resourceOwner/token")
                .then().assertThat().statusCode(200)
                .extract().body().asString();

        JsonPath js = new JsonPath(token);
        accessToken = js.getString("access_token").trim();
        System.out.println("Access Token: " + accessToken);
    }

    @Test
    public void BgetCourses(){
        Instructor courseDetails = given().queryParam("access_token", accessToken)
                .when().get("oauthapi/getCourseDetails")
                .then().assertThat().statusCode(401).log().all()
                .extract().response().as(Instructor.class);  //Deserialization

        System.out.println(courseDetails.getLinkedIn());
    }
}