package br.com.financeiro.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = { EmbeddedMongoAutoConfiguration.class })
public class MongoConfig{
}
/*

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTests {

    @Autowired
    UserRepository dao;

    @Before
    public void setUp() {

        User user = new User();

        user.setName("test");
        dao.save(user);
    }

    @Test
    public void findByName() {
        List<User> result = dao.findByName("test");
        assertThat(result).hasSize(1).extracting("name").contains("test");
    }

}
 */