package uemu.juroxjuro.point

import org.springframework.stereotype.Service
import java.util.*

@Service
class PointService(private val pointRepository: PointRepository) {

  fun get(): Optional<Point> {
    return pointRepository.findById(1)
  }

  fun save(point: Point) {
    pointRepository.save(point)
  }

  fun addPoint(additional: Int) {
    if (additional == 0) return;
    pointRepository.findById(1).ifPresent({ currentPoint ->
      val newPoint = Point(currentPoint.point + additional)
      pointRepository.save(newPoint)
    })
  }

}