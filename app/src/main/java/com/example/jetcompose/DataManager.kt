package com.example.jetcompose

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.jetcompose.data.Quote
import com.google.gson.Gson

object DataManager {
    var data = emptyArray<Quote>()
    var currentQuote:Quote?=null
    var currentPage = mutableStateOf(Pages.LISTING)
    var isDataLoaded = mutableStateOf(false)
    fun getAssetFromFile(context: Context)
    {
        val inputStream=context.assets.open("quotes.json")
        val size:Int=inputStream.available()
        val buffer=ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json= String(buffer,Charsets.UTF_8)
        val gson= Gson()
        data=gson.fromJson(json,Array<Quote>::class.java)
        isDataLoaded.value=true
    }
    fun switchPages(quote: Quote?) {
        if (currentPage.value==Pages.LISTING){
            currentPage.value=Pages.DETAIL
            currentQuote=quote
        }else{
            currentPage.value=Pages.LISTING
        }
    }
}