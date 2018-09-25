package net.felizi.mutant.application.translator;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.felizi.mutant.application.dto.StatisticsGeneticTypesDTO;

@Service
public class StatisticsOfGeneticTypesTranslator {
  private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsOfGeneticTypesTranslator.class);

  private final ObjectMapper mapper = new ObjectMapper();

  @SuppressWarnings("unchecked")
  public Map<String, Object> translate(StatisticsGeneticTypesDTO execute) {
    LOGGER.debug("Translating ={}", execute);
    return mapper.convertValue(execute, Map.class);
  }
}