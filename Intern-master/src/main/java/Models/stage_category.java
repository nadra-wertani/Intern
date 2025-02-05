package Models;

public class stage_category {
    private int id;

    private String stage_category_name;


    private String description;
    public stage_category(){}

    public stage_category(int id,  String stage_category_name, String description) {
        this.id = id;
        this.stage_category_name =stage_category_name;

        this.description=description;

    }
    public stage_category(String stage_category_name, String description) {

        this.stage_category_name =stage_category_name;

        this.description=description;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getstage_category_name() {
        return stage_category_name;
    }

    public void setstage_category_name(String stage_category_name) {
        this.stage_category_name =stage_category_name;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description=description;
    }



    @Override
    public String toString() {
        return "stage_category{" +
                "id=" + id +

                ",stage_category_name='" + stage_category_name+ '\'' +

                ", description='" + description + '\'' +


                '}';
    }

}
