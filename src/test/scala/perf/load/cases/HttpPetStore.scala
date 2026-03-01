package perf.load.cases

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

object HttpPetStore {

  val dogNames       = Array(
    "Boss",
    "Rex",
    "Barsik",
    "Bella",
    "Buddy",
    "Sharik",
    "Pes",
    "Belka",
  )
  val testDataFeeder = Iterator.continually(
    Map[String, Any](
      "dogName" -> dogNames(Random.nextInt(dogNames.length)),
      "petId"   -> (System.currentTimeMillis() + Random.nextInt(1000)).longValue
    ),
  )

  val postAddPet = http("Post Add a new pet to the store")
    .post("/pet")
    .body(
      StringBody(
        """
                  {
                    "id": #{petId},
                    "name": "#{dogName}",
                    "category": {
                      "id": 1,
                      "name": "Dogs"
                    },
                    "photoUrls": [
                      "string"
                    ],
                    "status": "available"
                  }
                  """,
      ),
    )
    .asJson
    .check(status is 200)

  val getPetById = http("GET Get pet by id")
    .get("/pet/#{petId}")
    .check(status is 200)

  val putUpdatePet = http("PUT Update an existing pet")
    .put("/pet")
    .body(
      StringBody(
        """
          {
            "id": #{petId},
            "name": "#{dogName}",
            "photoUrls": [
              "string"
            ],
            "status": "sold"
          }
          """,
      ),
    )
    .check(status is 200)

}
