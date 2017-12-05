package uemu.juroxjuro.result

import uemu.juroxjuro.common.Event
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class ResultEvent(val datetime: LocalDateTime,
                  val firstNumber: Int,
                  val secondNumber: Int,
                  val answer: Int,
                  val answerTimeMillis: Int,
                  val correct: Boolean,
                  val point: Int) : Event {
  @Id
  val id = UUID.randomUUID().toString()
}