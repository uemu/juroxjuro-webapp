package uemu.juroxjuro.point

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import uemu.juroxjuro.result.ResultEventQueue
import java.util.function.Consumer

@Controller
class PointController(private val pointService: PointService,
                      resultEventQueue: ResultEventQueue) {

  init {
    resultEventQueue.subscribe(Consumer { event -> pointService.addPoint(event.data().point) })
  }

  @GetMapping("/arduino-api/point/get")
  @ResponseBody
  fun get(): Int {
    return pointService.get().orElse(Point.INIT).point
  }

  @GetMapping("/arduino-api/point/save")
  @ResponseStatus(HttpStatus.OK)
  fun save(@RequestParam("point") point: Int) {
    pointService.save(Point(point))
  }

}