package com.example.restjpajunit.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
public class TodosModel implements Serializable {

    private static final long serialVersionUID = -632119473467449453L;

    private Long id;
    private String text;
    private Boolean completed;
}
