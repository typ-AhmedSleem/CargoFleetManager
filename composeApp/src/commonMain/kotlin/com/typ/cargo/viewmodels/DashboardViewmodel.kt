package com.typ.cargo.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.typ.cargo.data.models.DashboardData
import com.typ.cargo.data.repositories.abstractions.DashboardRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: DashboardRepository
) : ViewModel() {

    private val _robotInfo = mutableStateOf<DashboardData?>(null)
    val robotInfo: State<DashboardData?> = _robotInfo

    init {
        observeRobotInfo()
    }

    private fun observeRobotInfo() {
        viewModelScope.launch {
            while (isActive) {
                _robotInfo.value = repository.getDashboardData()
                delay(5000L)
            }
        }
    }
}
