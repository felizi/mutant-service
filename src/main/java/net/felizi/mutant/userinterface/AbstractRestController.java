package net.felizi.mutant.userinterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
public abstract class AbstractRestController<C> {
  public final C link = getControllerLinkBuilder(this.getClass());

  @SuppressWarnings("unchecked")
  private C getControllerLinkBuilder(Class<?> domain) {
    return (C) ControllerLinkBuilder.methodOn(domain);
  }

  protected ResponseEntity<?> toResource(Map<String, Object> result, Link... links) {
    Map<String, Object> content = Objects.nonNull(result) ? result : new HashMap<>();
    Resource<?> resource = new Resource<>(content);
    resource.add(links);
    return new ResponseEntity<>(resource, HttpStatus.OK);
  }

}