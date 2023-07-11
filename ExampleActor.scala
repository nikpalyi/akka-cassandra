package actors

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import akka.persistence.typed.PersistenceId
import akka.persistence.typed.scaladsl.{Effect, EventSourcedBehavior}

object ExampleActor {
  sealed trait Command
  case class CommandA(payload: String, replyTo: ActorRef[Event]) extends Command
  case class CommandB(payload: Int, replyTo: ActorRef[Event]) extends Command
  case class GetState(replyTo: ActorRef[ExampleState]) extends Command

  sealed trait Event
  case class EventA(payload: String) extends Event
  case class EventB(payload: Int) extends Event

  case class ExampleState(data: String)

  def apply(entityId: String): Behavior[Command] =
    Behaviors.setup(context => new ExampleActor(context, entityId))
}

class ExampleActor(context: ActorContext[ExampleActor.Command], entityId: String)
  extends AbstractBehavior[ExampleActor.Command](context) {

  import ExampleActor._

  private var state: ExampleState = ExampleState("")

  override def onMessage(msg: Command): Behavior[Command] =
    msg match {
      case CommandA(payload, replyTo) =>
        val event = EventA(payload)
        persistEvent(event, replyTo)

      case CommandB(payload, replyTo) =>
        val event = EventB(payload)
        persistEvent(event, replyTo)

      case GetState(replyTo) =>
        replyTo ! state
        Behaviors.same
    }

  private def persistEvent(event: Event, replyTo: ActorRef[Event]): Behavior[Command] = {
    val effect = Effect.persist(event).thenRun { _ =>
      state = updateState(event)
      replyTo ! event
    }
    Effect.effect(effect)
  }

  private def updateState(event: Event): ExampleState = event match {
    case EventA(payload) =>
      ExampleState(payload)

    case EventB(payload) =>
      ExampleState(payload.toString)
  }

  override def persistenceId: PersistenceId =
    PersistenceId(entityId)
}
