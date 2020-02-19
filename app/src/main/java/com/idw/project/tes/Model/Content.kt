package com.idw.project.tes.Model

import android.os.Parcel
import android.os.Parcelable

data class Content(
//    val areaId: String,
//    val areaName: String,
//    val categoryName: String,
//    val description: String,
//    val funFact: String,
//    val googleAddress: Any,
//    val googleOpeningHours: Any,
//    val googlePhoneNumber: Any,
//    val googlePlaceName: Any,
//    val googleRating: Any,
//    val googleWebsite: Any,
//    val imageId: String,
//    val imageUrl: String,
//    val isRecomended: Int,
    val latitude: String?,
    val longitude: String?,
//    val placeCategoryId: String,
//    val placeGoogleId: String,
//    val placeId: String,
    val placeImage: String?,
    val placeName: String?
//    val price: String,
//    val rating: Any,
//    val thumbnail: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(placeImage)
        parcel.writeString(placeName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Content> {
        override fun createFromParcel(parcel: Parcel): Content {
            return Content(parcel)
        }

        override fun newArray(size: Int): Array<Content?> {
            return arrayOfNulls(size)
        }
    }
}
