package pl.edu.uj.cenuj.model.scrapping;

import lombok.Data;

import java.util.List;

@Data
public class ScrapData {
    private final String domain;
    private final List<ProductLinkToScrap> links;
}
