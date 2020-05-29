package pe.permanente_evaluatie.domain;

public class Utils {

    public static Task DTOToTask(TaskDTO taskDTO){
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        for(SubTaskDTO subTaskDTO : taskDTO.getSubTasks()){
            task.addSubTask(DTOToSubTask(subTaskDTO));
        }
        return task;
    }

    public static TaskDTO taskToDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDueDate(task.getDueDate());
        for(SubTask subTask : task.getSubTasks()){
            taskDTO.addSubTask(subTaskToDTO(subTask));
        }
        return taskDTO;
    }

    public static SubTask DTOToSubTask(SubTaskDTO subTaskDTO){
        SubTask subTask = new SubTask();
        subTask.setName(subTaskDTO.getName());
        subTask.setDescription(subTaskDTO.getDescription());
        subTask.setId(subTaskDTO.getId());
        return subTask;
    }

    public static SubTaskDTO subTaskToDTO(SubTask subTask){
        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setName(subTask.getName());
        subTaskDTO.setDescription(subTask.getDescription());
        subTaskDTO.setId(subTask.getId());
        return subTaskDTO;
    }

}
