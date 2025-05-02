package com.typ.cargo.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.typ.cargo.data.models.DashboardData
import com.typ.cargo.data.models.StreamVideoFrame
import com.typ.cargo.data.repositories.abstractions.DashboardRepository
import com.typ.cargo.workers.StreamWatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: DashboardRepository,
    private val streamWatcher: StreamWatcher
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

    fun startWatchingVideoStream(): Flow<StreamVideoFrame> {
        return streamWatcher.subscribeToVideoStream()
    }
}
