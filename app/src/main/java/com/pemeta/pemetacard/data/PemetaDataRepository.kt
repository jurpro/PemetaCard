package com.pemeta.pemetacard.data

import com.pemeta.pemetacard.model.OrderExperience
import com.pemeta.pemetacard.model.client.Client
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import com.pemeta.pemetacard.model.client.DataPemetaClient
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class PemetaDataRepository {
    private val orderExperience = mutableListOf<OrderExperience>()
    private val allExperiences = mutableListOf<CompanyExperience>()
    private val allClients = mutableListOf<Client>()

    fun getListExperience(): Flow<List<CompanyExperience>> {
        return flowOf(DataPemetaExperience.CompanyExperienceList)
    }

    fun getListClient(): Flow<List<Client>> {
        return flowOf(DataPemetaClient.ClientList)
    }

    fun getListOnGoing(): Flow<List<CompanyExperience>> {
        return getListExperience().map { allOnGoingProjects ->
            allOnGoingProjects.filter { onGoing ->
                !onGoing.isFinished
            }
        }
    }

    fun getListExperienceByClient(clientId: Int): Flow<List<CompanyExperience>> {

        return getListExperience().map { allProjectsByClient ->
            allProjectsByClient.filter { allProjects ->
                allProjects.ourClient?.id == clientId
            }
        }
    }




    init {
        if (orderExperience.isEmpty()) {
            DataPemetaExperience.CompanyExperienceList.forEach {
                orderExperience.add(OrderExperience(it, 0))
            }
        }
    }

    fun getExperienceById(experienceId: Int): CompanyExperience {
        return allExperiences.first {
            it.id == experienceId
        }
    }

    fun getClientById(clientId: Int): Client {
        return allClients.first {
            it.id == clientId
        }
    }

    fun getClientExperiencebyId(clientExperienceId: Int): List<Client> {
        return  DataPemetaClient.ClientList
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
        private var instance: PemetaDataRepository? = null

        fun getInstance(): PemetaDataRepository =
            instance ?: synchronized(this) {
                PemetaDataRepository().apply {
                    instance = this
                }
            }
    }
}