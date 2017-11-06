package api.practitest.sets;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "test-ids"
})
public class Instances {

    @JsonProperty("test-ids")
    private List<Integer> testIds = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Instances() {
    }

    /**
     *
     * @param testIds
     */
    public Instances(List<Integer> testIds) {
        super();
        this.testIds = testIds;
    }

    @JsonProperty("test-ids")
    public List<Integer> getTestIds() {
        return testIds;
    }

    @JsonProperty("test-ids")
    public void setTestIds(List<Integer> testIds) {
        this.testIds = testIds;
    }

}