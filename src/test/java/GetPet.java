import Pets.Category;
import Pets.Pet;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class GetPet {

    @Test
    public void test() {

        Pet myPet = new Pet();
        myPet.setId(1312);
        myPet.setName("Pusya");
        Category category = new Category();
        category.setName("Кот");
        myPet.setCategory(category);
        myPet.setStatus("available");

        //создание питомца
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(myPet).post("https://petstore.swagger.io/v2/pet").then().statusCode(200);

        //проверка корректного создания - питомец есть
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(myPet).get("https://petstore.swagger.io/v2/pet/" + myPet.getId()).then().statusCode(200);

    }
}
