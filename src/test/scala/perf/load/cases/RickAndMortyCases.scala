package perf.load.cases

import io.gatling.commons.validation._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object RickAndMortyCases {

  val httpGetCharacterApi = "/api/character/"

  var idsForRickAndMortyGet = Seq("1", "183").mkString(",")

  val getCharactersByIds = http("GET Characters by IDs 1, 183")
    .get(s"$httpGetCharacterApi$idsForRickAndMortyGet")
    .check(jsonPath("$[*].id").findAll.saveAs("characterIds"))

  val validateCharactersIds = exec { session =>
    session("characterIds").validate[Seq[String]] match {
      case Success(ids)     => println(s"[INFO] characterIds is Seq[String]: $ids")
      case Failure(message) => println(s"[WARN] characterIds validation failed: $message")
    }
    session
  }

  val getCharacterById = http("GET Character by ID")
    .get(s"$httpGetCharacterApi#{charId}")
    .check(status is 200, jsonPath("$.name").saveAs("characterName"))

  val logCharacterName = exec { session =>
    session("characterName").validate[String] match {
      case Success(name) => println(s"[INFO] Character: $name")
      case Failure(e)    => println(s"[WARN] Character name not found: $e")
    }
    session
  }

  val randomLocationId = exec { session =>
    val id      = (scala.util.Random.nextInt(126) + 1).toString
    val updated = session.set("locData", Map("locationId" -> id))
    updated
  }

  val getLocationById = http("GET location by id").get { session =>
    val locationId = session("locData")
      .as[Map[String, String]]
      .getOrElse("locationId", "1")

    s"/api/location/$locationId"
  }
    .check(status is 200)

}
