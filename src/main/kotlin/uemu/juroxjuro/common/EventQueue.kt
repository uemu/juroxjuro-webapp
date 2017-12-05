package uemu.juroxjuro.common;

import org.springframework.http.codec.ServerSentEvent
import reactor.core.publisher.EmitterProcessor
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import java.util.function.Consumer

abstract class EventQueue <E: Event>(bufferSize: Int){
  private val emitterProcessor = EmitterProcessor.create<ServerSentEvent<E>>(bufferSize, false)
  private val sink = emitterProcessor.sink(FluxSink.OverflowStrategy.LATEST)
  private val sharedFlux = Flux.from(emitterProcessor).share();

  fun push(event: E) {
    sink.next(ServerSentEvent.builder(event).build())
  }

  fun subscribe(consumer: Consumer<ServerSentEvent<E>>) {
    sharedFlux.subscribe(consumer)
  }

  fun subscribe(): Flux<ServerSentEvent<E>> {
    return sharedFlux
  }
}
