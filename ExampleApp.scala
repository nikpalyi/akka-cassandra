import actors.ExampleActor
import actors.ExampleActor.{CommandA, CommandB, GetState}
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors

object ExampleApp extends App {
  val system = ActorSystem[Nothing](Behaviors.empty, "example-cluster-system")

  val actorRef = system.actorOf(ExampleActor("example-entity-1"))

  actorRef ! CommandA("Hello", system.ignoreRef)
  actorRef ! CommandB(42, system.ignoreRef)
  actorRef ! GetState(system.ignoreRef)

  Thread.sleep(2000)
  system.terminate()
}
