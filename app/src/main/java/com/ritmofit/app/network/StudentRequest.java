package com.ritmofit.app.network;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class StudentRequest extends UserRequest {
    @SerializedName("currentBookings")
    public List<Object> currentBookings = null;
    @SerializedName("classHistory")
    public List<Object> classHistory = null;
}
