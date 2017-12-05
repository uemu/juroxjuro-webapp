package uemu.juroxjuro.result

import org.springframework.http.codec.ServerSentEvent
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import reactor.core.publisher.Flux
import uemu.juroxjuro.operation.OperationEvent
import uemu.juroxjuro.operation.OperationEventQueue
import java.util.function.Consumer

@Controller
class ResultEventController(private val resultEventService: ResultEventService,
                            private val resultEventQueue: ResultEventQueue,
                            operationEventQueue: OperationEventQueue) {

  init {
    operationEventQueue.subscribe(Consumer { event -> register(event.data()) })
  }

  @GetMapping("/api/result/subscribe")
  @ResponseBody
  fun subscribe(): Flux<ServerSentEvent<ResultEvent>> {
    return resultEventQueue.subscribe();
  }

  @GetMapping("/api/result/all")
  @ResponseBody
  fun all(): Iterable<ResultEvent> {
    return resultEventService.findAll()
  }

  fun register(event: OperationEvent) {
    if (event.answerTimeMillis == 0) return;
    val correct = event.firstNumber * event.secondNumber == event.answer
    val result = ResultEvent(event.datetime,
            event.firstNumber,
            event.secondNumber,
            event.answer,
            event.answerTimeMillis,
            correct,
            if (correct) calcPoint(event.firstNumber, event.secondNumber) else 0)
    resultEventQueue.push(result)
    resultEventService.save(result)
  }

  fun calcPoint(firstNumber: Int, secondNumber: Int): Int {
    if (firstNumber == 1 || secondNumber == 1) return 1
    if (firstNumber > 10 && secondNumber > 10) return 4
    if (firstNumber > 10 || secondNumber > 10) return 3
    if (firstNumber > 5 || secondNumber > 5) return 2
    return 1
  }

}