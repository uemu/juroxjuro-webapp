package uemu.juroxjuro.operation

import org.springframework.stereotype.Component
import uemu.juroxjuro.common.EventQueue

@Component
class OperationEventQueue : EventQueue<OperationEvent>(10)