package pe.permanente_evaluatie.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pe.permanente_evaluatie.domain.SubTask;
import pe.permanente_evaluatie.domain.Task;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    TaskRepository repo;

    private Task task1;
    private LocalDateTime ldt1;
    private Task task2;
    private LocalDateTime ldt2;
    private Task task3;
    private LocalDateTime ldt3;

    private SubTask subTask1;
    private SubTask subTask2;

    @Before
    public void setUp(){
        ldt1 = LocalDateTime.of(LocalDate.of(2021,1,20), LocalTime.of(11, 10));
        ldt2 = LocalDateTime.of(LocalDate.of(2022,2,20), LocalTime.of(12, 20));
        ldt3 = LocalDateTime.of(LocalDate.of(2023,3,20), LocalTime.of(13, 30));

        task1 = new Task("task1", "task1 description", ldt1);
        task2 = new Task("task2", "task2 description", ldt2);
        task3 = new Task("task3", "task3 description", ldt3);

        subTask1 = new SubTask("subTask1", "subTask1 description");
        subTask2 = new SubTask("subTask2", "subTask2 description");
    }

    @Test
    public void findAll_returns_list_of_tasks(){
        assertEquals(0, repo.findAll().size());

        repo.save(task1);
        repo.save(task2);

        assertEquals(2, repo.findAll().size());
    }

    @Test
    public void save_saves_task_to_repo(){
        assertEquals(0, repo.findAll().size());

        repo.save(task1);
        assertEquals(1, repo.findAll().size());

        repo.save(task2);
        assertEquals(2, repo.findAll().size());
    }

    @Test
    public void findById_returns_task(){
        repo.save(task1);
        repo.save(task2);
        repo.save(task3);

        Task t = repo.findAll().get(0);
        Task t1 = repo.findAll().get(1);
        Task t2 = repo.findAll().get(2);

        Task tt = repo.findById(t.getId()).orElse(null);
        Task tt2 = repo.findById(t2.getId()).orElse(null);

        assertEquals(t.getId(), tt.getId());
        assertEquals(t2.getId(), tt2.getId());
        assertNotEquals(t1.getId(), tt.getId());
    }

    @Test
    public void delete_removes_task(){
        assertEquals(0, repo.findAll().size());

        repo.save(task1);
        assertEquals(1, repo.findAll().size());

        Task t = repo.findAll().get(0);
        repo.delete(t);
        assertEquals(0, repo.findAll().size());
    }
}
