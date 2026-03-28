package perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.apache.kafka.clients.producer.ProducerConfig
import org.galaxio.gatling.amqp.Predef._
import org.galaxio.gatling.amqp.protocol.AmqpProtocolBuilder
import org.galaxio.gatling.config.SimulationConfig._
import org.galaxio.gatling.jdbc.Predef._
import org.galaxio.gatling.jdbc.protocol.JdbcProtocolBuilder
import org.galaxio.gatling.kafka.Predef.kafka
import org.galaxio.gatling.kafka.protocol.KafkaProtocolBuilder
import perf.load.Utility.debugMemoryAndOpts

import scala.concurrent.duration.DurationInt

package object load {

  if (sys.env.get("DEBUG").exists(_.equalsIgnoreCase("true")))
    debugMemoryAndOpts()

//  val apiKey = sys.env.getOrElse("X_API_KEY", throw new RuntimeException("X_API_KEY not set"))

  // common http protocol params (eg headers, checks)
  val httpProtocol = http
    /*.baseUrl(
      baseUrl,
    )*/
    .contentTypeHeader("application/json")
//    .header("x-api-key", apiKey)
    .acceptHeader(
      "application/json,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8;",
    ) // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
    .disableFollowRedirect

  val jdbcProtocol: JdbcProtocolBuilder = DB
    .url(getStringParam("dbUrl"))
    .username(getStringParam("dbUser"))
    .password(getStringParam("dbPassword"))
    .connectionTimeout(2.minute)

  val amqpHost: String     = getStringParam("amqpHost")
  val amqpPort: Int        = getIntParam("amqpPort")
  val amqpLogin: String    = getStringParam("amqpLogin")
  val amqpPassword: String = getStringParam("amqpPassword")

  val amqpProtocol: AmqpProtocolBuilder   = amqp
    .connectionFactory(
      rabbitmq
        .host(amqpHost)
        .port(amqpPort)
        .username(amqpLogin)
        .password(amqpPassword)
        .vhost("/"),
    )
    .replyTimeout(60000)
    .consumerThreadsCount(8)
    .usePersistentDeliveryMode
  val kafkaProtocol: KafkaProtocolBuilder = kafka
    .topic("myTopic")
    .properties(
      Map(
        ProducerConfig.ACKS_CONFIG                   -> "1",
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG      -> getStringParam("kafkaUrl"),
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG   -> "org.apache.kafka.common.serialization.StringSerializer",
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringSerializer",
      ),
    )
}
