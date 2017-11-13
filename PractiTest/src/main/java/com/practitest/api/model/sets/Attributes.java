package com.practitest.api.model.sets;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "name"
})
public class Attributes {

    @JsonProperty("name")
    private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public Attributes() {
    }

    /**
     *
     * @param name
     */
    public Attributes(String name) {
        super();
        this.name = name;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

}