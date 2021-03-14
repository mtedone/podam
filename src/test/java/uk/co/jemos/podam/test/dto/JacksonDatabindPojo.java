/**
 *
 */
package uk.co.jemos.podam.test.dto;

import java.util.List;

import org.openapitools.jackson.nullable.JsonNullable;

/**
 * POJO to test jackson-databind-nullable
 *
 * @author daivanov
 *
 */
public class JacksonDatabindPojo {
    private JsonNullable<List<String>> stringList = JsonNullable.<List<String>>undefined();

    public JacksonDatabindPojo(List<String> stringList) {
      this.stringList = JsonNullable.of(stringList);
    }

    public List<String> getStringList() {
        return stringList.orElse(null);
    }

    public void setStringList(List<String> stringList) {
        this.stringList = JsonNullable.<List<String>>of(stringList);
    }
}
