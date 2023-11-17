package com.example.myapplication.data.remote.responses

import org.json.JSONObject
import java.io.Serializable

data class AlbumJson(
    var userId: Int,
    var id: Int,
    var title: String
) : Serializable {
    constructor(jsonObject: JSONObject): this(
        jsonObject.getInt("userId"),
        jsonObject.getInt("id"),
        jsonObject.getString("title")
    )
}

