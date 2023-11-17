package com.example.myapplication.data.remote.responses

import org.json.JSONObject
import java.io.Serializable

data class PhotoJson(
    var albumId: Int,
    var id: Int,
    var title: String,
    var url: String,
    var thumbnailUrl: String

)



//data class PhotoJson(
//    var albumId: Int,
//    var id: Int,
//    var title: String,
//    var url: String,
//    var thumbnailUrl: String
//
//) : Serializable {
//    constructor(jsonObject: JSONObject): this(
//        jsonObject.getInt("albumId"),
//        jsonObject.getInt("id"),
//        jsonObject.getString("title"),
//        jsonObject.getString("url"),
//        jsonObject.getString("thumbnailUrl")
//    )
//}

