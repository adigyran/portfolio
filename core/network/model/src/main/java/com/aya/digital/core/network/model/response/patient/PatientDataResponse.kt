package com.aya.digital.core.network.model.response.patient

import com.aya.digital.core.network.model.response.doctors.InsuranceResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/*{
    "id": 1185,
    "firstName": "Ursula",
    "lastName": "Crane",
    "middlename": null,
    "photo": "some test path",
    "age": 33,
    "insurances": [
        {
            "id": 1000,
            "number": "100-101-23",
            "organizationName": "INS COMP"
        },
        {
            "id": 1001,
            "number": "100-101-23",
            "organizationName": "Owl"
        }
    ]
}*/

@JsonClass(generateAdapter = true)
data class PatientDataResponse(
    val id: Int,
    val firstName: String,
    val lastName: String,
    @Json(name = "middlename")
    val middleName: String?,
    @Json(name = "birthdate")
    val birthDate:String?,
    val photo: String?,
    @Json(name = "insurancePolicies")
    val insurances: List<InsuranceResponse>?
)