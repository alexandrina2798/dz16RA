import Pets.Category;
import Pets.Pet;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class GetNullPet {

    @Test
    public void test() {
        Pet myPet = new Pet();
        myPet.setId(1312);
        myPet.setName("Pusya");
        Category category = new Category();
        category.setName("Кот");
        myPet.setCategory(category);
        myPet.setStatus("available");

        //удаление питомца на случай, если есть
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(myPet).delete("https://petstore.swagger.io/v2/pet/" + myPet.getId());

        //проверка, что питомца нет - ожидаем ошибку
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(myPet).get("https://petstore.swagger.io/v2/pet/" + myPet.getId()).then().statusCode(404);

    }

}
