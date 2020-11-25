package com.kotlinquiz.app.data.database.question


import com.architectcoders.data.source.LocalDataSource
import com.architectcoders.domain.question.Question
import com.kotlinquiz.app.data.database.ProjectDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource (db: ProjectDatabase) : LocalDataSource {

    private val questionDao=db.questionDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO){questionDao.questionCount()<=0}

    override suspend fun saveQuestion(question: List<Question>) {
        withContext(Dispatchers.IO){questionDao.insertUid(question.map{it.toRoomQuestion()  })}
    }

    override suspend fun getQuestions(): List<Question> =
        withContext(Dispatchers.IO){
            questionDao.getAll().map { it.toDomainQuestion() }
        }

    override suspend fun findById(id: String): Question = withContext(Dispatchers.IO){
        questionDao.findById(id).toDomainQuestion()
    }

    override suspend fun update(question: Question) {

        withContext(Dispatchers.IO){questionDao.updateUid(question.toRoomQuestion())}
    }

}