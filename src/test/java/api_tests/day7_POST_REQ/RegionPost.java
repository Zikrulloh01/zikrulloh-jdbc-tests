package api_tests.day7_POST_REQ;

public class RegionPost {



    private String region_name;
    private int region_id;

    public RegionPost() {
    }

    public RegionPost(int region_id, String region_name) {
        this.region_name = region_name;
        this.region_id = region_id;
    }


    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public int getId() {
        return region_id;
    }

    public void setId(int region_id) {
        this.region_id = region_id;
    }
}
