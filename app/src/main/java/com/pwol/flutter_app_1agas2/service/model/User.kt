package com.pwol.flutter_app_1agas2.service.model

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("user_role")
                val userRole: List<String>?,
                @SerializedName("user_email")
                val userEmail: String = "",
                @SerializedName("user_id")
                val userId: Int = 0,
                @SerializedName("user_nicename")
                val userNicename: String = "",
                @SerializedName("avatar")
                val avatar: String = "",
                @SerializedName("user_display_name")
                val userDisplayName: String = "",
                @SerializedName("token")
                val token: String = "")