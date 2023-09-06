package online.akcstudio.tareas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareasServiceImpl implements TareasService {

  @Autowired
  private TareasRepository tareasRepository;

  @Override
  public List<Tarea> getTareas() {
    List<Tarea> items = tareasRepository.findAll();
    return items;
  }

  @Override
  public Tarea getTarea(Long id) {
    Optional<Tarea> item = tareasRepository.findById(id);
    if (item.isPresent()) {
      return item.get();
    }
    return null;
  }

  @Override
  public Tarea createTarea(Tarea tarea) {
    Tarea created = tareasRepository.save(tarea);
    return created;
  }

  @Override
  public Tarea updateTarea(Long id, Tarea tarea) {
    if (tareasRepository.existsById(id)) {
      tarea.setId(id);
      return tareasRepository.save(tarea);
    } else {
      return null;
    }
  }

  @Override
  public boolean deleteTarea(Long id) {
    if (tareasRepository.existsById(id)) {
      tareasRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

}
