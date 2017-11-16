package com.practitest.api.model.sets;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "data"
})
public class SetsModel {

    @JsonProperty("data")
    private Data data;

    /**
     * No args constructor for use in serialization
     *
     */
    public SetsModel() {
    }

    /**
     *
     * @param data
     */
    public SetsModel(Data data) {
        super();
        this.data = data;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

}
