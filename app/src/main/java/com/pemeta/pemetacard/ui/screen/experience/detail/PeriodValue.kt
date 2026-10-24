package com.pemeta.pemetacard.ui.screen.experience.detail

import androidx.compose.runtime.Composable
import com.pemeta.pemetacard.extention.utils.LocalDateParsing
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience

@Composable
fun periodValue(
    experienceData: CompanyExperience
): String{

    val dateBegin = LocalDateParsing().convertLocalDate(experienceData.periodBegin)
    val dateEnd = LocalDateParsing().convertLocalDate(experienceData.periodEnd)

    val period = buildString {
        append(dateBegin)
        append(" - ")
        append(dateEnd)
    }
    return period
}