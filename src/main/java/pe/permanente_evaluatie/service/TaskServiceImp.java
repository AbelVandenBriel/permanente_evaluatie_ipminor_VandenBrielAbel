package pe.permanente_evaluatie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pe.permanente_evaluatie.domain.SubTask;
import pe.permanente_evaluatie.domain.Task;
import pe.permanente_evaluatie.domain.TaskDTO;
import pe.permanente_evaluatie.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImp implements TaskService{

    @Autowired
    private TaskRepository repository;

    @Override
    public List<Task> getTasks() {
        return repository.findAll();
    }

    @Override
    public void addTask(Task task){
        repository.save(task);
    }

    @Override
    public Task getTask(String id) {
        UUID uuid = UUID.fromString(id);
        return repository.findById(uuid).orElse(null);
    }

    @Override
    public void removeTask(String id) {
        repository.delete(getTask(id));
    }

    @Override
    public void addSubtask(String id, SubTask task){
        Task parentTask = getTask(id);
        parentTask.addSubTask(task);
        repository.save(parentTask);
    }

    @Override
    public void editTask(Task task){
        Task toEdit = getTask(task.getId().toString());
        toEdit.setName(task.getName());
        toEdit.setDescription(task.getDescription());
        toEdit.setDueDate(task.getDueDate());
    }
}
