package aecor.core.serialization

import java.util

import aecor.core.serialization.protobuf.EntityEventEnvelope
import org.apache.kafka.common.serialization.{Deserializer, Serializer}

trait PureDeserializer[A] extends Deserializer[A] {
  final override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = ()
  final override def close(): Unit = ()
}

class EntityEventEnvelopeSerde extends Serializer[EntityEventEnvelope] with PureDeserializer[(String, EntityEventEnvelope)] {
  override def serialize(topic: String, data: EntityEventEnvelope): Array[Byte] =
    data.toByteArray

  override def deserialize(topic: String, data: Array[Byte]): (String, EntityEventEnvelope) =
    (topic, EntityEventEnvelope.parseFrom(data))
}