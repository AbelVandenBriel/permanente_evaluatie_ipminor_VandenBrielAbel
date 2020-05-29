package pe.permanente_evaluatie.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TaskDTOTest {

    LocalDateTime ldt;
    TaskDTO task;
    SubTaskDTO subTask;

    @Before
    public void setUp(){
        ldt = LocalDateTime.of(LocalDate.of(2020,04,20), LocalTime.of(14, 30));
        task = new TaskDTO();
        subTask = new SubTaskDTO();
        subTask.setId(UUID.randomUUID());
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
        task.setDueDate(ldt);
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
