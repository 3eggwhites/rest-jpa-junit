package com.example.restjpajunit.data.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter @NoArgsConstructor
public class Todos implements Serializable {

  private static final long serialVersionUID = -7650241860130686014L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "hibernate.sequence")
  private Long id;
  private String text;
  private Boolean completed;
}
