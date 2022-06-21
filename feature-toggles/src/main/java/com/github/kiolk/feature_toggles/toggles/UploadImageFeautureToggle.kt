package com.github.kiolk.feature_toggles.toggles

import com.github.kiolk.feature_toggles.base.FeatureToggle
import com.github.kiolk.feature_toggles.base.ValueToggleType

class UploadImageFeatureToggle : FeatureToggle<Boolean>() {

    override val name: String = "Upload image to backend toggle"

    override val defaultValue: Boolean = true

    override fun getType(): ValueToggleType = ValueToggleType.BOOLEAN
}
