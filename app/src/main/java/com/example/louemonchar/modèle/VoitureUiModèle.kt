package com.example.louemonchar.modèle




import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import java.util.Date

data class VoitureUiModèle(
    @DrawableRes var imageRes: Int,
    var modèle: String,
    var année: Date,
    var location: Date,
    var passagers: String,
    var propriétaire: String,
) : Parcelable {
    // Constructeur par défaut nécessaire pour le Parcelable
    constructor() : this(0, "", Date(), Date(), "", "")
    // Ajoutez le code nécessaire pour rendre la classe Parcelable
    // Vous pouvez utiliser un générateur de code pour ajouter les méthodes writeToParcel et createFromParcel
    // Assurez-vous que toutes les propriétés de la classe sont bien écrites et lues depuis le Parcel.

    // Exemple (à adapter en fonction de votre implémentation) :
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(imageRes)
        dest.writeString(modèle)
        dest.writeLong(année.time)
        dest.writeLong(location.time)
        dest.writeString(passagers)
        dest.writeString(propriétaire)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<VoitureUiModèle> = object : Parcelable.Creator<VoitureUiModèle> {
            override fun createFromParcel(parcel: Parcel): VoitureUiModèle {
                return VoitureUiModèle(parcel)
            }

            override fun newArray(size: Int): Array<VoitureUiModèle?> {
                return arrayOfNulls(size)
            }
        }
    }

    // Constructeur pour la création depuis le Parcel
    constructor(parcel: Parcel) : this() {
        imageRes = parcel.readInt()
        modèle = parcel.readString() ?: ""
        année = Date(parcel.readLong())
        location = Date(parcel.readLong())
        passagers = parcel.readString() ?: ""
        propriétaire = parcel.readString() ?: ""
    }
}
