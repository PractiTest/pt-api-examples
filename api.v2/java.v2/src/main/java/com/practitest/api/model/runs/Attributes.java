package com.practitest.api.model.runs;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "instance-id"
})
public class Attributes {

    @JsonProperty("instance-id")
    private String instanceId;



    @JsonProperty("automated-execution-output")
    private String automatedExecutionOutput;


    @JsonProperty("exit-code")
    private int exitcode;

    /**
     * No args constructor for use in serialization
     *
     */
    public Attributes() {
    }

    /**
     *
     * @param instanceId
     */


    public Attributes(String instanceId, int exitcode) {
        super();
        this.instanceId = instanceId;
        this.exitcode = exitcode;
    }

    public Attributes(String instanceId, String automatedExecutionOutput, int exitcode) {
        super();
        this.instanceId = instanceId;
        this.automatedExecutionOutput = automatedExecutionOutput;
        this.exitcode = exitcode;
    }

    @JsonProperty("instance-id")
    public String getInstanceid() {
        return instanceId;
    }

    @JsonProperty("instance-id")
    public void setInstanceid(String instanceId) {
        this.instanceId = instanceId;
    }

    @JsonProperty("exit-code")
    public int getExitcode() {
        return exitcode;
    }

    @JsonProperty("exit-code")
    public void setExitcode(int exitcode) {
        this.exitcode = exitcode;
    }

    @JsonProperty("automated-execution-output")
    public String getAutomatedExecutionOutput() {
        return automatedExecutionOutput;
    }

    @JsonProperty("automated-execution-output")
    public void setAutomatedExecutionOutput(String automatedExecutionOutput) {
        this.automatedExecutionOutput = automatedExecutionOutput;
    }

}
