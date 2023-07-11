package events

import akka.serialization.Serializer

sealed trait ExampleEvent

case class ExampleEventA(payload: String) extends ExampleEvent
case class ExampleEventB(payload: Int) extends ExampleEvent

object ExampleEventSerializer {
  val Identifier = 12345 // unique identifier for the serializer
}

class ExampleEventSerializer extends Serializer {
  override def identifier: Int = ExampleEventSerializer.Identifier

  override def toBinary(o: AnyRef): Array[Byte] = o match {
    case event: ExampleEventA =>
      event.payload.getBytes

    case event: ExampleEventB =>
      event.payload.toString.getBytes

    case _ =>
      throw new IllegalArgumentException(s"Cannot serialize object of type ${o.getClass}")
  }

  override def fromBinary(bytes: Array[Byte], manifest: Option[Class[_]]): AnyRef = {
    val payload = new String(bytes)
    manifest match {
      case Some(man) if man == classOf[ExampleEventA] =>
        ExampleEventA(payload)

      case Some(man) if man == classOf[ExampleEventB] =>
        ExampleEventB(payload.toInt)

      case _ =>
        throw new IllegalArgumentException("Cannot deserialize object")
    }
  }

  override def includeManifest: Boolean = true
}
