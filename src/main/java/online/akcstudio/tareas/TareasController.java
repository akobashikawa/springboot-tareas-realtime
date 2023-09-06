package online.akcstudio.tareas;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tareas")
public class TareasController {

  @Autowired
  private TareasService tareasService;

  @GetMapping
  public List<Tarea> getTareas() {
    List<Tarea> items = tareasService.getTareas();
    return items;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Tarea> getTarea(@PathVariable Long id) {
    Tarea tarea = tareasService.getTarea(id);
    if (tarea != null) {
      return new ResponseEntity<>(tarea, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<Tarea> createTarea(@RequestBody Tarea tarea) {
    Tarea created = tareasService.createTarea(tarea);
    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  private Tarea getTareaFromMap(Map<String, Object> body) {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.convertValue(body, Tarea.class);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Tarea> updateTarea(@PathVariable Long id, @RequestBody Map<String, Object> body) {
    Tarea tareaBody = getTareaFromMap(body);
    Tarea updated = tareasService.updateTarea(id, tareaBody);
    if (updated != null) {
      return new ResponseEntity<>(updated, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTarea(@PathVariable Long id) {
    boolean deleted = tareasService.deleteTarea(id);
    if (deleted) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
