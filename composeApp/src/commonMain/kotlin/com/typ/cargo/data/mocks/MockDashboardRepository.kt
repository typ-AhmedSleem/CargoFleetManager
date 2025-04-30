package com.typ.cargo.data.mocks

import com.typ.cargo.data.models.DashboardData
import com.typ.cargo.data.repositories.abstractions.DashboardRepository
import com.typ.cargo.enums.DrivingMode
import kotlin.random.Random

class MockDashboardRepository : DashboardRepository {

    override suspend fun getDashboardData(): DashboardData {
        return DashboardData(
            batteryPercentage = Random.nextInt(0, 100),
            leftMotorSpeed = Random.nextInt(-100, 100),
            rightMotorSpeed = Random.nextInt(-100, 100),
            driversTemperature = Random.nextInt(0, 50),
            drivingMode = DrivingMode.entries.random(),
            robotHeading = Random.nextInt(0, 359),
            errorLogs = null
        )
    }

}
