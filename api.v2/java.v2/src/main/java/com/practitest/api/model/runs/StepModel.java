package com.practitest.api.model.runs;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "name",
        "status"
})
public class StepModel {

    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;

    /**
     * No args constructor for use in serialization
     *
     */

    public StepModel()
    {}

    /**
     *
     * @param status
     * @param name
     */
    public StepModel(String name, String status) {
        this.name = name;
        this.status = status;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
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
