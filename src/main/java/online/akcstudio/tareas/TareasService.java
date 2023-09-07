package online.akcstudio.tareas;

import java.util.List;

public interface TareasService {
  
  List<Tarea> getTareas();
  Tarea getTarea(Long id);
  Tarea createTarea(Tarea tarea);
  Tarea updateTarea(Long id, Tarea tarea);
  boolean deleteTarea(Long id);

}
