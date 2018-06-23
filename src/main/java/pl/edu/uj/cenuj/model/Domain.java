package pl.edu.uj.cenuj.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class Domain {
    @NotEmpty
    private String name;
}
