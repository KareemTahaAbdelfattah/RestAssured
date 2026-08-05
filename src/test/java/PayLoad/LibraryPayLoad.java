/** ==============================================**
 ** @Author: Kareem Taha Abd El-Fattah Mohammed
 ** @Category: Route Testing Diploma
 ** @brief: RestASSURED Session 2
/** ==============================================**/
package PayLoad;

public class LibraryPayLoad {

    public static String AddBook(String isbn, String aisle) {
        String payload = "{\n" +
                "  \"name\": \"Learn Appium Automation with Java\",\n" +
                "  \"isbn\": \"" + isbn + "\",\n" +
                "  \"aisle\": \"" + aisle + "\",\n" +
                "  \"author\": \"John foe\"\n" +
                "}";
        return payload;
    }
}