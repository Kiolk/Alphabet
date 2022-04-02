package com.github.kiolk.feature_toggles.toggles

import com.github.kiolk.feature_toggles.base.FeatureToggle

class UploadImageFeatureToggle : FeatureToggle() {

    override val name: String = "Upload image to backend toggle"

    override val defaultValue: Boolean = true
}
