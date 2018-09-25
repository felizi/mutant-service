package net.felizi.mutant.userinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.felizi.mutant.application.dto.GeneticDTO;
import net.felizi.mutant.application.usecase.MutantValidatorUseCase;
import net.felizi.mutant.domain.enums.GeneticTypeEnum;

@RestController
@RequestMapping("/mutant")
@CrossOrigin
public class MutantsRestController extends AbstractRestController<MutantsRestController> {
  private @Autowired MutantValidatorUseCase mutantValidatorUseCase;

  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> validate(@RequestBody GeneticDTO genetic) throws Exception {
    GeneticTypeEnum geneticType = mutantValidatorUseCase.execute(genetic);
    Link self = ControllerLinkBuilder.linkTo(link.validate(genetic)).withSelfRel();
    Link stat = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(StatsRestController.class).stats())
        .withRel("stats");
    Resource<GeneticTypeEnum> resource = new Resource<>(geneticType, self, stat);

    switch (geneticType) {
    case HUMAN:
      return new ResponseEntity<>(resource, HttpStatus.FORBIDDEN);
    case MUTANT:
      return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    return toResource(null, self, stat);
  }
}