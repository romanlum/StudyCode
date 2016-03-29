package moc5.canteenchecker;

import com.google.gson.annotations.SerializedName;

class Canteen {
    @SerializedName("Id")
    private int id;

    public int getId() {
        return id;
    }

    @SerializedName("Name")
    private String name;

    public String getName() {
        return name;
    }

    @SerializedName("Description")
    private String description;

    public String getDescription() {
        return description;
    }
}

class CanteenDetails extends Canteen {
    @SerializedName("Homepage")
    private String homepage;

    public String getHomepage() {
        return homepage;
    }

    @SerializedName("Phone")
    private String phone;

    public String getPhone() {
        return phone;
    }

    @SerializedName("Latitude")
    private double latitude;

    public double getLatitude() {
        return latitude;
    }

    @SerializedName("Longitude")
    private double longitude;

    public double getLongitude() {
        return longitude;
    }

    @SerializedName("SetMeal1")
    private SetMeal setMeal1;

    public SetMeal getSetMeal1() {
        return setMeal1;
    }

    @SerializedName("SetMeal2")
    private SetMeal setMeal2;

    public SetMeal getSetMeal2() {
        return setMeal2;
    }
}

class SetMeal {
    @SerializedName("MainCourse")
    private String mainCourse;

    public String getMainCourse() {
        return mainCourse;
    }

    @SerializedName("Price")
    private double price;

    public double getPrice() {
        return price;
    }
}