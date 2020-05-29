package pe.permanente_evaluatie.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pe.permanente_evaluatie.domain.SubTask;
import pe.permanente_evaluatie.domain.Task;
import pe.permanente_evaluatie.domain.TaskDTO;
import pe.permanente_evaluatie.domain.Utils;
import pe.permanente_evaluatie.repository.TaskRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class TaskServiceImpTest {

    @Autowired
    private TaskService taskService;

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
        ldt1 = LocalDateTime.of(LocalDate.of(2021,01,20), LocalTime.of(11, 10));
        ldt2 = LocalDateTime.of(LocalDate.of(2022,02,20), LocalTime.of(12, 20));
        ldt3 = LocalDateTime.of(LocalDate.of(2023,03,20), LocalTime.of(13, 30));

        task1 = new Task("task1", "task1 description", ldt1);
        task2 = new Task("task2", "task2 description", ldt2);
        task3 = new Task("task3", "task3 description", ldt3);

        subTask1 = new SubTask("subTask1", "subTask1 description");
        subTask2 = new SubTask("subTask2", "subTask2 description");
    }

    @Test
    public void addTask_adds_task(){
        assertEquals(0, taskService.getTasks().size());

        taskService.addTask(task1);
        assertEquals(1, taskService.getTasks().size());

        taskService.addTask(task2);
        assertEquals(2, taskService.getTasks().size());
    }

    @Test
    public void getTasks_returns_list_of_tasks(){
        taskService.addTask(task1);
        taskService.addTask(task2);

        assertEquals(2, taskService.getTasks().size());
    }

    @Test
    public void getTask_returns_task(){
        taskService.addTask(task1);
        taskService.addTask(task2);
        taskService.addTask(task3);

        Task t = taskService.getTasks().get(0);
        Task t1 = taskService.getTasks().get(1);
        Task t2 = taskService.getTasks().get(2);


        assertEquals(t2.getId(), taskService.getTask(t2.getId().toString()).getId());
        assertEquals(t.getId(), taskService.getTask(t.getId().toString()).getId());
        assertNotEquals(t.getId(), taskService.getTask(t1.getId().toString()).getId());
    }

    @Test
    public void deleteTask_deletes_a_task(){
        assertEquals(0, taskService.getTasks().size());

        taskService.addTask(task1);
        assertEquals(1, taskService.getTasks().size());

        Task t = taskService.getTasks().get(0);
        taskService.removeTask(t.getId().toString());
        assertEquals(0, taskService.getTasks().size());
    }

    @Test
    public void addSubTask_adds_subtask(){
        assertEquals(0, taskService.getTasks().size());

        taskService.addTask(task1);
        assertEquals(1, taskService.getTasks().size());

        Task t = taskService.getTasks().get(0);
        assertEquals(0, taskService.getTask(t.getId().toString()).getSubTasks().size());

        taskService.addSubtask(t.getId().toString(), subTask1);
        assertEquals(1, taskService.getTask(t.getId().toString()).getSubTasks().size());
    }

    @Test
    public void editTask_edits_task(){
        assertEquals(0, taskService.getTasks().size());

        taskService.addTask(task1);
        TaskDTO taskDTO = Utils.taskToDTO(task1);
        taskDTO.setId(taskService.getTasks().get(0).getId());
        assertEquals(taskDTO.getName(), taskService.getTask(task1.getId().toString()).getName());
        assertEquals(taskDTO.getDescription(), taskService.getTask(task1.getId().toString()).getDescription());
        assertEquals(taskDTO.getDueDate(), taskService.getTask(task1.getId().toString()).getDueDate());
        taskDTO.setName("updated");
        assertNotEquals("updated", taskService.getTask(task1.getId().toString()).getName());
        taskService.editTask(Utils.DTOToTask(taskDTO));
        assertEquals("updated", taskService.getTask(task1.getId().toString()).getName());
    }


}
