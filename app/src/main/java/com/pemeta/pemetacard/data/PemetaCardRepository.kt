package com.pemeta.pemetacard.data

import com.pemeta.pemetacard.model.Client
import com.pemeta.pemetacard.model.CompanyExperience
import com.pemeta.pemetacard.model.DataPemetaClient
import com.pemeta.pemetacard.model.DataPemetaExperience
import com.pemeta.pemetacard.model.OrderExperience
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class PemetaCardRepository {
    private val orderExperience = mutableListOf<OrderExperience>()
    private val allExperiences = mutableListOf<CompanyExperience>()

    fun getListExperience(): Flow<List<CompanyExperience>> {
        return flowOf(DataPemetaExperience.CompanyExperienceList)
    }

    init {
        if (orderExperience.isEmpty()) {
            DataPemetaExperience.CompanyExperienceList.forEach {
                orderExperience.add(OrderExperience(it, 0))
            }
        }
    }

    /*
    init {
        if (allAnimals.isEmpty()) {
            SampleAnimalDataSource.animalList.forEach {
                allAnimals.add(SampleAnimalDataSource.animalList(it, 0))
            }
        }
    }

    fun getAllAnimals(): Flow<List<OrderAdopted>> {
        return flowOf(SampleAnimalDataSource.animalList)
    } */

    fun getExperienceById(experienceId: Int): CompanyExperience {
        return allExperiences.first {
            it.id == experienceId
        }
    }

    fun getExperiences(): List<CompanyExperience> {
        return DataPemetaExperience.CompanyExperienceList
    }

    fun searchExperiences(query: String): List<CompanyExperience>{
        return DataPemetaExperience.CompanyExperienceList.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    fun getAllClients(): Flow<List<Client>> {
        return flowOf(DataPemetaClient.ClientList)
    }

    fun updateOrderExperienced(experienceId: Int, newCountValue: Int): Flow<Boolean> {
        val index = orderExperience.indexOfFirst { it.experience.id == experienceId }
        val result = if (index >= 0) {
            val orderExperiences = orderExperience[index]
            orderExperience[index] =
                orderExperiences.copy(
                    experience = orderExperience[index].experience,
                    price = newCountValue
                )
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderRewards(): Flow<List<CompanyExperience>> {
        return getListExperience().map { allItems ->
            allItems.filter { item ->
                item.id != 0
            }
        }
    }

    companion object {
        @Volatile
        private var instance: PemetaCardRepository? = null

        fun getInstance(): PemetaCardRepository =
            instance ?: synchronized(this) {
                PemetaCardRepository().apply {
                    instance = this
                }
            }
    }
}