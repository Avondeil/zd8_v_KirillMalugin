package com.example.prakt8

import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStreamReader

object JSONHelper
{
    private val FILE_NAME = "data.json"
    fun exportToJSON(context: Context, dataList:List<Case?>?):Boolean
    {
        val gson= Gson()
        val dataItems=DataItems()
        dataItems.cases=dataList
        val jsonString:String = gson.toJson(dataItems)
        try {
            context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use{ fileOutPutStream->fileOutPutStream.write(jsonString.toByteArray()) }
            return true
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }
        return false
    }

    fun importFromJSON(context: Context):List<Case?>?{
        try {
            context.openFileInput(FILE_NAME).use {fileInputStream->
                InputStreamReader(fileInputStream).use{ streamReader->
                    val gson= Gson()
                    val dataItems:DataItems=gson.fromJson(streamReader,DataItems::class.java)
                    return dataItems.cases
                }
            }
        }
        catch (ex: IOException)
        {
            ex.printStackTrace()
        }
        return null
    }
    private class DataItems{
        var cases: List<Case?>?=null
    }
}