package com.practitest.api.model.instance;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "set-id",
        "test-id"
})
public class Attributes {

    @JsonProperty("set-id")
    private String setId;
    @JsonProperty("test-id")
    private int testId;

    /**
     * No args constructor for use in serialization
     *
     */
    public Attributes() {
    }

    /**
     *
     * @param testId
     * @param setId
     */
    public Attributes(String setId, int testId) {
        super();
        this.setId = setId;
        this.testId = testId;
    }

    @JsonProperty("set-id")
    public String getSetId() {
        return setId;
    }

    @JsonProperty("set-id")
    public void setSetId(String setId) {
        this.setId = setId;
    }

    @JsonProperty("test-id")
    public int getTestId() {
        return testId;
    }

    @JsonProperty("test-id")
    public void setTestId(int testId) {
        this.testId = testId;
    }

}