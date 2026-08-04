/** ==============================================**
 ** @Author: Kareem Taha Abd El-Fattah Mohammed
 ** @Category: Route Testing Diploma
 ** @brief: RestASSURED
/** ==============================================**/
package mapsPayLoad;

import io.restassured.path.json.JsonPath;

public class mapsPayLoad
{
    public static String updatePlaceRequestBody(String place_id, String newAddress)
    {
        return "{\n" +
                "\"place_id\":\"" + place_id + "\",\n" +
                "\"address\":\"" + newAddress + "\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}\n";
    }
}