/** ==============================================**
 ** @Author: Kareem Taha Abd El-Fattah Mohammed
 ** @Category: Route Testing Diploma
 ** @brief: RestASSURED
/** ==============================================**/
package commons;

import io.restassured.path.json.JsonPath;

public class Utils {

    // This method take a raw json response as a string and convert it into a JsonPath object for easy querying
    public static JsonPath rawToJsonPath(String response)
    {
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;
    }
}