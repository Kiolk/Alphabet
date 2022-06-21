package com.github.kiolk.feature_toggles.toggles

import com.github.kiolk.feature_toggles.base.FeatureToggle
import com.github.kiolk.feature_toggles.base.ValueToggleType

class RemoteUploadImageFeatureToggle : FeatureToggle<Boolean>() {

    override val name: String = "upload_image_feature_toggle"

    override val defaultValue: Boolean = false

    override fun getType(): ValueToggleType = ValueToggleType.BOOLEAN
}