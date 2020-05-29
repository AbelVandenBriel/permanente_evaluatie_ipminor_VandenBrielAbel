package pe.permanente_evaluatie.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;


public class UtilsTest {

    Task task;
    LocalDateTime ldt;
    SubTask subTask1;
    SubTask subTask2;
    TaskDTO taskDTO;
    SubTaskDTO subTaskDTO1;
    SubTaskDTO subTaskDTO2;


    @Before
    public void setUp(){
        ldt = LocalDateTime.of(LocalDate.of(2020,04,20), LocalTime.of(14, 30));
        task = new Task("name", "description",ldt);
        task.setId(UUID.randomUUID());
        subTask1 = new SubTask("subTask1", "subTask1 description");
        subTask1.setId(UUID.randomUUID());
        subTask2 = new SubTask("subTask2", "subTask2 description");
        subTask2.setId(UUID.randomUUID());
    }

    @Test
    public void taskToDTO_converts_task_with_no_subtasks_correctly(){
        TaskDTO taskDTO = Utils.taskToDTO(task);
        assertEquals(task.getName(), taskDTO.getName());
        assertEquals(task.getDueDate(), taskDTO.getDueDate());
        assertEquals(task.getDescription(), taskDTO.getDescription());
        assertEquals(task.getId(), taskDTO.getId());
        assertEquals(task.getSubTasks().size(), taskDTO.getSubTasks().size());
    }

    @Test
    public void taskToDTO_converts_task_with_subtasks_correctly(){
        task.addSubTask(subTask1);
        task.addSubTask(subTask2);
        assertEquals(2, task.getSubTasks().size());
        TaskDTO taskDTO = Utils.taskToDTO(task);
        assertEquals(task.getName(), taskDTO.getName());
        assertEquals(task.getDueDate(), taskDTO.getDueDate());
        assertEquals(task.getDescription(), taskDTO.getDescription());
        assertEquals(task.getId(), taskDTO.getId());
        assertEquals(2, taskDTO.getSubTasks().size());
        assertEquals(task.getSubTasks().get(0).getId(), taskDTO.getSubTasks().get(0).getId());
        assertEquals(task.getSubTasks().get(1).getId(), taskDTO.getSubTasks().get(1).getId());
    }

    @Test
    public void DTOToTask_converts_taskdto_with_no_subtasks_correctly(){
        taskDTO = Utils.taskToDTO(task);
        Task task1 = Utils.DTOToTask(taskDTO);
        assertEquals(taskDTO.getName(), task1.getName());
        assertEquals(taskDTO.getDueDate(), task1.getDueDate());
        assertEquals(taskDTO.getDescription(), task1.getDescription());
        assertEquals(taskDTO.getId(), task1.getId());
        assertEquals(taskDTO.getSubTasks().size(), task1.getSubTasks().size());
    }

    @Test
    public void DTOToTask_converts_taskdto_with_subtasks_correctly(){
        task.addSubTask(subTask1);
        task.addSubTask(subTask2);
        assertEquals(2, task.getSubTasks().size());
        taskDTO = Utils.taskToDTO(task);
        Task task1 = Utils.DTOToTask(taskDTO);
        assertEquals(taskDTO.getName(), task1.getName());
        assertEquals(taskDTO.getDueDate(), task1.getDueDate());
        assertEquals(taskDTO.getDescription(), task1.getDescription());
        assertEquals(taskDTO.getId(), task1.getId());
        assertEquals(2, task1.getSubTasks().size());
        assertEquals(taskDTO.getSubTasks().get(0).getId(), task1.getSubTasks().get(0).getId());
        assertEquals(taskDTO.getSubTasks().get(1).getId(), task1.getSubTasks().get(1).getId());
    }

    @Test
    public void subTaskToDTO_converts_subTask_correctly(){
        SubTaskDTO subTaskDTO = Utils.subTaskToDTO(subTask1);

        assertEquals(subTask1.getId(), subTaskDTO.getId());
        assertEquals(subTask1.getName(), subTaskDTO.getName());
        assertEquals(subTask1.getDescription(), subTaskDTO.getDescription());
    }

    @Test
    public void DTOToSubTask_converts_subTaskDTO_correctly(){
        subTaskDTO1 = Utils.subTaskToDTO(subTask1);
        SubTask subTask = Utils.DTOToSubTask(subTaskDTO1);

        assertEquals(subTaskDTO1.getId(), subTask.getId());
        assertEquals(subTaskDTO1.getName(), subTask.getName());
        assertEquals(subTaskDTO1.getDescription(), subTask.getDescription());
    }


}
