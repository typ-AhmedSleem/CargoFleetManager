package com.typ.cargo.ui.converters

import com.typ.cargo.enums.DrivingMode
import com.typ.cargo.interfaces.BaseUiDataConverter

object UiDrivingModeConverter : BaseUiDataConverter<DrivingMode, String> {
    override fun convert(data: DrivingMode): String {
        return when (data) {
            DrivingMode.MANUAL -> "Manual"
            DrivingMode.SEMI_AUTONOMOUS -> "Semi-Autonomous"
        }
    }
}