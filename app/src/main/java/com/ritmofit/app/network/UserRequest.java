package com.ritmofit.app.network;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class UserRequest {
    @SerializedName("email")
    public String email;
    @SerializedName("name")
    public String name;
    @SerializedName("age")
    public Integer age;
    @SerializedName("gender")
    public String gender;
    @SerializedName("profilePicture")
    public String profilePicture;
    @SerializedName("rol")
    public String rol;
    @SerializedName("password")
    public String password;
}
