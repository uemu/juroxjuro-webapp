package uemu.juroxjuro.result

import org.springframework.stereotype.Component
import uemu.juroxjuro.common.EventQueue

@Component
class ResultEventQueue : EventQueue<ResultEvent>(10)