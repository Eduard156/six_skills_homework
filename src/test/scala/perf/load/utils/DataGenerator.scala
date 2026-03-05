package perf.load.utils

import com.github.javafaker.Faker

object DataGenerator {
  private val faker = new Faker()

  def randomUser(): Map[String, String] = {
    val name  = faker.name().firstName()
    val email = faker.internet().emailAddress()
    Map("name" -> name, "email" -> email)
  }
}
