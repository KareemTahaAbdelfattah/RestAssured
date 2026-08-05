/** ==============================================**
 ** @Author: Kareem Taha Abd El-Fattah Mohammed
 ** @Category: Route Testing Diploma
 ** @brief: RestASSURED Session 2
/** ==============================================**/
package courses;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static PayLoad.CoursesPayLoad.coursesPayLoad;
import static commons.Utils.rawToJsonPath;
import static org.testng.Assert.assertEquals;

public class Complex {

    JsonPath js = rawToJsonPath(coursesPayLoad());

    @Test
    public void complexJsonTest()
    {
        // 1- print number of courses returned by API
        int coursesCount = js.getInt("courses.size()");
        System.out.println("Number of courses: " + coursesCount);
        assertEquals(coursesCount, 3, "Number of courses is not as expected");

        // 2- print purchase amount
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase amount: " + purchaseAmount);
        assertEquals(purchaseAmount, 910, "Purchase amount is not as expected");

        // 3- print title of the first course
        String firstCourseTitle = js.getString("courses[0].title");
        System.out.println("Title of the first course: " + firstCourseTitle);
        assertEquals(firstCourseTitle, "Selenium Python", "First course title is not as expected");

        // 4- print all course titles and their respective prices
        System.out.println("Course titles and their respective prices:");
        for (int i = 0; i < coursesCount; i++) {
            String courseTitle = js.getString("courses[" + i + "].title");
            int coursePrice = js.getInt("courses[" + i + "].price");
            System.out.println(courseTitle + ": " + coursePrice);
        }
        assertEquals(coursesCount, 3, "Number of courses is not as expected");

        // 5- print number of copies sold by RPA course
        for (int i = 0; i < coursesCount; i++) {
            String courseTitle = js.getString("courses[" + i + "].title");
            if (courseTitle.equalsIgnoreCase("RPA")) {
                int copiesSold = js.getInt("courses[" + i + "].copies");
                System.out.println("Number of copies sold by RPA course: " + copiesSold);
                assertEquals(copiesSold, 10, "Number of copies sold by RPA course is not as expected");
                break;
            }
        }

        // 6- verify if sum of all course prices matches the purchase amount
        int sumOfPrices = 0;
        for (int i = 0; i < coursesCount; i++) {
            int coursePrice = js.getInt("courses[" + i + "].price");
            int coursesSold = js.getInt("courses[" + i + "].copies");
            sumOfPrices += (coursePrice * coursesSold);
        }
        System.out.println("Sum of all course prices: " + sumOfPrices);
        assertEquals(sumOfPrices, purchaseAmount, "Sum of course prices does not match purchase amount");
    }
}