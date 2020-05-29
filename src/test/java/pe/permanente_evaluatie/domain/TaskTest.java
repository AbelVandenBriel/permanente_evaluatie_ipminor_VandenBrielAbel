package pe.permanente_evaluatie.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class TaskTest {

    LocalDateTime ldt;
    Task task;
    SubTask subTask;

    @Before
    public void setUp(){
        ldt = LocalDateTime.of(LocalDate.of(2020,04,20), LocalTime.of(14, 30));
        task = new Task("name", "description", ldt);
        subTask = new SubTask("name", "description");
    }

    @Test
    public void Task_create_task_with_valid_parameters(){
        Task task = new Task("name", "description", ldt);
        assertEquals("description", task.getDescription());
        assertEquals(ldt, task.getDueDate());
        assertEquals("name", task.getName());
    }

    @Test (expected = IllegalArgumentException.class)
    public void Task_create_task_with_null_name_throws_error(){
        Task task = new Task(null, "description", ldt);
    }

    @Test (expected = IllegalArgumentException.class)
    public void Task_create_task_with_empty_name_throws_error(){
        Task task = new Task("  ", "description", ldt);
    }

    @Test
    public void setName_with_valid_parameter_sets_name(){
        task.setName("new name");
        assertEquals("new name", task.getName());
    }

    @Test (expected = IllegalArgumentException.class)
    public void setName_with_empty_name_throws_error(){
        task.setName(" ");
    }

    @Test (expected = IllegalArgumentException.class)
    public void setName_with_null_name_throws_error(){
        task.setName(null);
    }

    @Test
    public void setDescription_with_valid_parameter_sets_description(){
        task.setDescription("new description");
        assertEquals("new description", task.getDescription());
    }

    @Test
    public void setDueDate_with_valid_parameter_sets_dueDate(){
        LocalDateTime ldt1 = LocalDateTime.of(LocalDate.of(2021,11,20), LocalTime.of(12, 51));
        task.setDueDate(ldt1);
        assertEquals(ldt1, task.getDueDate());
    }

    @Test
    public void getDueDateFormattedAsString_returns_correct_string(){
        assertEquals("April 20 2020 at 14:30", task.getDueDateAsFormattedString());
    }

    @Test
    public void addSubTask_adds_subtask(){
        assertEquals(0, task.getSubTasks().size());
        task.addSubTask(subTask);
        assertEquals(1, task.getSubTasks().size());
        assertEquals(subTask.getId(), task.getSubTasks().get(0).getId());
    }

}
