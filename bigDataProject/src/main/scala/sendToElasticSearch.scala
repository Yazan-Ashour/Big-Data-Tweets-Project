import requests.Response

object sendToElasticSearch {

  def sendToElasticSearch(jsonString: String): Response = {

    val elasticUrl = "http://localhost:9200/tweets/_doc"
    requests.post(
      url = elasticUrl,
      data = jsonString,
      headers = Map("Content-Type" -> "application/json")
    )
  }
}
