package uemu.juroxjuro.operation

import uemu.juroxjuro.common.Event
import java.time.LocalDateTime

class OperationEvent(val firstNumber: Int,
                     val secondNumber: Int,
                     val answer: Int,
                     val answerTimeMillis: Int) : Event {
  val datetime = LocalDateTime.now()!!
}