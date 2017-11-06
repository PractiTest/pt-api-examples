package api.practitest.runs;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "instance-id"
})
public class Attributes {

    @JsonProperty("instance-id")
    private int instanceId;

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


    public Attributes(int instanceId) {
        super();
        this.instanceId = instanceId;
    }

    @JsonProperty("instance-id")
    public int getInstanceid() {
        return instanceId;
    }

    @JsonProperty("instance-id")
    public void setInstanceid(int instanceId) {
        this.instanceId = instanceId;
    }

}
