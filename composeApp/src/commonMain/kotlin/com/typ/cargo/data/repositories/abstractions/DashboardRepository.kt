package com.typ.cargo.data.repositories.abstractions

import com.typ.cargo.data.models.DashboardData

interface DashboardRepository {

    suspend fun getDashboardData(): DashboardData

}
