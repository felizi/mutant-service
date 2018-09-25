package net.felizi.mutant.userinterface;

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

import net.felizi.mutant.application.dto.GeneticDTO;
import net.felizi.mutant.application.usecase.MutantValidatorUseCase;
import net.felizi.mutant.domain.enums.GeneticTypeEnum;

@RunWith(SpringRunner.class)
@WebMvcTest(MutantsRestController.class)
public class MutantsRestControllerTest {
  
  private @MockBean MutantValidatorUseCase useCase;
  private @Autowired MockMvc mvc;
  private final ObjectMapper mapper = new ObjectMapper();
  
  @Test
  public void shouldCallAndReturnMutant() throws Exception {
    Mockito.when(useCase.execute(Mockito.any())).thenReturn(GeneticTypeEnum.MUTANT);
    
    mvc.perform(MockMvcRequestBuilders.post("/mutant/")
        .content(mapper.writeValueAsString(new GeneticDTO()))
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }
  
  @Test
  public void shouldCallAndReturnHuman() throws Exception {
    Mockito.when(useCase.execute(Mockito.any())).thenReturn(GeneticTypeEnum.HUMAN);
    
    mvc.perform(MockMvcRequestBuilders.post("/mutant/")
        .content(mapper.writeValueAsString(new GeneticDTO()))
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isForbidden())
        .andExpect(MockMvcResultMatchers.content()
        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }


}