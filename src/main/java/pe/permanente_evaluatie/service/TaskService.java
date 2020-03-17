package pe.permanente_evaluatie.service;

import pe.permanente_evaluatie.domain.SubTask;
import pe.permanente_evaluatie.domain.Task;
import pe.permanente_evaluatie.domain.TaskDTO;

import java.util.List;

public interface TaskService {

    List<Task> getTasks();
    void addTask(Task task);
    Task getTask(String id);
    void removeTask(String id);
    void addSubtask(String id, SubTask task);
    void editTask(Task task);
}
