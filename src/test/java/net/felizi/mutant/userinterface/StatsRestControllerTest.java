package net.felizi.mutant.userinterface;

import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.felizi.mutant.application.dto.StatisticsGeneticTypesDTO;
import net.felizi.mutant.application.usecase.StatisticsOfGeneticTypesUseCase;

@RunWith(SpringRunner.class)
@WebMvcTest(StatsRestController.class)
public class StatsRestControllerTest {

  private @MockBean StatisticsOfGeneticTypesUseCase useCase;
  private @Autowired MockMvc mvc;
  private final ObjectMapper mapper = new ObjectMapper();

  @SuppressWarnings("unchecked")
  @Test
  public void shouldCallAndReturnMutant() throws Exception {
    Mockito.when(useCase.execute()).thenReturn(mapper.convertValue(new StatisticsGeneticTypesDTO(0, 1, 2), Map.class));

    mvc.perform(MockMvcRequestBuilders.get("/stats/") //
        .contentType(MediaType.APPLICATION_JSON))//
        .andDo(MockMvcResultHandlers.print())//
        .andExpect(MockMvcResultMatchers.status().isOk())//
        .andExpect(MockMvcResultMatchers.content()//
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))//
        .andExpect(MockMvcResultMatchers.jsonPath("$.count_mutant_dna", Matchers.is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.count_human_dna", Matchers.is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.ratio", Matchers.equalTo(2.0)));

  }
}