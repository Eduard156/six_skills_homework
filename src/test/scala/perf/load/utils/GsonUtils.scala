package perf.load.utils

import com.google.gson.Gson

object GsonUtils {

  private val gson = new Gson()

  def toJson(obj: Any): String = gson.toJson(obj)

}
