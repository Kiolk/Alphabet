package com.github.kiolk.alphabet.data.domain.topics

import com.github.kiolk.alphabet.data.models.topic.Topic
import com.github.kiolk.alphabet.data.models.topic.local.TopicWithPhoto
import com.github.kiolk.alphabet.data.models.topic.local.TotalReadWordsTopic
import com.github.kiolk.alphabet.data.models.topic.local.TotalWordsTopic
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.common.domain.base.UseCase
import io.reactivex.Flowable
import io.reactivex.functions.Function3
import javax.inject.Inject

class GetActualTopicUseCase
@Inject
constructor(private val wordsRepository: WordsRepository) :
    UseCase<Flowable<List<Topic>>, GetActualTopicUseCase.Params> {

    override fun execute(params: Params): Flowable<List<Topic>> {
        val totalWordsByTopic = wordsRepository.countTotalWordsTopic()
        val readWordsByTopic = wordsRepository.countReadWordsTopic()
        val topicWithPhoto = wordsRepository.getTopicsWithPhoto()

        return Flowable.zip(
            totalWordsByTopic, readWordsByTopic, topicWithPhoto,
            Function3<List<TotalWordsTopic>, List<TotalReadWordsTopic>, List<TopicWithPhoto>, List<Topic>> { total, read, withPhoto ->
                return@Function3 total.map { topic -> Topic("", topic.topic, 0, topic.total) }
                    .filter { topic -> topic.total >= MIN_WORDS_IN_TOPIC }
                            .map { available ->
                                Topic(available.picture, available.title, read.firstOrNull { topic -> topic.topic == available.title }?.read
                                        ?: 0, available.total)
                            }.map { withoutPhoto ->
                                Topic(withPhoto.firstOrNull { topic -> topic.topic == withoutPhoto.title }?.image
                                        ?: EMPTY_HOLDER, withoutPhoto.title, withoutPhoto.read, withoutPhoto.total)
                            }
                })

    }

    class Params

    companion object {
        private const val EMPTY_HOLDER = "https://media-prideofmaui.netdna-ssl.com/blog/wp-content/uploads/2014/10/Top-10-Animals-in-Maui_Hawaiian-Owl-aka-Pueo-header.jpg"
        private const val MIN_WORDS_IN_TOPIC = 4
    }
}