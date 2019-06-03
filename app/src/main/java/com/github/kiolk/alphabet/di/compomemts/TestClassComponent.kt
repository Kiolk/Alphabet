package com.github.kiolk.alphabet.di.compomemts

import com.github.kiolk.alphabet.TestClass
import dagger.Subcomponent

@Subcomponent
interface TestClassComponent {

    val testClass : TestClass
}