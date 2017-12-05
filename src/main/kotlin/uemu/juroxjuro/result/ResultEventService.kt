package uemu.juroxjuro.result

import org.springframework.stereotype.Service

@Service
class ResultEventService(private val resultEventRepository: ResultEventRepository) {

  fun save(resultEvent: ResultEvent) {
    resultEventRepository.save(resultEvent)
  }

  fun findAll(): Iterable<ResultEvent> {
    return resultEventRepository.findAll()
  }

}