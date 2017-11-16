package com.practitest.api.model.sets;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "type",
        "attributes",
        "instances"
})
public class Data {

    @JsonProperty("type")
    private String type;
    @JsonProperty("attributes")
    private Attributes attributes;
    @JsonProperty("instances")
    private Instances instances;

    /**
     * No args constructor for use in serialization
     *
     */
    public Data() {
    }

    /**
     *
     * @param attributes
     * @param instances
     */
    public Data(Attributes attributes, Instances instances) {
        super();
        this.type = "sets";
        this.attributes = attributes;
        this.instances = instances;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("attributes")
    public Attributes getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @JsonProperty("instances")
    public Instances getInstances() {
        return instances;
    }

    @JsonProperty("instances")
    public void setInstances(Instances instances) {
        this.instances = instances;
    }

}