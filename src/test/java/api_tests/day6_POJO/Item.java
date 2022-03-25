package api_tests.day6_POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("region_id")
    private Integer regionId;
    @SerializedName("region_name")
    private String regionName;
    @SerializedName("links")
    private List<Link> links = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Item() {
    }

    /**
     *
     * @param regionId
     * @param regionName
     * @param links
     */
    public Item(Integer regionId, String regionName, List<Link> links) {
        super();
        this.regionId = regionId;
        this.regionName = regionName;
        this.links = links;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}