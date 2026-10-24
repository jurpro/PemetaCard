package com.pemeta.pemetacard.data

import com.pemeta.pemetacard.data.repository.PemetaCardRepository
import com.pemeta.pemetacard.data.PemetaDataRepository

object Injection {
    /* fun provideRepositoryid(): PemetaDataRepository {
        return PemetaDataRepository().getInstance()
    }

    fun provideDetailExperienceRepositoryid(): PemetaDataRepository {
        return PemetaDataRepository().getInstance()
    } */

    fun provideRepository(): PemetaCardRepository {
        return PemetaCardRepository.getInstance()
    }

    fun provideDetailExperienceRepository(): PemetaCardRepository {
        return PemetaCardRepository.getInstance()
    }
}