package pl.edu.uj.cenuj.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Domain {
    @NotEmpty
    private String name;
}
