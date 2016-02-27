import dev.codder.configuration.TODOSpringConfiguration;
import dev.codder.configuration.TestConfiguration;
import dev.codder.configuration.WebConfiguration;
import dev.codder.models.TODOModel;
import dev.codder.services.TODOStorageService;
import dev.codder.services.TODOStorageServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by Daniil on 2/27/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class, TODOSpringConfiguration.class, WebConfiguration.class})
@WebAppConfiguration
public class TODODServiceTest {

    @Autowired
    private TODOStorageService todoStorage;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp() {
        Mockito.reset(todoStorage);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void saveTest() throws Exception {
        List<TODOModel> tasks = todoStorage.getAll();
        int beforeSize = tasks.size();
        TODOModel task = todoStorage.save(new TODOModel(false, "Hello"));
        Assert.assertNotNull(task);
        Assert.assertEquals(beforeSize + 1, todoStorage.getAll().size());
    }

    @Test
    public void deleteTest() throws Exception {

    }




}
