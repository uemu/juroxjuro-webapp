package uemu.juroxjuro.point

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Point(val point: Int = 0) {
  @Id
  val id = 1

  companion object {
    val INIT = Point()
  }
}