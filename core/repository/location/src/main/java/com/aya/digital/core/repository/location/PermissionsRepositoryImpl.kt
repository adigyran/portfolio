package com.aya.digital.core.repository.location

import com.aya.digital.core.data.location.repository.PermissionsRepository
import io.reactivex.rxjava3.core.Observable

internal class PermissionsRepositoryImpl : PermissionsRepository {
    override fun check(vararg permissions: String): Observable<List<PermissionsRepository.Permission>> {
        TODO("Not yet implemented")
    }

    override fun request(permissions: Array<PermissionsRepository.Permission>): Observable<PermissionsRepository.Result> {
        TODO("Not yet implemented")
    }
}