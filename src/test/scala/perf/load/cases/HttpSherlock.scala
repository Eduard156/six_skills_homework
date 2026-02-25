package perf.load.cases

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object HttpSherlock {

  val postToken = http("POST /api/token")
    .post("/api/token/")
    .body(
      StringBody(
        """
          {
            "username": "sherlock",
            "password": "password1"
          }
        """,
      ),
    )
    .asJson
    .check(status is 200)

}
