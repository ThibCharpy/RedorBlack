package com.stl.tpalt.redorblack.utils

import android.os.Parcel
import android.os.Parcelable
import com.stl.tpalt.redorblack.R

enum class RulesObject() : Parcelable{

    ONE,
    TWO,
    THREE,
    EMPTY;

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ordinal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RulesObject> {
        override fun createFromParcel(parcel: Parcel): RulesObject {
            return RulesObject.values()[parcel.readInt()]
        }

        override fun newArray(size: Int): Array<RulesObject?> {
            return RulesObject.newArray(size)
        }
    }
}