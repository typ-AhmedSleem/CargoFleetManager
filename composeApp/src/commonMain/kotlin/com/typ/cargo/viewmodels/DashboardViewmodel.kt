package com.typ.cargo.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.typ.cargo.data.models.DashboardData
import com.typ.cargo.data.repositories.abstractions.DashboardRepository
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: DashboardRepository
) : ViewModel() {

    private val _dashboardData = mutableStateOf<DashboardData?>(null)
    val dashboardData: State<DashboardData?> = _dashboardData

    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            _dashboardData.value = repository.getDashboardData()
        }
    }
}
