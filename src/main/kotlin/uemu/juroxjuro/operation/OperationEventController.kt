package uemu.juroxjuro.operation

import org.springframework.http.HttpStatus
import org.springframework.http.codec.ServerSentEvent
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@Controller
class OperationEventController(private val queue: OperationEventQueue) {

  @GetMapping("/api/operation/subscribe")
  @ResponseBody
  fun subscribe(): Flux<ServerSentEvent<OperationEvent>> {
    return queue.subscribe()
  }

  @PostMapping("/api/operation/publish")
  @ResponseStatus(HttpStatus.CREATED)
  fun publish(@RequestParam("firstNumber") firstNumber: Int,
              @RequestParam("secondNumber") secondNumber: Int,
              @RequestParam("answer") answer: Int,
              @RequestParam("answerTimeMillis") answerTimeMillis: Int) {
    queue.push(OperationEvent(firstNumber, secondNumber, answer, answerTimeMillis))
  }

  @GetMapping("/arduino-api/operation/publish")
  @ResponseStatus(HttpStatus.CREATED)
  fun publishForArduino(@RequestParam("csv") csv: String) {
    val params = csv.split(",").map { p -> if (p == "") 0 else p.toInt() }
    publish(params[0], params[1], params[2], params[3])
  }

}