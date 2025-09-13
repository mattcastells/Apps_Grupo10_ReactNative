package com.ritmofit.app.network;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ProfessorRequest extends UserRequest {
    @SerializedName("classTypes")
    public List<String> classTypes;
    @SerializedName("taughtClasses")
    public List<Object> taughtClasses = null;
}
