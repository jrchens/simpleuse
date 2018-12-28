package mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import mybatis.mappers.User;
import mybatis.mappers.UserMapper;
import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserMapperTestCase {
    private static final Logger logger = LoggerFactory.getLogger(UserMapperTestCase.class);

    private static ApplicationContext applicationContext;

//    @Before is executed before each test
//    @Before
    private static Long id;

    @BeforeClass
    public static void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext-test.xml");
    }

    @Test
    public void test1insert() throws Exception {
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);

        User user = new User();
        for (int i = 1; i < 6; i++) {
            user.setUsername("admin");
            user.setViewname("Admininstrator");
            user.setPassword("admin");
            user.setDeleted(false);
            user.setCruser("system");
            user.setCrtime(DateTime.now().toDate());
            int aff = userMapper.insert(user);

            logger.info("aff: {}", aff);
            logger.info("id: {}", user.getId());

        }
        this.id
                = user.getId();
    }

    @Test
    public void test2delete() throws Exception {
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        int aff = userMapper.deleteByPrimaryKey(this.id);
        logger.info("deleted aff: {}", aff);
    }

    @Test
    public void test3update() throws Exception {
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);

        User user = userMapper.selectByPrimaryKey(this.id - 2);
        user.setMduser("system");
        user.setMdtime(DateTime.now().toDate());

        int aff = userMapper.updateByPrimaryKey(user);

        logger.info("update aff:  {}", aff);
        logger.info("user: {}", user.toString());
    }


    @Test
    public void test4select() throws Exception {
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);

        User user = userMapper.selectByPrimaryKey(this.id - 1);
        logger.info("select user by id: {}", this.id - 1);
        logger.info("user: {}", user.toString());
    }


    @Test
    public void test5selectByUsernameAndViewname() throws Exception {
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        User user = new User();
        user.setUsername("1");
        user.setViewname("2");
        Page<User> page = PageHelper.startPage(1, 5).setOrderBy("mdtime desc,id asc");
        userMapper.selectByUsernameAndViewname(user);

        logger.info("total.size: {}", page.getTotal());
        logger.info("users.size: {}", page.size());
        logger.info("pages: {}", page.getPages());
    }
}
