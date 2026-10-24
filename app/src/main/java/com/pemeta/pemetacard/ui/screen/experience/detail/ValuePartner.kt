package com.pemeta.pemetacard.ui.screen.experience.detail

import androidx.compose.runtime.Composable
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience

@Composable
fun valuePartner(
    experienceData: CompanyExperience
): String {

    val valueStandAlone = if (experienceData.isStandAlone) {"Tunggal"} else {""}
    val valuePartner1 = if (experienceData.partner1 != null) {"${experienceData.partner1.name}"} else {""}
    val valuePartner2 = if (experienceData.partner2 != null) {"${experienceData.partner2.name}"} else {""}
    val valuePartner3 = if (experienceData.partner3 != null) {"${experienceData.partner3.name}"} else {""}
    val valuePartner4 = if (experienceData.partner4 != null) {"${experienceData.partner4.name}"} else {""}
    val valuePartner5 = if (experienceData.partner5 != null) {"${experienceData.partner5.name}"} else {""}

    val finalValuePartner = if (valueStandAlone == "Tunggal") {valueStandAlone} else {
        if (valuePartner5 == "${experienceData.partner5?.name}") {
            valuePartner1 + "\n" + valuePartner2 + "\n" + valuePartner3 + "\n" + valuePartner4 + "\n" + valuePartner5
        } else {
            if (valuePartner4 == "${experienceData.partner4?.name}") {
                valuePartner1 + "\n" + valuePartner2 + "\n" + valuePartner3 + "\n" + valuePartner4
            } else {
                if (valuePartner3 == "${experienceData.partner3?.name}") {
                    valuePartner1 + "\n" + valuePartner2 + "\n" + valuePartner3
                } else {
                    if (valuePartner2 == "${experienceData.partner2?.name}") {
                        valuePartner1 + "\n" + valuePartner2
                    } else {valuePartner1}
                }
            }
        }
    }
    return finalValuePartner
}
