package com.example.chipdip.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import com.example.chipdip.model.valute.*
import com.google.gson.annotations.SerializedName

data class Valute(
    @SerializedName("AUD") val aUD: AUD,
    @SerializedName("AZN") val aZN: AZN,
    @SerializedName("GBP") val gBP: GBP,
    @SerializedName("AMD") val aMD: AMD,
    @SerializedName("BYN") val bYN: BYN,
    @SerializedName("BGN") val bGN: BGN,
    @SerializedName("BRL") val bRL: BRL,
    @SerializedName("HUF") val hUF: HUF,
    @SerializedName("VND") val vND: VND,
    @SerializedName("HKD") val hKD: HKD,
    @SerializedName("GEL") val gEL: GEL,
    @SerializedName("DKK") val dKK: DKK,
    @SerializedName("AED") val aED: AED,
    @SerializedName("USD") val uSD: USD,
    @SerializedName("EUR") val eUR: EUR,
    @SerializedName("EGP") val eGP: EGP,
    @SerializedName("INR") val iNR: INR,
    @SerializedName("IDR") val iDR: IDR,
    @SerializedName("KZT") val kZT: KZT,
    @SerializedName("CAD") val cAD: CAD,
    @SerializedName("QAR") val qAR: QAR,
    @SerializedName("KGS") val kGS: KGS,
    @SerializedName("CNY") val cNY: CNY,
    @SerializedName("MDL") val mDL: MDL,
    @SerializedName("NZD") val nZD: NZD,
    @SerializedName("NOK") val nOK: NOK,
    @SerializedName("PLN") val pLN: PLN,
    @SerializedName("RON") val rON: RON,
    @SerializedName("XDR") val xDR: XDR,
    @SerializedName("SGD") val sGD: SGD,
    @SerializedName("TJS") val tJS: TJS,
    @SerializedName("THB") val tHB: THB,
    @SerializedName("TRY") val tRY: TRY,
    @SerializedName("TMT") val tMT: TMT,
    @SerializedName("UZS") val uZS: UZS,
    @SerializedName("UAH") val uAH: UAH,
    @SerializedName("CZK") val cZK: CZK,
    @SerializedName("SEK") val sEK: SEK,
    @SerializedName("CHF") val cHF: CHF,
    @SerializedName("RSD") val rSD: RSD,
    @SerializedName("ZAR") val zAR: ZAR,
    @SerializedName("KRW") val kRW: KRW,
    @SerializedName("JPY") val jPY: JPY
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("aUD"),
        TODO("aZN"),
        TODO("gBP"),
        TODO("aMD"),
        TODO("bYN"),
        TODO("bGN"),
        TODO("bRL"),
        TODO("hUF"),
        TODO("vND"),
        TODO("hKD"),
        TODO("gEL"),
        TODO("dKK"),
        TODO("aED"),
        TODO("uSD"),
        TODO("eUR"),
        TODO("eGP"),
        TODO("iNR"),
        TODO("iDR"),
        TODO("kZT"),
        TODO("cAD"),
        TODO("qAR"),
        TODO("kGS"),
        TODO("cNY"),
        TODO("mDL"),
        TODO("nZD"),
        TODO("nOK"),
        TODO("pLN"),
        TODO("rON"),
        TODO("xDR"),
        TODO("sGD"),
        TODO("tJS"),
        TODO("tHB"),
        TODO("tRY"),
        TODO("tMT"),
        TODO("uZS"),
        TODO("uAH"),
        TODO("cZK"),
        TODO("sEK"),
        TODO("cHF"),
        TODO("rSD"),
        TODO("zAR"),
        TODO("kRW"),
        TODO("jPY")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Valute> {
        override fun createFromParcel(parcel: Parcel): Valute {
            return Valute(parcel)
        }

        override fun newArray(size: Int): Array<Valute?> {
            return arrayOfNulls(size)
        }
    }
}
