import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.codder.configuration.TODOSpringConfiguration;
import dev.codder.configuration.TestConfiguration;
import dev.codder.configuration.WebConfiguration;
import dev.codder.models.TODOModel;
import dev.codder.services.TODOStorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Daniil on 2/27/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class, TODOSpringConfiguration.class, WebConfiguration.class})
@WebAppConfiguration
public class TODOControllerTest {

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    @Autowired
    private TODOStorageService todoStorageMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private List<TODOModel> tasks;

    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        Mockito.reset(todoStorageMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        TODOModel first = new TODOModel(true, "Hello");
        first.setId(1L);

        TODOModel second = new TODOModel(false, "World");
        second.setId(2L);
        tasks = Arrays.asList(first, second);

        when(todoStorageMock.getAll()).thenReturn(tasks);
        when(todoStorageMock.getById(1L)).thenReturn(first);
        when(todoStorageMock.save(null)).thenReturn(first);

    }

    @Test
    public void taskNotFound() throws Exception {
        mockMvc.perform(get("/api/todos/-1")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }
    @Test
    public void getAllTest() throws Exception {

        mockMvc.perform(get("/api/todos")
                .contentType(contentType)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Hello")))
                .andExpect(jsonPath("$[0].completed", is(true)));

    }


    @Test
    public void getByIdTest() throws Exception {

        mockMvc.perform(get("/api/todos/1")
                .contentType(contentType)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Hello")))
                .andExpect(jsonPath("$.completed", is(true)));

    }

    @Test
    public void createTaskTest() throws Exception {

        TODOModel task = new TODOModel(true, "Bla bla bla");
        String bookmarkJson = json(task);
        this.mockMvc.perform(post("/api/todos")
                .contentType(contentType)
                .content(bookmarkJson))
                .andExpect(status().isCreated());
    }

    protected String json(Object o)  {
        try {
            String result = mapper.writeValueAsString(o);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
