package net.petrikainulainen.spring.trenches.todo.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import net.petrikainulainen.spring.trenches.config.PersistenceContext;
import net.petrikainulainen.spring.trenches.todo.model.Todo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Petri Kainulainen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
//@ContextConfiguration(locations = {"classpath:exampleApplicationContext-persistence.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = ColumnSensingReplacementDataSetLoader.class)
public class ITTodoRepositoryTest {

    private static final Long ID = 2L;
    private static final String DESCRIPTION = "description";
    private static final String TITLE = "title";
    private static final long VERSION = 0L;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TodoRepository repository;

    @Before
    public void setUp() throws SQLException {
        DbTestUtil.invokeSql(applicationContext, "todos");
    }

    @Test
    @DatabaseSetup("todo-entries.xml")
    public void findByDescription_ShouldReturnOneTodoEntry() {
        List<Todo> todoEntries = repository.findByDescription(DESCRIPTION);
        assertThat(todoEntries).hasSize(1);

        Todo found = todoEntries.get(0);
        assertThat(found.getId()).isEqualTo(ID);
        assertThat(found.getTitle()).isEqualTo(TITLE);
        assertThat(found.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(found.getVersion()).isEqualTo(VERSION);
    }

    @Test
    @DatabaseSetup("no-todo-entries.xml")
    @ExpectedDatabase("save-todo-entry-with-title-and-description-expected.xml")
    public void save_WithTitleAndDescription_ShouldSaveTodoEntryToDatabase() {
        Todo todoEntry = Todo.getBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .build();

        repository.save(todoEntry);
    }

    @Test
    @DatabaseSetup("no-todo-entries.xml")
    @ExpectedDatabase("save-todo-entry-without-description-expected.xml")
    public void save_WithoutDescription_ShouldSaveTodoEntryToDatabase() {
        Todo todoEntry = Todo.getBuilder()
                .title(TITLE)
                .description(null)
                .build();

        repository.save(todoEntry);
    }
}
