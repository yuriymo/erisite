import com.google.gson.Gson;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mmy.camel.Application;
import org.mmy.camel.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CamelTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void addPlayer() throws Exception {
        String contentAsString = mvc.perform(MockMvcRequestBuilders.post("/camel/api/bean")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(MyBean.builder().id(1).name("World").build()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        MyBean world = new Gson().fromJson(contentAsString, MyBean.class);
        if (!world.equals(MyBean.builder().id(10).name("Hello, World").build())) {
            Assertions.fail("addPlayer - fail");
        }
    }
}
