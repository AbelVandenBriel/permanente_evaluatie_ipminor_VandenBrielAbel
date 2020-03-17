package pe.permanente_evaluatie.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.permanente_evaluatie.domain.*;
import pe.permanente_evaluatie.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public String getTasks(Model model){
        model.addAttribute("tasks", ObjectMapperUtils.mapAll(taskService.getTasks(), TaskDTO.class));
        return "tasks";
    }

    @GetMapping("/new")
    public String getAddTaskForm(){
        return "taskForm";
    }

    @PostMapping("/createTask")
    public String createTask(@ModelAttribute TaskDTO taskDTO){
        Task task = ObjectMapperUtils.map(taskDTO, Task.class);
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String getDescription(Model model, @PathVariable("id") String id){
        model.addAttribute("task", taskService.getTask(id));
        return "taskDescription";
    }

    @GetMapping("/{id}/sub/create")
    public String getAddSubTaskForm(Model model, @PathVariable("id") String id){
        model.addAttribute("id", id);
        return "createSubTask";
    }

    @PostMapping("/createSubTask/{parentId}")
    public String createSubTask(@PathVariable("parentId") String parentId, @ModelAttribute SubTaskDTO subTaskDTO){
        SubTask subTask = ObjectMapperUtils.map(subTaskDTO, SubTask.class);
        taskService.addSubtask(parentId, subTask);
        return "redirect:/tasks/" + parentId;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable("id") String id){
        model.addAttribute("task", taskService.getTask(id));
        return "editTask";
    }

    @PostMapping("/editTask")
    public String editTask(@ModelAttribute TaskDTO taskDTO){
        Task task = ObjectMapperUtils.map(taskDTO, Task.class);
        taskService.editTask(task);
        return "redirect:/tasks/" + task.getId();
    }
}
