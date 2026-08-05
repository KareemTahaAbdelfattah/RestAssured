/** ==============================================**
 ** @Author: Kareem Taha Abd El-Fattah Mohammed
 ** @Category: Route Testing Diploma
 ** @brief: RestASSURED Session 2
/** ==============================================**/
package library;

import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static PayLoad.LibraryPayLoad.AddBook;

public class DynamicJson {

    @Test(dataProvider = "BookData")
    public void TestDynamicJson(String isbn, String aisle) {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        String respone = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(AddBook(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        System.out.println("Response: " + respone);
    }

    @DataProvider(name = "BookData")
    public Object[][] getData() {
        return new Object[][]{
                {"Kareem", "001"},
                {"Mohammed", "002"},
                {"Aya", "003"}
        };
    }
}