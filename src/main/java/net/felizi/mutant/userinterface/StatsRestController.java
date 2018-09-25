package net.felizi.mutant.userinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.felizi.mutant.application.usecase.StatisticsOfGeneticTypesUseCase;

@RestController
@RequestMapping("/stat")
@CrossOrigin
public class StatsRestController extends AbstractRestController<StatsRestController> {
  private @Autowired StatisticsOfGeneticTypesUseCase statisticsOfGeneticTypesUseCase;

  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> stats() throws Exception {
    return ResponseEntity.ok(statisticsOfGeneticTypesUseCase.execute());
  }
}
