package serializers

import actors.ExampleActor.ExampleState
import akka.serialization.Serializer
import com.datastax.driver.core.utils.Bytes

class ExampleStateSerializer extends Serializer {
  override def identifier: Int = 12346 // unique identifier for the serializer

  override def toBinary(o: AnyRef): Array[Byte] = o match {
    case state: ExampleState =>
      Bytes.fromHexString(state.data).array()

    case _ =>
      throw new IllegalArgumentException(s"Cannot serialize object of type ${o.getClass}")
  }

  override def fromBinary(bytes: Array[Byte], manifest: Option[Class[_]]): AnyRef = {
    val data = Bytes.toHexString(bytes)
    ExampleState(data)
  }

  override def includeManifest: Boolean = true
}
