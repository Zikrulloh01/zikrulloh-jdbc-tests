
package api_tests.day6_POJO;


import com.google.gson.annotations.SerializedName;

public class Link__1 {

    @SerializedName("rel")
    private String rel;
    @SerializedName("href")
    private String href;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Link__1() {
    }

    /**
     * 
     * @param rel
     * @param href
     */
    public Link__1(String rel, String href) {
        super();
        this.rel = rel;
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
