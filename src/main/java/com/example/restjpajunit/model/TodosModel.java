package com.example.restjpajunit.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class TodosModel {
    private String text;
    private Boolean completed;
}
