package com.demo.scg.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Routes {

    private String id;

    private String uri;

    private Integer domainId;

    private String routeId;

    private List<PredicateAndFilter> predicates;

    private List<PredicateAndFilter> filters;

    private Map<String, Object> metadata;
}
