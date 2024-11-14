package com.pemeta.pemetacard.data

object Injection {
    fun provideRepository(): PemetaCardRepository {
        return PemetaCardRepository.getInstance()
    }

    fun provideDetailExperienceRepository(): PemetaCardRepository {
        return PemetaCardRepository.getInstance()
    }
}