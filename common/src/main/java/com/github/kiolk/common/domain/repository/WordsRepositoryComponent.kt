package com.github.kiolk.common.domain.repository

import com.github.kiolk.common.domain.repository.word.WordsRepository
import com.github.kiolk.common_di.base.DIComponent
import com.github.kiolk.common_di.holder.LazyComponentHolder

interface WordsRepositoryComponent : DIComponent {

    fun getRepository(): WordsRepository
}

object WordsRepositoryComponentHolder : LazyComponentHolder<WordsRepositoryComponent>()