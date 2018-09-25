package net.felizi.mutant.domain.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.felizi.mutant.application.dto.GeneticMatrixDTO;
import net.felizi.mutant.domain.enums.GeneticTypeEnum;
import net.felizi.mutant.domain.tasks.ColumnTask;
import net.felizi.mutant.domain.tasks.DiagonalLeftTask;
import net.felizi.mutant.domain.tasks.DiagonalRightTask;
import net.felizi.mutant.domain.tasks.LineTask;

@Service
public class IdentifyTypeOfDNAParallelExecutorService {
  private static final Logger LOGGER = LoggerFactory.getLogger(IdentifyTypeOfDNAParallelExecutorService.class);

  protected GeneticTypeEnum execute(GeneticMatrixDTO matrix) throws Exception {
    try {
      if (matrix != null && matrix.hasDNA()) {
        ExecutorService executorService = Executors.newWorkStealingPool();
        List<Callable<GeneticTypeEnum>> tasks = createTasks(matrix);
        List<Future<GeneticTypeEnum>> executorResult = executorService.invokeAll(tasks);
        executorService.shutdown();
        return isMutant(executorResult) ? GeneticTypeEnum.MUTANT : GeneticTypeEnum.HUMAN;
      }
      return null;
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw e;
    }
  }

  private boolean isMutant(List<Future<GeneticTypeEnum>> executorResult) {
    return executorResult.stream().map(parse()).anyMatch(g -> g.equals(GeneticTypeEnum.MUTANT));
  }

  private Function<? super Future<GeneticTypeEnum>, ? extends GeneticTypeEnum> parse() {
    return t -> {
      try {
        return t.get();
      } catch (InterruptedException | ExecutionException e) {
        LOGGER.error("Error on get future", e);
      }
      return null;
    };
  }

  private List<Callable<GeneticTypeEnum>> createTasks(GeneticMatrixDTO matrix) {
    return Arrays.asList(new LineTask(matrix), new ColumnTask(matrix), new DiagonalLeftTask(matrix),
        new DiagonalRightTask(matrix));
  }
}