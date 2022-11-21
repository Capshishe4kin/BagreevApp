package com.example.bagreev.data

import com.example.bagreev.data.cache.GifCache
import com.example.bagreev.data.cache.GifDao
import com.example.bagreev.data.cloud.DataService
import com.example.bagreev.data.cloud.ServiceBuilder
import com.example.bagreev.data.model.DataImage
import com.example.bagreev.data.model.DataObject
import com.example.bagreev.data.model.DataResult
import com.example.bagreev.data.model.OgImage

class Repository(private val dao: GifDao) {

    private val retrofit = ServiceBuilder.buildService(DataService::class.java)

    suspend fun getGifts(): DataResult {
        val cachedData = dao.readAllData()
        if (cachedData.isEmpty()) {
            val cloudData = retrofit.getGifs().body()!!
            cloudData.res.forEach {
                dao.insert(GifCache(it.images.ogImage.url))
            }
            return cloudData
        } else {
            val dataObjects = mutableListOf<DataObject>()
            cachedData.forEach {
                val ogImage = OgImage(it.link)
                val dataImage = DataImage(ogImage)
                val dataObject = DataObject(dataImage)
                dataObjects.add(dataObject)
            }
            return DataResult(dataObjects)
        }
    }


}