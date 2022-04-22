package com.github.kiolk.alphabet.di.compomemts

import com.github.kiolk.alphabet.di.compomemts.presenters.AboutPresenterComponent
import com.github.kiolk.alphabet.di.compomemts.presenters.GamePresenterComponent
import com.github.kiolk.alphabet.di.compomemts.presenters.GamePreviewComponent
import com.github.kiolk.alphabet.di.compomemts.presenters.HelpPresenterComponent
import com.github.kiolk.alphabet.di.compomemts.presenters.HomePresenterComponent
import com.github.kiolk.alphabet.di.compomemts.presenters.MainPresenterComponent
import com.github.kiolk.alphabet.di.compomemts.presenters.PolicyPresenterComponent
import com.github.kiolk.alphabet.di.compomemts.presenters.ResultPresenterCompomemt
import com.github.kiolk.alphabet.di.compomemts.presenters.SettingsPresenterComponent
import com.github.kiolk.alphabet.di.compomemts.presenters.SharePresenterComponent
import com.github.kiolk.alphabet.di.compomemts.presenters.SplashPresenterComponent
import com.github.kiolk.alphabet.di.compomemts.presenters.ThanksPresenterComponenet
import com.github.kiolk.alphabet.di.compomemts.presenters.WordsPresenterComponent
import com.github.kiolk.alphabet.di.modules.AppModule
import com.github.kiolk.alphabet.di.modules.DataSourceModule
import com.github.kiolk.alphabet.di.modules.DbModule
import com.github.kiolk.alphabet.di.modules.NetworkModule
import com.github.kiolk.alphabet.di.modules.presenter.GamePresenterModule
import com.github.kiolk.alphabet.di.modules.presenter.GamePreviewPresenterModule
import com.github.kiolk.alphabet.di.modules.presenter.HomePresenterModule
import com.github.kiolk.alphabet.di.modules.presenter.ResultPresenterModule
import com.github.kiolk.feature_toggles.di.FeatureToggleComponent
import com.github.kiolk.feature_upload_image.di.UploadImageComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataSourceModule::class,
        DbModule::class,
        NetworkModule::class],
    dependencies = [
        FeatureToggleComponent::class,
        UploadImageComponent::class
    ]
)
interface ApplicationComponent {

    fun plusTaestClass(): TestClassComponent

    fun plusWordPresenter(): WordsPresenterComponent

    fun plusHomePresenter(module: HomePresenterModule): HomePresenterComponent

    fun plusGamePresenter(module: GamePresenterModule): GamePresenterComponent

    fun plusGamePreviewComponent(module: GamePreviewPresenterModule): GamePreviewComponent

    fun plusResultPresenter(module: ResultPresenterModule): ResultPresenterCompomemt

    fun plusSplashPresenter(): SplashPresenterComponent

    fun plusMainPresenter(): MainPresenterComponent

    fun plusSettingsPresenter(): SettingsPresenterComponent

    fun plusAboutPresenter(): AboutPresenterComponent

    fun plusHelpPresenter(): HelpPresenterComponent

    fun plusPolicyPresenter(): PolicyPresenterComponent

    fun plusThanksPresenter(): ThanksPresenterComponenet

    fun plusSharePresenter(): SharePresenterComponent

    @Component.Factory
    interface Factory {
        fun create(
            uploadImageComponent: UploadImageComponent,
            featureToggleComponent: FeatureToggleComponent,
            appModule: AppModule,
            dbModule: DbModule,
            networkModule: NetworkModule
        ): ApplicationComponent
    }
}