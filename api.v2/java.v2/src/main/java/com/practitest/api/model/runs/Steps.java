package com.practitest.api.model.runs;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "data"
})
public class Steps {

    @JsonProperty("data")
    private List<StepModel> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Steps() {
    }

    /**
     *
     * @param data
     */
    public Steps(List<StepModel> data) {
        super();
        this.data = data;
    }

    @JsonProperty("data")
    public List<StepModel> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<StepModel> data) {
        this.data = data;
    }

}
