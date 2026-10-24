package com.pemeta.pemetacard.data.repository

import com.pemeta.pemetacard.model.lagilitas.data.DataLegalitas
import com.pemeta.pemetacard.model.lagilitas.model.Legalitas
import com.pemeta.pemetacard.model.newsinfo.data.DataNewsInfo
import com.pemeta.pemetacard.model.newsinfo.model.NewsInfo
import com.pemeta.pemetacard.model.client.Client
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import com.pemeta.pemetacard.model.client.DataPemetaClient
import com.pemeta.pemetacard.model.newsinfo.data.HighLightData
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience
import com.pemeta.pemetacard.model.pengalaman.data.DataJobType
import com.pemeta.pemetacard.model.pengalaman.data.ServicesData
import com.pemeta.pemetacard.model.pengalaman.model.JobType
import com.pemeta.pemetacard.model.pengalaman.model.ServiceBudget
import com.pemeta.pemetacard.model.team.Employee
import com.pemeta.pemetacard.model.team.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class PemetaCardRepository {
    // List Pengalaman dan Client
   // private val orderExperience = mutableListOf<OrderExperience>()
    private val allExperiences = mutableListOf<CompanyExperience>()
    private val allClients = mutableListOf<Client>()

    private val allEmployee = mutableListOf<Employee>()

    fun getListExperience(): Flow<List<CompanyExperience>> {
        return flowOf(DataPemetaExperience.CompanyExperienceList)
    }

    fun getListGlobalWork(typeGlobalId: Int): Flow<List<CompanyExperience>> {
        return getListExperience().map { allPlanningWork ->
            allPlanningWork.filter { allPlanningProjects ->
                allPlanningProjects.typeGlobal.id == typeGlobalId
            }
        }
    }

    fun getListClient(): Flow<List<Client>> {
        return flowOf(DataPemetaClient.ClientList)
    }

    fun getListOnGoing(): Flow<List<CompanyExperience>> {
        return getListExperience().map { allOnGoingProjects ->
            allOnGoingProjects.filter { onGoing ->
                onGoing.isFinished != true
            }
        }
    }

    fun getListExperienceByClient(clientId: Int): Flow<List<CompanyExperience>> {
         return getListExperience().map { allProjectsByClient ->
            allProjectsByClient.filter { allProjects ->
                allProjects.ourClient.id == clientId
            }
        }
    }

    fun getListJobFinished(clientId: Int): Flow<List<CompanyExperience>> {
        return getListExperience().map { allProjectsByClient ->
            allProjectsByClient.filter { allProjects ->
                allProjects.ourClient.id == clientId && allProjects.isFinished
            }
        }
    }

    fun getListJobOnGoing(clientId: Int): Flow<List<CompanyExperience>> {
        return getListExperience().map { allProjectsByClient ->
            allProjectsByClient.filter { allProjects ->
                allProjects.ourClient.id == clientId && !allProjects.isFinished
            }
        }
    }

    // List Pengalaman Berdasarkan Jenis Pekerjaan
    fun getListType(): Flow<List<JobType>> {
        return flowOf(DataJobType.JobTypeList)
    }

    fun getListExperienceByType(typeId: Int): Flow<List<CompanyExperience>> {
        return getListExperience().map { allProjectsByType ->
            allProjectsByType.filter { allProjects ->
                allProjects.type.id == typeId
            }
        }
    }

    fun getListFinishedByType(typeId: Int): Flow<List<CompanyExperience>> {
        return getListExperience().map { allProjectsByType ->
            allProjectsByType.filter { allProjects ->
                allProjects.type.id == typeId && allProjects.isFinished
            }
        }
    }

    fun getListOnGoingByType(typeId: Int): Flow<List<CompanyExperience>> {
        return getListExperience().map { allProjectsByType ->
            allProjectsByType.filter { allProjects ->
                allProjects.type.id == typeId && !allProjects.isFinished
            }
        }
    }

    // List Pengalaman Berdasarkan Budget Service Pekerjaan
    fun getListBudget(): Flow<List<ServiceBudget>> {
        return flowOf(ServicesData.listServicesData)
    }

    fun getListExperienceByBudget(serviceId: Int): Flow<List<CompanyExperience>> {
        return getListExperience().map { allProjectsByService ->
            allProjectsByService.filter { allProjects ->
                allProjects.serviceBudget.id == serviceId
            }
        }
    }

    fun getListFinishedByBudget(serviceId: Int): Flow<List<CompanyExperience>> {
        return getListExperience().map { allProjectsByService ->
            allProjectsByService.filter { allProjects ->
                allProjects.serviceBudget.id == serviceId && allProjects.isFinished
            }
        }
    }

    fun getListOnGoingByBudget(serviceId: Int): Flow<List<CompanyExperience>> {
        return getListExperience().map { allProjectsByService ->
            allProjectsByService.filter { allProjects ->
                allProjects.serviceBudget.id == serviceId && !allProjects.isFinished
            }
        }
    }


    // List Legalitas Pemeta
    private val allLegalitas = mutableListOf<Legalitas>()

    fun getListLegal(): Flow<List<Legalitas>> {
        return flowOf(DataLegalitas.LegalitasList)
    }

    // List News Info
    private val allNewsInfo = mutableListOf<NewsInfo>()

    fun getListNewsInfo(): Flow<List<NewsInfo>> {
        return flowOf(DataNewsInfo.NewsInfoList)
    }

    fun getListHeadline(): Flow<List<NewsInfo>> {
        return flowOf(HighLightData.highLightProject)
    }

    fun getListNewsByExperience(experienceId: Int): Flow<List<NewsInfo>> {
        return getListNewsInfo().map { allProjectsByNews ->
            allProjectsByNews.filter { newsProject ->
                newsProject.project?.id == experienceId
            }
        }
    }

    // List Karyawan Pemeta
    fun getListTeam(): Flow<List<Employee>> {
        return flowOf(Team.teamManagement)
    }

    fun getListTeamManagement(): Flow<List<Employee>> {
        return getListTeam().map { allTeamManagements ->
            allTeamManagements.filter { management ->
                management.isTopManagement
            }
        }
    }

    fun getListTeamDesign(): Flow<List<Employee>> {
        return getListTeam().map { allTeamDesigns ->
            allTeamDesigns.filter { design ->
                design.isDEDTeam
            }
        }
    }

    fun getListTeamSupervision(): Flow<List<Employee>> {
        return getListTeam().map { allTeamSupervisions ->
            allTeamSupervisions.filter { supervision ->
                supervision.isSupervisionTeam
            }
        }
    }

    /*
    init {
        if (orderExperience.isEmpty()) {
            DataPemetaExperience.CompanyExperienceList.forEach {
                orderExperience.add(OrderExperience(it, 0))
            }
        }
    }


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

    /*
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
    } */

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